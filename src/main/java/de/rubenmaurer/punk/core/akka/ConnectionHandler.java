package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;
import de.rubenmaurer.punk.core.util.Ask;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for handling the connection to the irc server.
 *
 * @author Ruben Maurer
 */
public class ConnectionHandler extends AbstractActor {

    /**
     * IRC server address
     */
    private InetSocketAddress remote;

    /**
     * Connection actor
     */
    private ActorRef remoteActor;

    /**
     * Connection manager
     */
    private ActorRef manager;

    /**
     * Actor which sends an ask
     */
    private ActorRef questioner;

    /**
     * Expected response lines
     */
    private int expectedLines;

    /**
     * Response
     */
    private String response = "";

    private LinkedList<String> journal;

    /**
     * Received 'trash'.
     */
    private LinkedList<String> trash;

    /**
     * Incoming data is trash?
     */
    private boolean trashing = true;

    private Map<Integer, LinkedList<String>> log;

    /**
     * Instantiates a new Connection handler.
     *
     * @param host the host
     * @param port the port
     */
    public ConnectionHandler(String host, int port) {
        this.remote = new InetSocketAddress(host, port);
        this.manager = Tcp.get(getContext().getSystem()).getManager();
        this.trash = new LinkedList<>();
        this.log = new HashMap<>();
        this.journal = new LinkedList<>();
    }

    /**
     * Establish connection between client and server.
     */
    private void connect() {
        this.questioner = getSender();
        this.manager.tell(TcpMessage.connect(remote), getSelf());
    }

    /**
     * Count the lines in a given string.
     *
     * @param lines the string
     * @return line count
     */
    private int getLineCount(String lines) {
        return lines.split("\r\n").length;
    }

    private int detectCode(String message) {
        Matcher matcher = Pattern.compile("\\d+").matcher(message);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group());
        }

        return -1;
    }

    private String formatJournal() {
        StringBuilder sb = new StringBuilder();

        journal.forEach(s -> sb.append(String.format("%s;", s)));

        return sb.toString();
    }

    /**
     * Handles incoming messages.
     *
     * @return a receive object
     */
    public Receive createReceive() {
        return receiveBuilder()
                .match(Ask.class, ask -> {
                    questioner = getSender();
                    expectedLines = ask.lineCount();
                    trashing = false;

                    self().tell(ask.message(), self());
                })
                .matchEquals("connect", s -> connect())
                .matchEquals("connected", s -> getSender().tell(remoteActor != null, self()))
                .matchEquals("last", s -> getSender().tell(response, self()))
                .matchEquals("trash", s -> getSender().tell(trash.getLast(), self()))
                .matchEquals("journal", s -> getSender().tell(formatJournal(), self()))
                .match(Tcp.Connected.class, s -> {
                    getSender().tell(TcpMessage.register(getSelf()), getSelf());
                    this.remoteActor = getSender();
                    this.questioner.tell(true, self());
                })
                .match(String.class, msg -> {
                    if (this.remoteActor == null) {
                        self().tell(msg, getSender());
                        return;
                    }

                    this.remoteActor.tell(TcpMessage.write(ByteString.fromString(msg.intern() + '\r' + '\n')), sender());
                })
                .match(Integer.class, code -> {
                    List<String> l = log.getOrDefault(code, new LinkedList<>());

                    StringBuilder sb = new StringBuilder();
                    l.forEach(s -> sb.append(String.format("%s;", s)));

                    getSender().tell(sb.toString(), self());
                })
                .match(Tcp.ConnectionClosed.class, msg -> getContext().stop(getSelf()))
                .match(Tcp.Received.class, msg -> {
                    String incoming = msg.data().decodeString("US-ASCII");
                    String[] splitted = incoming.split("\r\n");

                    for (String split : splitted) {
                        if (split.isEmpty()) continue;
                        int code = detectCode(split);
                        if (code != -1) {
                            if (log.getOrDefault(code, null) == null) {
                                log.put(code, new LinkedList<>());
                            }

                            log.get(code).add(split);
                        }

                        journal.add(split);
                    }

                    if (!trashing) {
                        response = response.concat(incoming);

                        if (getLineCount(response) >= expectedLines) {
                            questioner.tell(response, self());
                            trashing = true;
                            response = "";
                        }

                        return;
                    }

                    trash.add(incoming);
                }).build();
    }

    /**
     * Get the props for spawning a new actor.
     *
     * @param host the servers hostname
     * @param port the servers port
     * @return the properties
     */
    static Props props(String host, int port) {
        return Props.create(ConnectionHandler.class, host, port);
    }
}
