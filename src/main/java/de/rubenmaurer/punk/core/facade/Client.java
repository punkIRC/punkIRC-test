package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.rubenmaurer.punk.core.util.*;
import de.rubenmaurer.punk.evaluation.Response;
import de.rubenmaurer.punk.messages.Template;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class for representing an irc client.
 *
 * @author Ruben Maurer
 */
public class Client {

    /**
     * The nickname
     */
    private String nickname;

    /**
     * Get the nickname
     *
     * @return the nickname
     */
    public String nickname() {
        return nickname;
    }

    /**
     * The Username.
     */
    private String username;

    /**
     * Username string.
     *
     * @return the string
     */
    public String username() {
        return username;
    }

    /**
     * The Realname.
     */
    private String realname;

    /**
     * Realname string.
     *
     * @return the string
     */
    public String realname() {
        return realname;
    }

    /**
     * The Last lines.
     */
    private String[] lastLines = new String[] {""};

    /**
     * Last lines string [ ].
     *
     * @return the string [ ]
     */
    public String[] lastLines() {
        return lastLines;
    }

    /**
     * The Last response.
     */
    private String lastResponse;

    /**
     * Last response string.
     *
     * @return the string
     */
    public String lastResponse() {
        String[] splitted = lastResponse.split("\r\n");
        return splitted[splitted.length - 1];
    }

    /**
     * The Connection.
     */
    private ActorRef connection;

    /**
     * The Connection manager.
     */
    static ActorRef connectionManager;

