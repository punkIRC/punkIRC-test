package de.rubenmaurer.punk.test.ping;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ping extends BaseTest {

    @Test
    void testPing() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("PING", 1);
        }

        assertTrue(Evaluation.replyPing(c));
    }

    @Test
    void testMultiping() throws Exception {
        Client c1 = Client.create(ClientPreset.SCHROTTY);
        Client c2 = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive("PING", 1);
            c2.sendAndReceive("PING", 1);
        }

        assertTrue(Evaluation.replyPing(c1));
        assertTrue(Evaluation.replyPing(c2));
    }
}
