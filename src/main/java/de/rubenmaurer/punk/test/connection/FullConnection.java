package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FullConnection extends BaseTest {

    @Test
    void connectFull1() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void connectFull2() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.user(c), 0);
            c.sendAndReceive(ClientUtils.nick(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void connectFull3() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }
}
