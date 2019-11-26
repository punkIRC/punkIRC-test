package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.core.akka.Ask;
import de.rubenmaurer.punk.util.*;
import de.rubenmaurer.punk.evaluation.Response;
import de.rubenmaurer.punk.util.Template;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Represents a single irc client.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Client {

    /**
     * The clients nickname.
     */
    private String nickname;

    /**
     * Getter for the nickname.
     *
     * @return the nickname
     */
    public String nickname() {
        return nickname;
    }

    /**
     * The clients username.
     */
    private String username;

    /**
     * Getter for the username.
     *
     * @return the username
     */
    public String username() {
        return username;
    }

    /**
     * The clients fullname.
     */
    private String fullname;

    /**
     * Getter for the fullname.
     *
     * @return the fullname
     */
    public String fullname() {
        return fullname;
    }

    /**
     * The last received lines.
     */
    private String[] lastLines = new String[] {""};

    /**
     * Getter for the last received lines.
     *
     * @return the last lines
     */
    public String[] lastLines() {
        return lastLines;
    }

    /**
     * The last received response.
     */
    String lastResponse;

    /**
     * Getter for the last response.
     *
     * @return the last response
     */
    public String lastResponse() {
        String[] splitted = lastResponse.split("\r\n");
        return splitted[splitted.length - 1];
    }

    /**
     * The {@link de.rubenmaurer.punk.core.akka.ConnectionHandler} used for
     * communicate with the irc server.
     */
    private ActorRef connection;

    /**
     * The {@link de.rubenmaurer.punk.core.akka.ConnectionManager} used for
     * creating and retrieving new {@link de.rubenmaurer.punk.core.akka.ConnectionHandler}.
     */
    static ActorRef connectionManager;

    /**
     * Create a new {@link Client} and requests a {@link de.rubenmaurer.punk.core.akka.ConnectionHandler}
     * for the communication with the irc server.
     *
     * @param nickname the clients nickname
     * @param username the clients username
     * @param fullname the clients fullname
     * @throws Exception if handler requests fails
     */
    private Client(String nickname, String username, String fullname) throws Exception {
        this.nickname = nickname;
        this.username = username;
        this.fullname = fullname;

        if (!Settings.isDebug()) {
            Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(connectionManager, "connection-request", timeout);
            this.connection = (ActorRef) Await.result(future, timeout.duration());
        }
    }

    /**
     * Tries to establish an connection to the irc-server.
     * Establishing an connection does not mean the client is auth. at the server.
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

    /**
     * Authenticate the client.
     * Sends both nick and user message.
     */
    public void authenticate() {
        this.sendAndReceiveAll(Utilities.auth(this), Settings.authLines());
    }

    /**
     * Authenticate the client and let it join a channel.
     * Sends a nick, a user and a join message.
     *
     * @param channel the channel to join
     */
    public void authenticateAndJoin(String channel) {
        authenticate();

        sendAndReceive(Utilities.joinChannel(channel));
    }

    /**
     * Retrieves a list of server replies with a specific response code.
     * If no responses matching the given code are found an empty list is returned.
     *
     * @param code the code to search for
     * @return the list of responses
     */
    private LinkedList<String> logOrEmpty(int code) {
        String result;
        LinkedList<String> resultList = new LinkedList<>();
        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, code, timeout);

        try {
            result = (String) Await.result(future, timeout.duration());
            if (result != null) {
                resultList = Arrays.stream(result.split(";")).filter(s -> !s.isEmpty()).collect(Collectors.toCollection(LinkedList::new));
            }
        } catch (Exception e) {
            Terminal.debugErro(e.getMessage());
        }

        if (Settings.devMode()) {
            Terminal.devLog(String.format("Looking for message with code: %d", code));
            Terminal.devLog(resultList.toString());
        }

        return resultList;
    }

    /**
     * Retrieves a list of server replies with a specific response code.
     * If no responses matching the given code are found an empty list is returned.
     *
     * @param response the {@link Response} which code is used
     * @return the list of responses
     */
    public LinkedList<String> logOrEmpty(Response response) {
        return logOrEmpty(response.value);
    }

    /**
     * Retrieves a list of server replies with a specific response code.
     * If no responses matching the given code are found, an exception occurs.
     *
     * @param response the {@link Response} which code is used
     * @return the list of responses
     */
    public LinkedList<String> logOrThrow(Response response) {
        LinkedList<String> result = logOrEmpty(response);

        if (result.isEmpty()) {
            if (response != Response.NONE) {
                throw new RuntimeException(
                        Template.get("EMPTY_STRING_MISSING_CODE")
                                .single("code", response.value < 100 ? String.format("%03d", response.value) : Integer.toString(response.value)).render()
                );
            }

            throw new RuntimeException(Template.get("EMPTY_STRING_FOR_PARSER").render());
        }

        return result;
    }

    /**
     * Terminates the {@link de.rubenmaurer.punk.core.akka.ConnectionHandler} used for
     * communication with the irc server.
     */
    public void disconnect() {
        this.connection.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }

    /**
     * Is a connection with the irc server established?
     *
     * @return connection established?
     */
    public boolean isConnected() {
        boolean result = false;

        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connected", timeout);

        try {
            result = (Boolean) Await.result(future, timeout.duration());
        } catch (Exception e) {
            Terminal.debugErro(e.getMessage());
        }

        return result;
    }

    /**
     * Send a message to the irc server and wait for an answer.
     *
     * @param message the message to send
     * @return the response
     */
    public String[] sendAndReceive(String message) {
        return sendAndReceive(message, Settings.expectedLines());
    }

    /**
     * Send a message to the irc server and wait for an answer.
     *
     * @param message the message to send
     * @param expectedLines the expected response lines
     * @return the response
     */
    public String[] sendAndReceive(String message, int expectedLines) {
        return sendAndReceive(message, expectedLines, true);
    }

    /**
     * Send a message to the irc server and wait for an answer.
     *
     * @param message the message to send
     * @param expectedLines the expected response lines
     * @param sendLast on error send last received lines?
     * @return the response
     */
    public String[] sendAndReceive(String message, int expectedLines, boolean sendLast) {
        if (!isConnected()) connect();
        lastResponse = "";
        lastLines = new String[]{ "" };

        if (isConnected()) {
            connection.tell("clear", ActorRef.noSender());

            Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(connection, Ask.create(message, expectedLines), timeout);

            try {
                lastResponse = (String) Await.result(future, timeout.duration());
                lastLines = lastResponse.split(Settings.delimiter());
            } catch (Exception exception) {
                if (sendLast && expectedLines > 0) {
                    Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
                    Future<Object> f = Patterns.ask(connection, "last", timeout);

                    try {
                        lastResponse = (String) Await.result(f, t.duration());
                        lastLines = lastResponse.split(Settings.delimiter());
                    } catch (Exception e) {
                        Terminal.debugErro(e.getMessage());
                        Terminal.printError(lastResponse, Thread.currentThread().getStackTrace()[3].getMethodName());
                    }

                    Terminal.debugErro(exception.getMessage());
                    Terminal.printError(lastResponse, Thread.currentThread().getStackTrace()[3].getMethodName());
                }
            }

            return lastLines;
        }

        throw new RuntimeException("Client not connected");
    }

    /**
     * Send a list of messages to the irc server and wait for an answer.
     *
     * @param messages the message to send
     * @param expectedLines the expected response lines
     * @return the response
     */
    public String[] sendAndReceiveAll(List<String> messages, int expectedLines) {
        int index = 1;
        int lineCount = 0;

        for (String messsage : messages) {
            if (index == messages.size()) lineCount = expectedLines;
            sendAndReceive(messsage, lineCount);
            index++;
        }

        return lastLines;
    }

    /**
     * Send a message to the irc server.
     *
     * @param message the message to send
     */
    public void send(String message) {
        sendAndReceive(message, 0);
    }

    /**
     * Retrieves the last lines received by the irc server.
     * The last received lines are cleared before each new message.
     *
     * @return the last received lines
     */
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

    /**
     * Retrieves the last response which is stored inside the 'trash'.
     * 'Trashed' messages are messages that have not been requested
     *
     * @return the last trash line
     */
    public String trash() {
        String result = "";
        Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> f = Patterns.ask(connection, "trash", t);

        try {
            result = ((String) Await.result(f, t.duration())).split("\r\n")[0];
        } catch (Exception e) {
            if (Settings.debug()) {
                System.err.println(Template.get("DEBUG").single("message", e.getMessage()).render());
            }
        }

        if (Settings.devMode()) {
            Terminal.devLog(result);
        }

        return result;
    }

    /**
     * Create a new client.
     *
     * @param nickname the nickname
     * @param username the username
     * @param realname the fullname
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
        return new Client(preset.nickname(), preset.username(), preset.fullname());
    }

    /**
     * Represents a {@link Client} preset.
     * Is used for easily creating a new client.
     *
     * @author Ruben Maurer
     * @version 1.0
     * @since 1.0
     */
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

        /**
         * The clients nickname.
         */
        private String nickname;

        /**
         * Getter for the clients nickname.
         *
         * @return the nickname
         */
        public String nickname() {
            return nickname;
        }

        /**
         * The clients username.
         */
        private String username;

        /**
         * Getter for the clients username.
         *
         * @return the username
         */
        public String username() {
            return username;
        }

        /**
         * The clients fullname.
         */
        private String fullname;

        /**
         * Getter for the clients fullname.
         *
         * @return the fullname
         */
        public String fullname() {
            return fullname;
        }

        /**
         * Constructor for a new {@link Preset}.
         *
         * @param nickname the clients nickname
         * @param username the clients username
         * @param fullname the clients fullname
         */
        Preset(String nickname, String username, String fullname) {
            this.nickname = nickname;
            this.fullname = fullname;
            this.username = username;
        }
    }

    /**
     * Utilities for easy {@link Template} access.
     *
     * @author Ruben Maurer
     * @version 1.0
     * @since 1.0
     */
    public static class Utilities {

        /**
         * Get a NICK message for a client.
         *
         * @param client the client
         * @return the NICK message
         */
        public static String nick(Client client) {
            return Template.get("nick").single("nickname", client.nickname()).render();
        }

        /**
         * Get a USER message for a client.
         *
         * @param client the client
         * @return the USER message
         */
        public static String user(Client client) {
            return Template.get("user")
                    .single("username", client.username())
                    .single("fullname", client.fullname()).render();
        }

        /**
         * Get a list with a NICK and a USER message for a client.
         *
         * @param client the client
         * @return the list with the messages
         */
        public static List<String> auth(Client client) {
            List<String> lst = new ArrayList<>();
            lst.add(user(client));
            lst.add(nick(client));

            return lst;
        }

        /**
         * Get a PRIVMSG message addressed to a given target and with given message.
         *
         * @param target the target
         * @param message the message
         * @return the PRIVMSG message
         */
        public static String privateMessage(Client target, String message) {
            return Template.get("privmsg")
                    .single("nickname", target.nickname())
                    .single("message", message).render();
        }

        /**
         * Get a PRIVMSG message addressed to a channel and with given message.
         *
         * @param channel the channel
         * @param message the message
         * @return the PRIVMSG message
         */
        public static String privateMessage(String channel, String message) {
            return Template.get("privmsgchannel")
                    .single("channel", channel)
                    .single("message", message).render();
        }

        /**
         * Get a NOTICE message addressed to a given target and with given message.
         *
         * @param target the target
         * @param message the message
         * @return the NOTICE message
         */
        public static String notice(String target, String message) {
            return Template.get("notice")
                    .single("nickname", target)
                    .single("message", message).render();
        }

        /**
         * Get a NOTICE message addressed to a given target and with given message.
         *
         * @param target the target
         * @param message the message
         * @return the NOTICE message
         */
        public static String notice(Client target, String message) {
            return notice(target.nickname(), message);
        }

        /**
         * Get a QUIT message with a message.
         *
         * @param message the message
         * @return the QUIT message
         */
        public static String quit(String message) {
            return Template.get("quit").single("message", message).render();
        }

        /**
         * Get a JOIN message to join a channel.
         *
         * @param channel the channel
         * @return the JOIN message
         */
        public static String joinChannel(String channel) {
            return Template.get("join").single("channel", channel).render();
        }

        /**
         * Get a WHOIS message for a client.
         *
         * @param user the nickname
         * @return the WHOIS message
         */
        public static String whoIs(String user) {
            return Template.get("whois").single("nickname", user).render();
        }

        /**
         * Get a PART message for a channel and with a message.
         *
         * @param channel the channel
         * @param message the message
         * @return the PART message
         */
        public static String part(String channel, String message) {
            return Template.get("part").single("channel", channel).single("message", message).render();
        }

        /**
         * Get a TOPIC message for setting the topic of a channel.
         *
         * @param channel the channel
         * @param topic the new channel topic
         * @return the TOPIC message
         */
        public static String setTopic(String channel, String topic) {
            return Template.get("topic_set").single("channel", channel).single("topic", topic).render();
        }

        /**
         * Get a TOPIC message for getting the current topic of a channel.
         *
         * @param channel the channel
         * @return the TOPIC message
         */
        public static String getTopic(String channel) {
            return Template.get("topic_get").single("channel", channel).render();
        }

        /**
         * Get a LIST message.
         *
         * @return the LIST message
         */
        public static String list() {
            return "LIST";
        }

        /**
         * Get a LIST message for a channel.
         *
         * @param channel the channel
         * @return the LIST message
         */
        public static String list(String channel) {
            return String.format("%s #%s", list(), channel);
        }
    }
}
