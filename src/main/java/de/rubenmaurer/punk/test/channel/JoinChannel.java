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

        if (Session.serverIsAlive()) {
            c.authenticate();
            c.sendAndReceive(ClientUtils.joinChannel("Arcadia-Dinner"), 3);
        }

        Evaluation.join(c);
    }

    @Test
    void testJoin2() throws Exception {
        Client c = create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.authenticate();

            c.sendAndReceive(ClientUtils.joinChannel("Arcadia-Dinner"), 3);
            Evaluation.join(c);

            c.sendAndReceive(ClientUtils.joinChannel("Arcadia-Dinner"), 3, false);
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

        Evaluation.join(c1);
        Evaluation.join(c2);
        Evaluation.join(c3);
        Evaluation.join(c4);
        Evaluation.join(c5);
    }

    @Test
    void testJoin4() throws Exception {
        Client c1 = Client.create(Preset.SAMUEL);
        Client c2 = Client.create(Preset.VICTORIA);

        String channel = "Dinner";
        if (Session.serverIsAlive()) {
            c1.authenticate();
            c1.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c1);

            c2.authenticate();
            c2.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c2);
        }

        Evaluation.channelRelay(c1, c2);
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
            Evaluation.join(c1);

            c2.authenticate();
            c2.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c2);

            c3.authenticate();
            c3.sendAndReceive(ClientUtils.joinChannel(channel));
            Evaluation.join(c3);
        }

        Evaluation.channelRelay(c1, c2);
        Evaluation.channelRelay(c2, c3);
    }
}
