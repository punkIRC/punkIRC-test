package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.rubenmaurer.punk.core.util.Ask;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.Settings;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {

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

    private String hostname;

    public String hostname() {
        return hostname;
    }

    private String[] lastLines = new String[] {""};

    public String[] lastLines() {
        return lastLines;
    }

    private String lastResponse;

    public String lastResponse() {
        return lastResponse;
    }

    private ActorRef connection;

    static ActorRef connectionManager;

    private Client(String nickname, String username, String realname, String hostname) throws Exception {
        this.nickname = nickname;
        this.username = username;
        this.realname = realname;
        this.hostname = hostname;

        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connectionManager, "connection-request", timeout);
        this.connection = (ActorRef) Await.result(future, timeout.duration());
    }

    public Boolean connect() {
        boolean connected = false;
        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connect", timeout);

        try {
            connected = (Boolean) Await.result(future, timeout.duration());
        } catch (Exception ignore) {
        }

        return connected;
    }

    public void disconnect() {
        this.connection.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }

    private boolean isConnected() throws Exception {
        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connected", timeout);

        return (Boolean) Await.result(future, timeout.duration());
    }

    public String[] sendAndReceive(String message) throws Exception {
        return sendAndReceive(message, Settings.defaultExpectedLineCount);
    }

    public String[] sendAndReceive(String message, int expectedLines) throws Exception {
        if (!isConnected()) connect();
        lastLines = new String[]{ "" };

        if (isConnected()) {
            Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(connection, Ask.create(message, expectedLines), timeout);

            try {
                lastResponse = (String) Await.result(future, timeout.duration());
                lastLines = lastResponse.split(Settings.defaultResponseDelimiter);
            } catch (Exception exception) {
                Timeout t = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
                Future<Object> f = Patterns.ask(connection, "last", timeout);

                lastResponse = (String) Await.result(f, t.duration());
                lastLines = lastResponse.split(Settings.defaultResponseDelimiter);

                System.err.println(lastResponse);
            }

            return lastLines;
        }

        throw new Exception("Client not connected");
    }

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

    public void send(String message) throws Exception {
        sendAndReceive(message, 0);
    }

    public void sendAll(List<String> messages) throws Exception {
        for (String message : messages) {
            send(message);
        }
    }

    public static Client create(String nickname, String username, String realname, String hostname) throws Exception {
        return new Client(nickname, username, realname, hostname);
    }

    public static Client create(ClientPreset preset) throws Exception {
        return new Client(preset.nickname(), preset.username(), preset.realname(), preset.hostname());
    }
}
