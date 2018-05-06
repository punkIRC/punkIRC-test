package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorSystem;
import de.rubenmaurer.punk.core.akka.ConnectionManager;

import java.util.LinkedList;
import java.util.List;

public class Session {

    private static List<Client> clients = new LinkedList<>();

    private Session(String hostname, int port, String executable) {
        Server.create(executable);
        Client.connectionManager = ActorSystem.apply("pricefield").actorOf(ConnectionManager.props(hostname, port), "connection-manager");
    }

    public static void initiate(String executable) {
        initiate("localhost", 6667, executable);
    }

    public static void initiate(String hostname, int port, String executable) {
        new Session(hostname, port, executable);
    }

    public static boolean startServer() {
        return Server.start();
    }

    public static boolean stopServer() {
        return Server.stop();
    }

    public static boolean serverIsAlive() {
        return Server.isAlive();
    }

    public static void createClient(String nickname, String username, String realname) {
        try {
            clients.add(Client.create(nickname, username, realname));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}
