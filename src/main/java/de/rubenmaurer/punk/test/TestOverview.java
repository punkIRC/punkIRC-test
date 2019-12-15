package de.rubenmaurer.punk.test;

import de.rubenmaurer.punk.test.ping.*;
import de.rubenmaurer.punk.test.channel.*;
import de.rubenmaurer.punk.test.connection.*;
import de.rubenmaurer.punk.test.motd.Motd;

import de.rubenmaurer.punk.test.privmsg.NoticePrivmsg;
import de.rubenmaurer.punk.test.robustness.Robustness;
import de.rubenmaurer.punk.test.unknown.Unknown;
import de.rubenmaurer.punk.test.whois.Whois;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestOverview {
    public static final List<Class> CLASSES = Stream.of(
            AssignmentChannel.class,
            JoinChannel.class,
            ListChannel.class,
            PartChannel.class,
            PrivateMessageChannel.class,
            TopicChannel.class,
            WhoChannel.class,
            BasicConnection.class,
            FullConnection.class,
            MultiUserConnection.class,
            QuitConnection.class,
            Motd.class,
            Ping.class,
            Pong.class,
            NoticePrivmsg.class,
            Robustness.class,
            Unknown.class,
            Whois.class
    ).collect(Collectors.toList());
}
