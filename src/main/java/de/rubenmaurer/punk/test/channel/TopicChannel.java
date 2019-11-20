package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.*;
import static de.rubenmaurer.punk.core.facade.Client.Preset.*;

public class TopicChannel extends BaseTest {

    @Test
    void topic1() throws Exception {
        Client c = create(Preset.KATE);

        String channel = "Blackwell-Dorms";
        String topic = "Hello there!";
        if (Session.serverIsAlive()) {
            c.authenticateAndJoin(channel);

            c.sendAndReceive(Utilities.setTopic(channel, topic));
        }

        Evaluation.setTopic(c, channel, topic);
    }

    @Test
    void topic2() throws Exception {
        Client c = create(Preset.MAX);

        String channel = "Blackwell-Art";
        String topic = "Photography rules!";
        if (Session.serverIsAlive()) {
            c.authenticateAndJoin(channel);

            c.send(Utilities.setTopic(channel, topic));
            c.sendAndReceive(Utilities.getTopic(channel));
        }

        Evaluation.getTopic(c, channel, topic);
    }

    @Test
    void topic3() throws Exception {
        Client c = create(Preset.CHLOE);

        String channel = "Blackwell-Art";
        if (Session.serverIsAlive()) {
            c.authenticateAndJoin(channel);

            c.sendAndReceive(Utilities.getTopic(channel));
        }

        Evaluation.getTopic(c, channel, "");
    }

    @Test
    void topic4() throws Exception {
        Client c = Client.create(SCHROTTY);

        String channel = "Blackwell-Dorms";
        if (Session.serverIsAlive()) {
            c.authenticate();

            c.sendAndReceive(Utilities.getTopic(channel));
        }

        Evaluation.notOnChannel(c, channel);
    }

    @Test
    void topic5() throws Exception {
        Client c = Client.create(DAVID);

        String channel = "Blackwell-Dorms";
        if (Session.serverIsAlive()) {
            c.authenticate();

            c.sendAndReceive(Utilities.setTopic(channel, "Those damn students!"));
        }

        Evaluation.notOnChannel(c, channel);
    }

    @Test
    void topic6() throws Exception {
        Client c1 = Client.create(DAVID);
        Client c2 = Client.create(JOYCE);

        String channel = "Blackwell-Dorms";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticate();

            c1.sendAndReceive(Utilities.setTopic(channel, "Those damn students!"));
            c2.sendAndReceive(Utilities.getTopic(channel));
        }

        Evaluation.notOnChannel(c2, channel);
    }

    @Test
    void topic7() throws Exception {
        Client c1 = Client.create(RACHEL);
        Client c2 = Client.create(MAX);

        String channel = "Blackwell-Hallway";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticate();

            c2.sendAndReceive(Utilities.getTopic(channel));
        }

        Evaluation.notOnChannel(c2, channel);
    }

    @Test
    void topic8() throws Exception {
        Client c1 = Client.create(RACHEL);
        Client c2 = Client.create(MAX);

        String channel = "Blackwell-Hallway";
        String topic = "All the marble";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c1.sendAndReceive(Utilities.setTopic(channel, topic));

            c2.authenticateAndJoin(channel);
        }

        Evaluation.joinAndTopic(c2, channel, topic, c1.nickname(), c2.nickname());
    }

    @Test
    void topic9() throws Exception {
        Client c1 = Client.create(RACHEL);
        Client c2 = Client.create(MAX);
        Client c3 = Client.create(DAVID);
        Client c4 = Client.create(VICTORIA);
        Client c5 = Client.create(SAMUEL);

        String channel = "Blackwell-Hallway";
        String topic = "All the marble";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c1.sendAndReceive(Utilities.setTopic(channel, topic));

            c2.authenticateAndJoin(channel);
            c3.authenticateAndJoin(channel);
            c4.authenticateAndJoin(channel);
            c5.authenticateAndJoin(channel);
        }

        Evaluation.joinAndTopic(c2, channel, topic, c1.nickname(), c2.nickname());
        Evaluation.joinAndTopic(c3, channel, topic, c1.nickname(), c2.nickname(), c3.nickname());
        Evaluation.joinAndTopic(c4, channel, topic, c1.nickname(), c2.nickname(), c3.nickname(), c4.nickname());
        Evaluation.joinAndTopic(c5, channel, topic, c1.nickname(), c2.nickname(), c3.nickname(), c4.nickname(), c5.nickname());
    }
}
