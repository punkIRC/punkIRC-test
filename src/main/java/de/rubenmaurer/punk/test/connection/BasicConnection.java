package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicConnection extends BaseTest {

    @Test
    void simpleConnect1() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.nick(c));
            c.sendAndReceive(ClientUtils.user(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void simpleConnect2() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.user(c));
            c.sendAndReceive(ClientUtils.nick(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void completeAuthAtOnce() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect1() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 1, 5);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect2() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 1, 25);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect3() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 1, 10);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect4() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 1, 38);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect5() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 3, 10);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect6() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 4, 12);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void partitionedConnect7() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        List<String> messages = ClientUtils.authPartitioned(c, 10, 5);
        c.sendAndReceiveAll(messages, 4);

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void nickUserParsing1() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void nickUserParsing2() throws Exception {
        Client c = Client.create(ClientPreset.RACHEL);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void nickUserParsing3() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void nickUserParsing4() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);
        Client m = Client.create(ClientPreset.MAX);
        Client s = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.nick(c));
            m.send(ClientUtils.nick(m));
            s.send(ClientUtils.nick(s));
            s.send(ClientUtils.user(s));
        }

        assertTrue(Evaluation.replyWelcome(s));
    }

    @Test
    void nickUserParsing5() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);
        Client m = Client.create(ClientPreset.MAX);
        Client s = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.user(c));
            m.send(ClientUtils.user(m));
            s.send(ClientUtils.user(s));
            s.send(ClientUtils.nick(s));
        }

        assertTrue(Evaluation.replyWelcome(s));
    }

    @Test
    void noUnexpectedWelcome1() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.nick(c));
        }

        assertTrue(Evaluation.noReply(c));
    }

    @Test
    void noUnexpectedWelcome2() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.user(c));
        }

        assertTrue(Evaluation.noReply(c));
    }

    @Test
    void noUnexpectedWelcome3() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            String dmgAuth = ClientUtils.auth(c);
            c.send(dmgAuth.substring(0, dmgAuth.length() - 4));
        }

        assertTrue(Evaluation.noReply(c));
    }

    @Test
    void noUnexpectedWelcome4() throws Exception {
        Client max = Client.create(ClientPreset.MAX);
        Client chloe = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            chloe.send(ClientUtils.nick(chloe));
            max.send(ClientUtils.nick(max));
        }

        assertTrue(Evaluation.noReply(chloe) && Evaluation.noReply(max));
    }

    @Test
    void noUnexpectedWelcome5() throws Exception {
        Client max = Client.create(ClientPreset.MAX);
        Client chloe = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            chloe.send(ClientUtils.user(chloe));
            max.send(ClientUtils.user(max));
        }

        assertTrue(Evaluation.noReply(chloe) && Evaluation.noReply(max));
    }
}
