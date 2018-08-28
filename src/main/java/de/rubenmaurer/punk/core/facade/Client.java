package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.rubenmaurer.punk.core.util.Ask;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.core.util.Terminal;
import scala.concurrent.Await;
import scala.concurrent.Future;

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
     * The Hostname.
     */
    private String hostname;

    /**
     * Hostname string.
     *
     * @return the string
     */
    public String hostname() {
        return hostname;
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
        return lastResponse;
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
     * @throws Exception the exception
     */
    public boolean isConnected() throws Exception {
        Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connected", timeout);

        return (Boolean) Await.result(future, timeout.duration());
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
        if (!isConnected()) connect();
        lastLines = new String[]{ "" };

        if (isConnected()) {
            Timeout timeout = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(connection, Ask.create(message, expectedLines), timeout);

            try {
                lastResponse = (String) Await.result(future, timeout.duration());
                lastLines = lastResponse.split(Settings.delimiter());
            } catch (Exception exception) {
                Timeout t = new Timeout(Settings.timeout(), TimeUnit.SECONDS);
                Future<Object> f = Patterns.ask(connection, "last", timeout);

                lastResponse = (String) Await.result(f, t.duration());
                lastLines = lastResponse.split(Settings.delimiter());

                Terminal.printError(lastResponse, Thread.currentThread().getStackTrace()[3].getMethodName());
            }

            return lastLines;
        }

        throw new Exception("Client not connected");
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
    public static Client create(ClientPreset preset) throws Exception {
        return new Client(preset.nickname(), preset.username(), preset.realname());
    }
}
