package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.*;

public class MultiUserConnection extends BaseTest {

    @Test
    void connectTwoUsers() throws Exception {
        Client c1 = create(Preset.SCHROTTY);
        Client c2 = create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();
        }

        Evaluation.welcome(c1);
        Evaluation.welcome(c2, 2, 0);
    }

    @Test
    void connectDuplicateNick() throws Exception {
        Client c1 = create(Preset.RACHEL);
        Client c2 = create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.sendAndReceiveAll(Utilities.auth(c2), 1);
        }

        Evaluation.welcome(c1);
        Evaluation.nicknameInUse(c2);
    }
}
