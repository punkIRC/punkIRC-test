package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MultiUserConnection extends BaseTest {

    @Test
    void connectTwoUsers() throws Exception {
        Client c1 = Client.create(ClientPreset.SCHROTTY);
        Client c2 = Client.create(ClientPreset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);
        }

        assertTrue(Evaluation.replyWelcome(c1));
        assertTrue(Evaluation.replyWelcome(c2));
    }

    @Test
    void connectDuplicateNick() throws Exception {
        Client c1 = Client.create(ClientPreset.RACHEL);
        Client c2 = Client.create(ClientPreset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);
        }

        assertTrue(Evaluation.replyWelcome(c1));
        assertTrue(Evaluation.nicknameInUse(c2));
    }
}
