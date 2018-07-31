package de.rubenmaurer.punk.test.privmsg;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoticePrivmsg extends BaseTest {

    @Test
    void privmsg1() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);

            c1.send(ClientUtils.privateMessage(c2, "Hello"));
        }

        assertEquals("Hello", c2.lastResponse());
    }

    @Test
    void privmsg2() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);
        Client c3 = Client.create(ClientPreset.RACHEL);
        Client c4 = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);
            c3.sendAndReceive(ClientUtils.auth(c3), 4);
            c4.sendAndReceive(ClientUtils.auth(c4), 4);

            c1.send(ClientUtils.privateMessage(c2, "Hello 1"));
            c2.send(ClientUtils.privateMessage(c3, "Hello 2"));
            c3.send(ClientUtils.privateMessage(c4, "Hello 3"));
            c4.send(ClientUtils.privateMessage(c1, "Hello 4"));
        }

        assertEquals("Hello 1", c1.lastResponse());
        assertEquals("Hello 2", c2.lastResponse());
        assertEquals("Hello 3", c3.lastResponse());
        assertEquals("Hello 4", c4.lastResponse());
    }

    @Test
    void privmsgNoNick() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c1.send(ClientUtils.privateMessage(c2, "Hello"));
        }

        assertTrue(Evaluation.noSuchNick(c1));
    }

    @Test
    void notice() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);

            c1.send(ClientUtils.notice(c2, "Hello"));
        }

        assertEquals("Hello", c2.lastResponse());
    }

    @Test
    void noticeNoNick() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);

            c1.sendAndReceive(ClientUtils.notice(c2, "Hello"), 0);
        }

        assertTrue(Evaluation.noReply(c1));
    }
}
