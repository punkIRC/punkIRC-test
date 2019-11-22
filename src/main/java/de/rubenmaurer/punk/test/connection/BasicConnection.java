package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class BasicConnection extends BaseTest {

    @Test
    void simpleConnect1() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(Utilities.nick(c));
            c.sendAndReceive(Utilities.user(c), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void simpleConnect2() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(Utilities.user(c));
            c.sendAndReceive(Utilities.nick(c), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void completeAuthAtOnce() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.welcome(c);
    }

    @Test
    void nickUserParsing1() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.welcome(c);
    }

    @Test
    void nickUserParsing2() throws Exception {
        Client c = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.welcome(c);
    }

    @Test
    void nickUserParsing3() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.welcome(c);
    }

    @Test
    void nickUserParsing4() throws Exception {
        Client c = Client.create(Preset.CHLOE);
        Client m = Client.create(Preset.MAX);
        Client s = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(Utilities.nick(c));
            m.send(Utilities.nick(m));
            s.send(Utilities.nick(s));
            s.send(Utilities.user(s));
        }

        Evaluation.welcome(s, 1, 2);
    }

    @Test
    void nickUserParsing5() throws Exception {
        Client c = Client.create(Preset.CHLOE);
        Client m = Client.create(Preset.MAX);
        Client s = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(Utilities.user(c));
            m.send(Utilities.user(m));
            s.send(Utilities.user(s));
            s.send(Utilities.nick(s));
        }

        Evaluation.welcome(s, 1, 2);
    }

    @Test
    void noUnexpectedWelcome1() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(Utilities.nick(c), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome2() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(Utilities.user(c), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            String dmgAuth = Utilities.auth(c).get(0);
            c.sendAndReceive(dmgAuth.substring(0, dmgAuth.length() - 4), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome4() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(Utilities.nick(c1), 0);
            c2.sendAndReceive(Utilities.nick(c2), 0);

            Evaluation.empty(c1);
            Evaluation.empty(c2);
        }
    }

    @Test
    void noUnexpectedWelcome5() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(Utilities.user(c1), 0);
            c2.sendAndReceive(Utilities.user(c2), 0);

            Evaluation.empty(c1);
            Evaluation.empty(c2);
        }
    }
}
