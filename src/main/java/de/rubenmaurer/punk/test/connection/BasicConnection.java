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

        Evaluation.welcome(s);
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

        Evaluation.welcome(s);
    }

    @Test
    void noUnexpectedWelcome1() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            assertEquals("", c.sendAndReceive(ClientUtils.nick(c), 0)[0]);
        }
    }

    @Test
    void noUnexpectedWelcome2() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            assertEquals("", c.sendAndReceive(ClientUtils.user(c), 0)[0]);
        }
    }

    @Test
    void noUnexpectedWelcome3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            String dmgAuth = ClientUtils.auth(c).get(0);

            assertEquals("", c.sendAndReceive(dmgAuth.substring(0, dmgAuth.length() - 4), 0)[0]);
        }
    }

    @Test
    void noUnexpectedWelcome4() throws Exception {
        Client max = Client.create(Preset.MAX);
        Client chloe = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            assertEquals("", chloe.sendAndReceive(ClientUtils.nick(chloe))[0]);
            assertEquals("", max.sendAndReceive(ClientUtils.nick(max))[0]);
        }
    }

    @Test
    void noUnexpectedWelcome5() throws Exception {
        Client max = Client.create(Preset.MAX);
        Client chloe = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            assertEquals("", chloe.sendAndReceive(ClientUtils.user(chloe))[0]);
            assertEquals("", max.sendAndReceive(ClientUtils.user(max))[0]);
        }
    }
}
