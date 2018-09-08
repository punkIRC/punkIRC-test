package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Class for managing all connections to the irc server.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class ConnectionManager extends AbstractActor {

    /**
     * The hostname
     */
    private String host;

    /**
     * The port
     */
    private int port;

    /**
     * Instantiates a new Connection manager.
     *
     * @param host the host
     * @param port the port
     */
    public ConnectionManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Spawn a new connection handler.
     *
     * @return the connection handler
     */
    private ActorRef createClient() {
        return context().actorOf(ConnectionHandler.props(host, port));
    }

    /**
     * Handles incoming messages.
     *
     * @return a receive object
     */
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("connection-request", s -> sender().tell(createClient(), self()))
                .build();
    }

    /**
     * Get the props for spawning a new actor.
     *
     * @param host the servers hostname
     * @param port the servers port
     * @return the properties
     */
    public static Props props(String host, int port) {
        return Props.create(ConnectionManager.class, host, port);
    }
}
