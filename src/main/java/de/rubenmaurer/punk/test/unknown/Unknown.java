package de.rubenmaurer.punk.test.unknown;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Unknown extends BaseTest {

    @Test
    void testUnknown1() throws Exception {
        Client c = Client.create(ClientPreset.RACHEL);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("VERSION", 1);
        }

        assertTrue(Evaluation.replyUnknown(c));
    }

    @Test
    void testUnknown2() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("WHOWAS", 1);
        }

        assertTrue(Evaluation.replyUnknown(c));
    }

    @Test
    void testUnknown3() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("KILL someone", 1);
        }

        assertTrue(Evaluation.replyUnknown(c));
    }
}
