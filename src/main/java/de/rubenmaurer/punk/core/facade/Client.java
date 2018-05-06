package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.pattern.Patterns;
import akka.util.Timeout;
import de.rubenmaurer.punk.core.util.Ask;
import de.rubenmaurer.punk.core.util.Settings;
import scala.concurrent.Await;
import scala.concurrent.Future;

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

    private String lastResponse;

    public String lastResponse() {
        return lastResponse;
    }

    private ActorRef connection;

    static ActorRef connectionManager;

    private Client(String nickname, String username, String realname) throws Exception {
        this.nickname = nickname;
        this.username = username;
        this.realname = realname;

        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connectionManager, "connection-request", timeout);
        this.connection = (ActorRef) Await.result(future, timeout.duration());
    }

    public Boolean connect() throws Exception {
        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connect", timeout);

        return (Boolean) Await.result(future, timeout.duration());
    }

    public void disconnect() {
        this.connection.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }

    public boolean isConnected() throws Exception {
        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, "connected", timeout);

        return (Boolean) Await.result(future, timeout.duration());
    }

    public String sendAndReceive(String message) throws Exception {
        return sendAndReceive(message, Settings.defaultExpectedLineCount);
    }

    public String sendAndReceive(String message, int expectedLines) throws Exception {
        Timeout timeout = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(connection, Ask.create(message, expectedLines), timeout);

        try {
            lastResponse = (String) Await.result(future, timeout.duration());
        } catch (Exception exception) {
            Timeout t = new Timeout(Settings.defaultTimeout, TimeUnit.SECONDS);
            Future<Object> f = Patterns.ask(connection, "last", timeout);
            lastResponse = (String) Await.result(f, t.duration());

            System.err.println(lastResponse);
        }

        return lastResponse;
    }

    public static Client create(String nickname, String username, String realname) throws Exception {
        return new Client(nickname, username, realname);
    }
}
