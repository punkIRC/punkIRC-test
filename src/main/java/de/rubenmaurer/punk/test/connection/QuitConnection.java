package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuitConnection extends BaseTest {

    @Test
    void quitAfterRegistration1() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
            c.sendAndReceive(ClientUtils.quit("Goodbye!"), 1);
        }

        assertTrue(Evaluation.replyQuit(c));
    }

    @Test
    void quitAfterRegistration2() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
            c.sendAndReceive(ClientUtils.quit("Goodbye!"), 1);
        }

        assertTrue(Evaluation.replyQuit(c));
        assertFalse(c.isConnected());
    }

    @Test
    void quitAfterRegistration3() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.auth(c), 4);
            c.sendAndReceive("QUIT", 1);
        }

        assertTrue(Evaluation.replyQuit(c));
        assertFalse(c.isConnected());
    }

    @Test
    void quitAfterRegistration4() throws Exception {
        Client c1 = Client.create(ClientPreset.MAX);
        Client c2 = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.auth(c1), 4);
            c2.sendAndReceive(ClientUtils.auth(c2), 4);

            c1.sendAndReceive(ClientUtils.quit("Goodbye!"), 1);
            c2.sendAndReceive(ClientUtils.quit("See ya!"), 1);
        }

        assertTrue(Evaluation.replyQuit(c1));
        assertFalse(c1.isConnected());

        assertTrue(Evaluation.replyQuit(c2));
        assertFalse(c2.isConnected());
    }
}
