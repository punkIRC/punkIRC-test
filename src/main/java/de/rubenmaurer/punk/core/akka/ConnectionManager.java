package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ConnectionManager extends AbstractActor {

    private String host;

    private int port;

    public ConnectionManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private ActorRef createClient() {
        return context().actorOf(ConnectionHandler.props(host, port));
    }

    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("connection-request", s -> sender().tell(createClient(), self()))
                .build();
    }

    public static Props props(String host, int port) {
        return Props.create(ConnectionManager.class, host, port);
    }
}
