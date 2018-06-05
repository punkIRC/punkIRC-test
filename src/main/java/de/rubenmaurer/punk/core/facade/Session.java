package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorSystem;
import de.rubenmaurer.punk.core.akka.ConnectionManager;

public class Session {

    private static boolean initiated = false;

    public static boolean isInitiated() {
        return initiated;
    }

    private Session(String hostname, int port, String executable) {
        if (!initiated) {
            Server.create(executable);
            Client.connectionManager = ActorSystem.apply("pricefield").actorOf(ConnectionManager.props(hostname, port), "de.rubenmaurer.punk.test.connection-manager");
            initiated = true;
        }
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
}
