package de.rubenmaurer.punk.test.motd;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;

public class Motd extends BaseTest {

    @Test
    void connectMotdLusers1() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.luser(c);
    }

    @Test
    void connectMotdLusers2() throws Exception {
        Client c = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c.sendAndReceive(ClientUtils.user(c), 0);
            c.sendAndReceive(ClientUtils.nick(c), Settings.authLines());
        }

        Evaluation.luser(c);
    }

    @Test
    void connectMotdLusers3() throws Exception {
        Client c1 = Client.create(Preset.SCHROTTY);
        Client c2 = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();
        }

        Evaluation.luser(c1);
        Evaluation.luser(c2, 2);
    }

    @Test
    void connectMotdLusersUnknown1() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.connect();
            c2.authenticate();
        }

        Evaluation.luser(c2, 1, 1, 0);
    }

    @Test
    void connectMotdLusersUnknown2() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c2.sendAndReceive(ClientUtils.nick(c2), 0);
            c1.authenticate();
        }

        Evaluation.luser(c1, 1, 1, 0);
    }

    @Test
    void connectMotdLusersUnknown3() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);
        Client c3 = Client.create(Preset.SCHROTTY);
        Client c4 = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.connect();
            c2.connect();
            c3.connect();

            c4.authenticate();
        }

        Evaluation.luser(c4, 1, 3, 0);
    }

    @Test
    void lusers1() throws Exception {
        Client c = Client.create(Preset.MAX);

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive("LUSERS", 5);
        }

        Evaluation.luser(c);
    }

    @Test
    void lusers2() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.SCHROTTY);
        Client c3 = Client.create(Preset.CHLOE);
        Client c4 = Client.create(Preset.RACHEL);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();
            c3.authenticate();
            c4.authenticate();

            c1.sendAndReceive("LUSERS", 5);
        }

        Evaluation.luser(c1, 4, 0, 0);
    }

    @Test
    void lusersChannels1() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.SCHROTTY);
        Client c3 = Client.create(Preset.CHLOE);
        Client c4 = Client.create(Preset.RACHEL);
        Client c5 = Client.create(Preset.SAMUEL);
        Client c6 = Client.create(Preset.VICTORIA);
        Client c7 = Client.create(Preset.DAVID);
        Client c8 = Client.create(Preset.MARK);
        Client c9 = Client.create(Preset.KATE);

        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin("blackwell_art");
            c2.authenticateAndJoin("blackwell_art");
            c3.authenticateAndJoin("blackwell_art");
            c4.authenticateAndJoin("blackwell_science");
            c5.authenticateAndJoin("blackwell_science");
            c6.authenticateAndJoin("blackwell_science");
            c7.authenticateAndJoin("arcadia_dinner");
            c8.authenticateAndJoin("arcadia_dinner");
            c9.authenticateAndJoin("arcadia_dinner");

            c1.sendAndReceive("LUSERS", 5);
        }

        Evaluation.luser(c1, 9, 0, 3);
    }

    @Test
    void lusersChannels2() throws Exception {
        Client c1 = Client.create(Preset.JOYCE);
        Client c2 = Client.create(Preset.SCHROTTY);
        Client c3 = Client.create(Preset.CHLOE);
        Client c4 = Client.create(Preset.VICTORIA);
        Client c5 = Client.create(Preset.DAVID);
        Client c6 = Client.create(Preset.MARK);
        Client c7 = Client.create(Preset.KATE);

        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin("blackwell_art");
            c2.authenticateAndJoin("blackwell_art");
            c3.authenticateAndJoin("blackwell_art");
            c4.authenticateAndJoin("blackwell_science");
            c5.authenticateAndJoin("blackwell_science");
            c6.authenticate();
            c7.authenticate();

            c3.sendAndReceive("LUSERS", 5);
        }

        Evaluation.luser(c3, 7, 0, 2);
    }

    @Test
    void lusersChannels3() throws Exception {
        Client c1 = Client.create(Preset.JOYCE);
        Client c2 = Client.create(Preset.MAX);
        Client c3 = Client.create(Preset.SAMUEL);
        Client c4 = Client.create(Preset.VICTORIA);
        Client c5 = Client.create(Preset.DAVID);
        Client c6 = Client.create(Preset.SCHROTTY);
        Client c7 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin("blackwell_principal");
            c2.authenticateAndJoin("blackwell_art");
            c3.authenticateAndJoin("blackwell_art");
            c4.authenticateAndJoin("blackwell_hallway");
            c5.authenticateAndJoin("blackwell_principal");
            c6.authenticate();
            c7.authenticate();

            c5.sendAndReceive("LUSERS", 5);
        }

        Evaluation.luser(c5, 7, 0, 3);
    }

    @Test
    void motd1() throws Exception {
        Client c = Client.create(Preset.MAX);

        String msg = "General Kenobi!";
        PrintWriter pw = new PrintWriter("motd.txt", "UTF-8");
        pw.println(msg);
        pw.close();

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.motd(c, msg);
    }

    @Test
    void motd2() throws Exception {
        Client c = Client.create(Preset.MAX);

        File f = new File("motd.txt");
        if (f.isFile()) f.delete();

        if (Session.serverIsAlive()) {
            c.authenticate();
        }

        Evaluation.noMotd(c);
    }
}
