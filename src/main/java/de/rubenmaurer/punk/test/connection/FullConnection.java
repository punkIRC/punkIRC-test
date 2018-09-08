package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class FullConnection extends BaseTest {

    @Test
    void connectFull1() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.welcome(c);
    }

    @Test
    void connectFull2() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(Utilities.user(c), 0);
            c.sendAndReceive(Utilities.nick(c), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void connectFull3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceiveAll(Utilities.auth(c), Settings.authLines());
        }

        Evaluation.welcome(c);
    }
}
