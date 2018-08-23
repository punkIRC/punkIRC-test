package de.rubenmaurer.punk.test.ping;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Pong extends BaseTest {

    @Test
    void testPong() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("PONG", 0);
        }

        assertTrue(Evaluation.noReply(c));
    }
}
