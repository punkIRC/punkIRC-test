package de.rubenmaurer.punk.core.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;
import de.rubenmaurer.punk.core.util.Ask;

import java.net.InetSocketAddress;

public class ConnectionHandler extends AbstractActor {

    private InetSocketAddress remote;

    private ActorRef remoteActor;

    private ActorRef manager;

    private ActorRef questioner;

    private int expectedLines;

    private String response = "";

    public ConnectionHandler(String host, int port) {
        this.remote = new InetSocketAddress(host, port);
        this.manager = Tcp.get(getContext().getSystem()).getManager();
    }

    private void connect() {
        this.questioner = getSender();
        this.manager.tell(TcpMessage.connect(remote), getSelf());
    }

    private int getLineCount(String lines) {
        return lines.split("\r\n").length;
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(Ask.class, ask -> {
                    questioner = getSender();
                    expectedLines = ask.lineCount();

                    self().tell(ask.message(), self());
                })
                .matchEquals("connect", s -> connect())
                .matchEquals("connected", s -> getSender().tell(remoteActor != null, self()))
                .matchEquals("last", s -> getSender().tell(response, self()))
                .match(Tcp.Connected.class, s -> {
                    getSender().tell(TcpMessage.register(getSelf()), getSelf());
                    this.remoteActor = getSender();
                    this.questioner.tell(true, self());
                })
                .match(String.class, msg -> {
                    if (this.remoteActor == null) {
                        self().tell(msg, getSender());
                        return;
                    }

                    this.remoteActor.tell(TcpMessage.write(ByteString.fromString(msg)), sender());
                })
                .match(Tcp.ConnectionClosed.class, msg -> getContext().stop(getSelf()))
                .match(Tcp.Received.class, msg -> {
                    response = response.concat(msg.data().decodeString("US-ASCII"));

                    if (getLineCount(response) == expectedLines) {
                        questioner.tell(response, self());
                        response = "";
                    }
                })
                .build();
    }

    static Props props(String host, int port) {
        return Props.create(ConnectionHandler.class, host, port);
    }
}