    /**
     * Instantiates a new Client.
     *
     * @param nickname the nickname
     * @param username the username
     * @param realname the realname
     * @throws Exception the exception
     */
    private Client(String nickname, String username, String realname) throws Exception {
        this.nickname = nickname;
        this.username = username;
        this.realname = realname;

        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connectionManager, "connection-request", timeout);
        this.connection = (ActorRef) Await.result(future, timeout.duration());
    }

    /**
     * Tries to connect to the irc server.
     *
     * @return connection established?
     */
    public Boolean connect() {
        boolean connected = false;
        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connect", timeout);

        try {
            connected = (Boolean) Await.result(future, timeout.duration());
        } catch (Exception e) {
            Terminal.printError(e.getMessage());
        }

        return connected;
    }

    public Boolean connectAndIdle() {
        boolean isConnected = connect();

        try {
            idle(1);
        } catch (Exception e) {
            Log.debug(e.getMessage());
        }

        return isConnected;
    }

    public void authenticate() throws Exception {
        this.sendAndReceiveAll(ClientUtils.auth(this), Settings.authLines());
    }

    public void authenticateAndJoin(String channel) throws Exception {
        authenticate();

        sendAndReceive(ClientUtils.joinChannel(channel));
    }

    public LinkedList<String> log(int code) {
        String result = null;
        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, code, timeout);

        try {
            result = (String) Await.result(future, timeout.duration());
        } catch (Exception e) {
            Log.debug(e.getMessage());
        }

        if (result != null) {
            return new LinkedList<>(Arrays.asList(result.split(";")));
        }

        return new LinkedList<>();
    }

    private LinkedList<String> journal() {
        String result = null;
        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "journal", timeout);

        try {
            result = (String) Await.result(future, timeout.duration());
        } catch (Exception e) {
            Log.debug(e.getMessage());
        }

        if (result != null) {
            return new LinkedList<>(Arrays.asList(result.split(";")));
        }

        return new LinkedList<>();
    }

    public LinkedList<String> getJournalEntries(String part) {
        LinkedList<String> result = new LinkedList<>();

        journal().forEach(s -> {
            if (s.contains(part)) result.add(s);
        });

        return result;
    }

    public LinkedList<String> log(Response response) {
        return log(response.value);
    }

    /**
     * Disconnect the client from the server.
     */
    public void disconnect() {
        this.connection.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }

    /**
     * Is client connected with the server?
     *
     * @return is connected?
     */
    public boolean isConnected() {
        boolean result = false;

        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connected", timeout);

        try {
            result = (Boolean) Await.result(future, timeout.duration());
        } catch (Exception e) {
            if (Settings.debug()) {
                System.err.println(Template.get("DEBUG").single("message", e.getMessage()).render());
            }
        }

        return result;
    }

    /**
     * Send a message and receives an answer.
     *
     * @param message the message
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceive(String message) throws Exception {
        return sendAndReceive(message, Settings.expectedLines());
    }

    /**
     * Send a message and receives an answer.
     *
     * @param message       the message
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceive(String message, int expectedLines) throws Exception {
        return sendAndReceive(message, expectedLines, true);
    }

    /**
     * Send a message and receives an answer.
     *
     * @param message       the message
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceive(String message, int expectedLines, int maxLines) throws Exception {
        return sendAndReceive(message, expectedLines, maxLines, true);
    }

    /**
     * Send a message and receives an answer.
     *
     * @param message       the message
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceive(String message, int expectedLines, boolean sendLast) throws Exception {
        return sendAndReceive(message, expectedLines, expectedLines, sendLast);
    }

    /**
     * Send a message and receives an answer.
     *
     * @param message       the message
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceive(String message, int expectedLines, int maxLines, boolean sendLast) throws Exception {
        if (!isConnected()) connect();
        lastLines = new String[]{ "" };

        if (isConnected()) {
            Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(connection, Ask.create(message, expectedLines, maxLines), timeout);

            try {
                lastResponse = (String) Await.result(future, timeout.duration());
                lastLines = lastResponse.split(Settings.delimiter());

                Log.debug(lastResponse);
            } catch (Exception exception) {
                if (sendLast && expectedLines > 0) {
                    Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
                    Future<Object> f = Patterns.ask(connection, "last", timeout);

                    lastResponse = (String) Await.result(f, t.duration());
                    lastLines = lastResponse.split(Settings.delimiter());

                    Log.debug(exception.getMessage());
                    Terminal.printError(lastResponse, Thread.currentThread().getStackTrace()[3].getMethodName());
                }
            }

            return lastLines;
        }

        throw new RuntimeException("Client not connected");
    }

    /**
     * Send a list of messages and receives an answer.
     *
     * @param messages      the messages
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceiveAll(List<String> messages, int expectedLines) throws Exception {
        return sendAndReceiveAll(messages, expectedLines, expectedLines);
    }

    /**
     * Send a list of messages and receives an answer.
     *
     * @param messages      the messages
     * @param expectedLines the expected lines
     * @return the response
     * @throws Exception the exception
     */
    public String[] sendAndReceiveAll(List<String> messages, int expectedLines, int maxLines) throws Exception {
        int index = 1;
        int lineCount = 0;

        for (String messsage : messages) {
            if (index == messages.size()) lineCount = expectedLines;
            sendAndReceive(messsage, lineCount, maxLines);
            index++;
        }

        return lastLines;
    }

    /**
     * Send a message.
     *
     * @param message the message
     * @throws Exception the exception
     */
    public void send(String message) throws Exception {
        sendAndReceive(message, 0);
    }

    /**
     * Send a list of messages.
     *
     * @param messages the messages
     * @throws Exception the exception
     */
    public void sendAll(List<String> messages) throws Exception {
        for (String message : messages) {
            send(message);
        }
    }

    public String last() {
        Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> f = Patterns.ask(connection, "last", t);

        try {
            return (String) Await.result(f, t.duration());
        } catch (Exception e) {
            if (Settings.debug()) {
                System.err.println(Template.get("DEBUG").single("message", e.getMessage()).render());
            }
        }

        return "";
    }

    public String trash() {
        Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> f = Patterns.ask(connection, "trash", t);

        try {
            return ((String) Await.result(f, t.duration())).split("\r\n")[0];
        } catch (Exception e) {
            if (Settings.debug()) {
                System.err.println(Template.get("DEBUG").single("message", e.getMessage()).render());
            }
        }

        return "";
    }

    private void idle(int time) throws Exception {
        Thread.sleep(time * 1000);
    }

    /**
     * Create a new client.
     *
     * @param nickname the nickname
     * @param username the username
     * @param realname the realname
     * @return the client
     * @throws Exception the exception
     */
    public static Client create(String nickname, String username, String realname) throws Exception {
        return new Client(nickname, username, realname);
    }

    /**
     * Create a new client.
     *
     * @param preset the preset
     * @return the client
     * @throws Exception the exception
     */
    public static Client create(Preset preset) throws Exception {
        return new Client(preset.nickname(), preset.username(), preset.realname());
    }

    public enum Preset {
        SCHROTTY ("schrotty", "schrottler", "Rodolf Schrottler"),
        MAX ("max", "maxine", "Maxine Caulfield"),
        CHLOE ("chloe", "elisabeth", "Chloe Elisabeth Price"),
        RACHEL ("rachel", "ramber", "Rachel Amber"),
        WARREN ("warren", "apemaster", "Warren Graham"),
        SAMUEL("samuel", "sammy", "Samuel Taylor"),
        VICTORIA ("victoria", "richkid", "Victoria Chase"),
        DAVID ("david", "soldier16", "David Madsen"),
        MARK ("mark", "creeperDude", "Mark Jefferson"),
        JOYCE ("joyce", "queenofkings", "Joyce Price"),
        KATE ("kate", "bunnymommy", "Kate Marsh");

        private String nickname;

        public String nickname() {
            return nickname;
        }

        private String username;

        public String username() {
            return username;
        }

        private String realname;

        public String realname() {
            return realname;
        }

        Preset(String nickname, String username, String realname) {
            this.nickname = nickname;
            this.realname = realname;
            this.username = username;
        }
    }
}
