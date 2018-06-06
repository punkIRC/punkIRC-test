package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;
import de.rubenmaurer.punk.core.util.Ask;

import java.net.InetSocketAddress;

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

    /**
     * Instantiates a new Connection handler.
     *
     * @param host the host
     * @param port the port
     */
    public ConnectionHandler(String host, int port) {
        this.remote = new InetSocketAddress(host, port);
        this.manager = Tcp.get(getContext().getSystem()).getManager();
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

                    self().tell(ask.message(), self());
                })
                .matchEquals("connect", s -> connect())
                .matchEquals("connected", s -> getSender().tell(remoteActor != null, self()))
                .matchEquals("last", s -> getSender().tell(response, self()))
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
                .match(Tcp.ConnectionClosed.class, msg -> getContext().stop(getSelf()))
                .match(Tcp.Received.class, msg -> {
                    response = response.concat(msg.data().decodeString("US-ASCII"));

                    if (getLineCount(response) == expectedLines) {
                        questioner.tell(response, self());
                        response = "";
                    }
                })
                .build();
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
