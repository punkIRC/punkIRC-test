package de.rubenmaurer.punk.test.unknown;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class Unknown extends BaseTest {

    @Test
    void testUnknown1() throws Exception {
        Client c = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("VERSION", 1);
        }

        Evaluation.unknown(c, "VERSION");
    }

    @Test
    void testUnknown2() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("WHOWAS", 1);
        }

        Evaluation.unknown(c, "WHOWAS");
    }

    @Test
    void testUnknown3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("KILL someone", 1);
        }

        Evaluation.unknown(c, "KILL");
    }
}
