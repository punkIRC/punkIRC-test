package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.create;

public class JoinChannel extends BaseTest {

    @Test
    void testJoin1() throws Exception {
        Client c = create(Preset.MAX);

        String channel = "Arcadia-Dinner";
        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive(ClientUtils.joinChannel(channel), 3);
        }

        Evaluation.join(c, channel, c.nickname());
    }

    @Test
    void testJoin2() throws Exception {
        Client c = create(Preset.CHLOE);

        String channel = "Arcadia-Dinner";
        if (Session.serverIsAlive()) {
            c.authenticate();

            c.sendAndReceive(ClientUtils.joinChannel(channel), 3);
            Evaluation.join(c, channel, c.nickname());

            c.sendAndReceive(ClientUtils.joinChannel(channel), 3, false);
        }

        Evaluation.empty(c);
    }

    @Test
    void testJoin3() throws Exception {
        Client c1 = Client.create(Preset.SAMUEL);
        Client c2 = Client.create(Preset.MAX);
        Client c3 = Client.create(Preset.DAVID);
        Client c4 = Client.create(Preset.CHLOE);
        Client c5 = Client.create(Preset.JOYCE);

        String channel = "Blackhell";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);
            c3.authenticateAndJoin(channel);
            c4.authenticateAndJoin(channel);
            c5.authenticateAndJoin(channel);
        }

        Evaluation.join(c1, channel, c1.nickname());
        Evaluation.join(c2, channel, c1.nickname(), c2.nickname());
        Evaluation.join(c3, channel, c1.nickname(), c2.nickname(), c3.nickname());
        Evaluation.join(c4, channel, c1.nickname(), c2.nickname(), c3.nickname(), c4.nickname());
        Evaluation.join(c5, channel, c1.nickname(), c2.nickname(), c3.nickname(), c4.nickname(), c5.nickname());
    }

    @Test
    void testJoin4() throws Exception {
        Client c1 = Client.create(Preset.SAMUEL);
        Client c2 = Client.create(Preset.VICTORIA);

        String channel = "Dinner";
        if (Session.serverIsAlive()) {
            c1.authenticate();
            c1.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c1, channel, c1.nickname());

            c2.authenticate();
            c2.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c2, channel, c1.nickname(), c2.nickname());
        }

        Evaluation.channelRelay(c1, channel);
    }

    @Test
    void testJoin5() throws Exception {
        Client c1 = Client.create(Preset.DAVID);
        Client c2 = Client.create(Preset.VICTORIA);
        Client c3 = Client.create(Preset.CHLOE);

        String channel = "Dinner";
        if (Session.serverIsAlive()) {
            c1.authenticate();
            c1.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c1, channel, c1.nickname());

            c2.authenticate();
            c2.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c2, channel, c1.nickname(), c2.nickname());

            c3.authenticate();
            c3.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c3, channel, c1.nickname(), c2.nickname(), c3.nickname());
        }

        Evaluation.channelRelay(c1, channel);
        Evaluation.channelRelay(c2, channel);
    }
}
