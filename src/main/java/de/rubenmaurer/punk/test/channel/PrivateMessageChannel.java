package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.create;

public class PrivateMessageChannel extends BaseTest {

    @Test
    void joinAndMessage1() throws Exception {
        Client c1 = create(Client.Preset.CHLOE);
        Client c2 = create(Client.Preset.MAX);

        String channel = "Lighthouse";
        String message = "I love you";

        c1.authenticateAndJoin(channel);
        c2.authenticateAndJoin(channel);

        c1.send(Utilities.privateMessage(channel, message));
        Evaluation.privateMessage(c1, c2, channel, message, true);
    }

    @Test
    void joinAndMessage2() throws Exception {
        Client c1 = create(Client.Preset.JOYCE);
        Client c2 = create(Client.Preset.MAX);
        Client c3 = create(Client.Preset.DAVID);
        Client c4 = create(Client.Preset.CHLOE);

        String channel = "Price-House";
        String[] msgs = new String[] { "Hello there", "Howdy Dowdy", "Good Morning", "General Kenobi" };

        c1.authenticateAndJoin(channel);
        c2.authenticateAndJoin(channel);
        c3.authenticateAndJoin(channel);
        c4.authenticateAndJoin(channel);

        c1.send(Utilities.privateMessage(channel, msgs[0]));
        Evaluation.privateMessage(c1, c2, channel, msgs[0], true);

        c2.send(Utilities.privateMessage(channel, msgs[1]));
        Evaluation.privateMessage(c2, c3, channel, msgs[1], true);

        c3.send(Utilities.privateMessage(channel, msgs[2]));
        Evaluation.privateMessage(c3, c4, channel, msgs[2], true);

        c4.send(Utilities.privateMessage(channel, msgs[3]));
        Evaluation.privateMessage(c4, c1, channel, msgs[3], true);
    }

    @Test
    void joinAndMessageNoChannel() throws Exception {
        Client c1 = create(Client.Preset.CHLOE);

        String channel = "Lighthouse";
        c1.authenticate();

        c1.send(Utilities.privateMessage(channel, "I love you"));
        Evaluation.noSuchChannel(c1, channel);
    }

    @Test
    void joinAndNoticeNoChannel() throws Exception {
        Client c = create(Client.Preset.RACHEL);

        String channel = "Lighthouse";
        c.authenticate();

        c.send(Utilities.notice(channel, "I love you"));
        Evaluation.empty(c);
    }

    @Test
    void joinAndMessageNotOnChannel() throws Exception {
        Client c1 = create(Client.Preset.CHLOE);
        Client c2 = create(Client.Preset.MAX);

        String channel = "Lighthouse";
        c1.authenticateAndJoin(channel);
        c2.authenticate();

        c2.send(Utilities.privateMessage(channel, "I love you"));
        Evaluation.cannotSendToChannel(c2, channel);
    }
}
