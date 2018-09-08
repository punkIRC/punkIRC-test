package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class QuitConnection extends BaseTest {

    @Test
    void quitAfterRegistration1() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        String msg = "Goodbye!";
        if (Session.serverIsAlive()) {
            c.sendAndReceiveAll(Utilities.auth(c), Settings.authLines());
            c.sendAndReceive(Utilities.quit(msg), 1);
        }

        Evaluation.quit(c, msg);
    }

    @Test
    void quitAfterRegistration2() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        String msg = "Goodbye!";
        if (Session.serverIsAlive()) {
            c.sendAndReceiveAll(Utilities.auth(c), Settings.authLines());
            c.sendAndReceive(Utilities.quit(msg), 1);
        }

        Evaluation.quit(c, msg);
        assertFalse(c.isConnected());
    }

    @Test
    void quitAfterRegistration3() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.sendAndReceiveAll(Utilities.auth(c), Settings.authLines());
            c.sendAndReceive("QUIT", 1);
        }

        Evaluation.quit(c, "Client Quit");
        assertFalse(c.isConnected());
    }

    @Test
    void quitAfterRegistration4() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceiveAll(Utilities.auth(c1), Settings.authLines());
            c2.sendAndReceiveAll(Utilities.auth(c2), Settings.authLines());

            c1.sendAndReceive(Utilities.quit("Goodbye!"), 1);
            c2.sendAndReceive(Utilities.quit("See ya!"), 1);
        }

        Evaluation.quit(c1, "Goodbye!");
        assertFalse(c1.isConnected());

        Evaluation.quit(c2, "See ya!");
        assertFalse(c2.isConnected());
    }
}
