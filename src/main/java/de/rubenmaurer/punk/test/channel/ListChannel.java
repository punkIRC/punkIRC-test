package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.*;

public class ListChannel extends BaseTest {

    @Test
    void list1() throws Exception {
        Client c1 = create(Preset.KATE);
        Client c2 = create(Preset.MAX);
        Client c3 = create(Preset.CHLOE);

        String channel = "Arcadia";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);
            c3.authenticateAndJoin(channel);

            c1.sendAndReceive(Utilities.list(channel), 2);
        }

        Evaluation.list(c1, channel, "Burn", 3);
    }

    @Test
    void list2() throws Exception {
        Client c1 = create(Preset.KATE);
        Client c2 = create(Preset.MAX);
        Client c3 = create(Preset.VICTORIA);

        String channel = "Arcadia";
        String topic = "Let the world burn!";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);
            c3.authenticateAndJoin("Hell");

            c1.sendAndReceive(Utilities.setTopic(channel, topic));
            c1.sendAndReceive(Utilities.list(channel), 2);
        }

        Evaluation.list(c1, channel, topic, 2);
        Evaluation.list(c3, "Hell", "", 1);
    }

    @Test
    void list3() throws Exception {
        Client c1 = create(Preset.DAVID);
        Client c2 = create(Preset.JOYCE);
        Client c3 = create(Preset.SAMUEL);

        String channel = "Adults";
        if (Session.serverIsAlive()) {
            c1.authenticate();
            c2.authenticate();
            c3.authenticate();

            c1.sendAndReceive(Utilities.list(channel), 2);
        }

        Evaluation.list(c1, channel, "", 0);
    }

    @Test
    void list4() throws Exception {
        Client c1 = create(Preset.KATE);
        Client c2 = create(Preset.WARREN);
        Client c3 = create(Preset.SCHROTTY);

        String channel = "Geeks";
        String topic = "Nerdistan";
        if (Session.serverIsAlive()) {
            c3.authenticateAndJoin(channel);
            c3.sendAndReceive(Utilities.setTopic(channel, topic));

            c2.authenticate();
            c1.authenticate();

            c1.sendAndReceive(Utilities.list(channel), 2);
        }

        Evaluation.list(c3, channel, topic, 1);
    }
}
