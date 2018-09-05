package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Class for managing all connections to the irc server.
 *
 * @author Ruben Maurer
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

    public static Props props(String host, int port) {
        return Props.create(ConnectionManager.class, host, port);
    }
}
