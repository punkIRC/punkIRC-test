package de.rubenmaurer.punk.test.privmsg;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class NoticePrivmsg extends BaseTest {

    @Test
    void privmsg1() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();

            c1.send(Utilities.privateMessage(c2, "Hello"));
        }

        Evaluation.privateMessage(c1, c2, c2.nickname(), "Hello");
    }

    @Test
    void privmsg2() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);
        Client c3 = Client.create(Preset.RACHEL);
        Client c4 = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();
            c3.authenticate();
            c4.authenticate();

            c1.send(Utilities.privateMessage(c2, "Hello 1"));
            c2.send(Utilities.privateMessage(c3, "Hello 2"));
            c3.send(Utilities.privateMessage(c4, "Hello 3"));
        }

        Evaluation.privateMessage(c1, c2, c2.nickname(), "Hello 1");
        Evaluation.privateMessage(c2, c3, c3.nickname(), "Hello 2");
        Evaluation.privateMessage(c3, c4, c4.nickname(), "Hello 3");
    }

    @Test
    void privmsgNoNick() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c1.send(Utilities.privateMessage(c2, "Hello"));
        }

        Evaluation.noSuchNick(c1);
    }

    @Test
    void notice() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.sendAndReceiveAll(Utilities.auth(c2), 4);

            c1.send(Utilities.notice(c2, "Hello"));
        }

        Evaluation.notice(c1, c2, c2.nickname(), "Hello");
    }

    @Test
    void noticeNoNick() throws Exception {
        Client c1 = Client.create(Preset.MAX);
        Client c2 = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c1.authenticate();

            c1.sendAndReceive(Utilities.notice(c2, "Hello"));
        }

        Evaluation.empty(c1);
    }
}
