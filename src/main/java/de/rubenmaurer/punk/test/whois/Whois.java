package de.rubenmaurer.punk.test.whois;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.messages.Template;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Whois extends BaseTest {

    @Test
    void whois() throws Exception {
        Client c1 = Client.create(ClientPreset.RACHEL);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.connect();
            c2.sendAndReceive(Template.get("whois").single("nickname", c1.nickname()).render(), 3);
        }

        assertTrue(Evaluation.replyWhoIs(c2, c1));
    }

    @Test
    void whoisNoNick() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("WHOIS herman", 3);
        }

        assertTrue(Evaluation.replyWhoIs(c, "herman"));
    }
}
