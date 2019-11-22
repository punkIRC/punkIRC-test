package de.rubenmaurer.punk.test.whois;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class Whois extends BaseTest {

    @Test
    void whois() throws Exception {
        Client c1 = Client.create(Preset.RACHEL);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();

            c2.sendAndReceive(Client.Utilities.whoIs(c1.nickname()), 3, false);
        }

        Evaluation.whois(c2, c1);
    }

    @Test
    void whoisNoNick() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("WHOIS herman", 1);
        }

        Evaluation.noSuchNick(c, "herman");
    }
}
