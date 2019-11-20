package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.Preset.*;
import static de.rubenmaurer.punk.core.facade.Client.create;

public class PartChannel extends BaseTest {

    @Test
    void joinAndPart1() throws Exception {
        Client c1 = Client.create(VICTORIA);
        Client c2 = Client.create(SAMUEL);

        String channel = "Blackwell-Hallways";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(String.format("PART #%s", channel), 1);
        }

        Evaluation.part(c1, channel);
        Evaluation.part(c2, channel);
    }

    @Test
    void joinAndPart2() throws Exception {
        Client c1 = Client.create(MAX);
        Client c2 = Client.create(JOYCE);

        String channel = "Blackwell-Hallways";
        String message = "See ya!";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(Utilities.part(channel, message), 1);
        }

        Evaluation.part(c1, channel);
        Evaluation.part(c2, channel);
    }

    @Test
    void joinAndPart3() throws Exception {
        Client c1 = Client.create(CHLOE);
        Client c2 = Client.create(JOYCE);

        String channel = "Lighthouse";
        String message = "I love you!";
        String quit = "See ya!";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(Utilities.privateMessage(channel, message));
            Evaluation.privateMessage(c1, c2, channel, message, true);

            c2.sendAndReceive(Utilities.part(channel, quit), 1);
            c1.sendAndReceive(Utilities.privateMessage(channel, message));

            Evaluation.privateMessage(c1, c2, channel, message, true);
        }
    }

    @Test
    void joinAndPart4() throws Exception {
        Client c1 = Client.create(DAVID);
        Client c2 = Client.create(JOYCE);

        String channel = "Price-House";
        String message = "See ya!";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(Utilities.part(channel, message), 1);
            c2.sendAndReceive(Utilities.part(channel, message), 1);
        }

        Evaluation.part(c1, channel);
        Evaluation.part(c2, channel);
    }

    @Test
    void partNoChannel1() throws Exception {
        Client c1 = create(Client.Preset.CHLOE);

        String channel = "Lighthouse";
        if (Session.serverIsAlive()) {
            c1.authenticate();
            c1.sendAndReceive(Utilities.part(channel, "What ever"), 1);
        }

        Evaluation.noSuchChannel(c1, channel);
    }

    @Test
    void partNoChannel2() throws Exception {
        Client c = Client.create(DAVID);

        String channel = "Blackwell";
        if (Session.serverIsAlive()) {
            c.authenticateAndJoin(channel);
            c.send(String.format("PART #%s", channel));
            c.sendAndReceive(String.format("PART #%s", channel), 1);
        }

        Evaluation.noSuchChannel(c, channel);
    }

    @Test
    void partNotOnChannel1() throws Exception {
        Client c1 = Client.create(MAX);
        Client c2 = Client.create(WARREN);

        String channel = "Blackwell-Dorms";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticate();

            c2.sendAndReceive(String.format("PART #%s", channel), 1);
        }

        Evaluation.notOnChannel(c2, channel);
    }

    @Test
    void partNotOnChannel2() throws Exception {
        Client c1 = Client.create(RACHEL);
        Client c2 = Client.create(VICTORIA);

        String channel = "Blackwell-Dorms";
        if (Session.serverIsAlive()) {
            c2.authenticateAndJoin(channel);
            c1.authenticate();

            c1.sendAndReceive(String.format("PART #%s", channel), 1);
        }

        Evaluation.notOnChannel(c1, channel);
    }
}
