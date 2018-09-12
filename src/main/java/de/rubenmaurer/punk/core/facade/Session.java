package de.rubenmaurer.punk.core.facade;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import de.rubenmaurer.punk.core.akka.ConnectionManager;
import de.rubenmaurer.punk.util.Template;

/**
 * Representing a single test session.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Session {

    /**
     * Session status
     */
    private static boolean initiated = false;

    /**
     * Is session initiated?
     *
     * @return is initiated?
     */
    public static boolean isInitiated() {
        return initiated;
    }

    /**
     * Instantiates a new Session.
     *
     * @param hostname   the hostname
     * @param port       the port
     * @param executable the executable
     */
    private Session(String hostname, int port, String executable) {
        if (!initiated) {
            Server.create(executable);
            Config cfg = ConfigFactory.load("resources/application.conf");
            Client.connectionManager = ActorSystem.apply("pricefield", cfg).actorOf(ConnectionManager.props(hostname, port), "de.rubenmaurer.punk.test.connection-manager");

            initiated = true;
        }
    }

    /**
     * Initiate a session.
     *
     * @param hostname the servers executable
     * @param port the port
     * @param executable the hostname
     */
    public static void initiate(String hostname, int port, String executable) {
        new Session(hostname, port, executable);
    }

    /**
     * Terminate the session.
     */
    public static boolean terminate() {
        Session.initiated = false;

        return Session.stopServer();
    }

    /**
     * Start the server.
     *
     * @return server started?
     */
    public static boolean startServer() {
        return Server.start();
    }

    /**
     * Stop the server.
     *
     * @return server stopped?
     */
    public static boolean stopServer() {
        if (!Server.stop()) {
            System.err.println(Template.get("UNABLE_TO_STOP_SERVER").render());
            return false;
        }

        return true;
    }

    /**
     * Is the server running?
     *
     * @return server running?
     */
    public static boolean serverIsAlive() {
        return Server.isAlive();
    }
}
