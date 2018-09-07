package de.rubenmaurer.punk.test.connection;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicConnection extends BaseTest {

    @Test
    void simpleConnect1() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.nick(c));
            c.sendAndReceive(ClientUtils.user(c), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void simpleConnect2() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.user(c));
            c.sendAndReceive(ClientUtils.nick(c), Settings.authLines());
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
            c.send(ClientUtils.nick(c));
            m.send(ClientUtils.nick(m));
            s.send(ClientUtils.nick(s));
            s.send(ClientUtils.user(s));
        }

        Evaluation.welcome(s, 1, 2);
    }

    @Test
    void nickUserParsing5() throws Exception {
        Client c = Client.create(Preset.CHLOE);
        Client m = Client.create(Preset.MAX);
        Client s = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(ClientUtils.user(c));
            m.send(ClientUtils.user(m));
            s.send(ClientUtils.user(s));
            s.send(ClientUtils.nick(s));
        }

        Evaluation.welcome(s, 1, 2);
    }

    @Test
    void noUnexpectedWelcome1() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.nick(c), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome2() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.user(c), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            String dmgAuth = ClientUtils.auth(c).get(0);
            c.sendAndReceive(dmgAuth.substring(0, dmgAuth.length() - 4), 0);

            Evaluation.empty(c);
        }
    }

    @Test
    void noUnexpectedWelcome4() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.nick(c1), 0);
            c2.sendAndReceive(ClientUtils.nick(c2), 0);

            Evaluation.empty(c1);
            Evaluation.empty(c2);
        }
    }

    @Test
    void noUnexpectedWelcome5() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.sendAndReceive(ClientUtils.user(c1), 0);
            c2.sendAndReceive(ClientUtils.user(c2), 0);

            Evaluation.empty(c1);
            Evaluation.empty(c2);
        }
    }
}
