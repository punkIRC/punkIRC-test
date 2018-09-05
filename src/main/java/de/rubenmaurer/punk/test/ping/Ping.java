package de.rubenmaurer.punk.test.ping;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class Ping extends BaseTest {

    @Test
    void testPing() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("PING", 1);
        }

        Evaluation.ping(c);
    }

    @Test
    void testMultiPing() throws Exception {
        Client c1 = Client.create(Preset.SCHROTTY);
        Client c2 = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();

            c1.sendAndReceive("PING", 1);
            c2.sendAndReceive("PING", 1);
        }

        Evaluation.ping(c1);
        Evaluation.ping(c2);
    }
}
