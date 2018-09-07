package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.*;

public class WhoChannel extends BaseTest {

    @Test
    void who1() throws Exception {
        Client c1 = create(Preset.MARK);
        Client c2 = create(Preset.DAVID);

        Client c3 = Client.create(Preset.KATE);
        Client c4 = Client.create(Preset.CHLOE);

        Client c5 = Client.create(Preset.MAX);
        Client c6 = Client.create(Preset.WARREN);

        String[] channels = new String[] { "Parents", "Dorms", "Geeks" };
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channels[0]);
            c2.authenticateAndJoin(channels[0]);

            c3.authenticateAndJoin(channels[1]);
            c4.authenticateAndJoin(channels[1]);

            c5.authenticateAndJoin(channels[2]);
            c6.authenticateAndJoin(channels[2]);

            c1.sendAndReceive(String.format("WHO #%s", channels[0]));
            Evaluation.who(c1, channels[0], 0, c1, c2);

            c1.sendAndReceive(String.format("WHO #%s", channels[1]));
            Evaluation.who(c1, channels[1], 2, c3, c4);

            c1.sendAndReceive(String.format("WHO #%s", channels[2]));
            Evaluation.who(c1, channels[2], 4, c5, c6);
        }
    }

    @Test
    void who2() throws Exception {
        Client c1 = create(Preset.MARK);
        Client c2 = create(Preset.DAVID);

        Client c3 = Client.create(Preset.KATE);
        Client c4 = Client.create(Preset.CHLOE);

        Client c5 = Client.create(Preset.MAX);
        Client c6 = Client.create(Preset.WARREN);

        String[] channels = new String[] { "Parents", "Dorms", "Geeks" };
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channels[0]);
            c2.authenticateAndJoin(channels[0]);

            c3.authenticateAndJoin(channels[1]);
            c4.authenticateAndJoin(channels[1]);

            c5.authenticateAndJoin(channels[2]);
            c6.authenticateAndJoin(channels[2]);

            c1.sendAndReceive("WHO *");
        }

        Evaluation.who(c1, "*", 0, c3, c4, c5, c6);
    }

    @Test
    void who3() throws Exception {
        Client c1 = create(Preset.MARK);
        Client c2 = create(Preset.DAVID);

        Client c3 = Client.create(Preset.KATE);
        Client c4 = Client.create(Preset.CHLOE);

        Client c5 = Client.create(Preset.MAX);
        Client c6 = Client.create(Preset.WARREN);

        String[] channels = new String[] { "Parents", "Dorms" };
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channels[0]);
            c2.authenticateAndJoin(channels[0]);

            c3.authenticateAndJoin(channels[1]);
            c4.authenticateAndJoin(channels[1]);

            c5.authenticate();
            c6.authenticate();

            c1.sendAndReceive("WHO *");
        }

        Evaluation.who(c1, "*", 0, c3, c4);
    }

    @Test
    void who4() throws Exception {
        Client c1 = create(Preset.MARK);
        Client c2 = create(Preset.DAVID);

        Client c3 = Client.create(Preset.KATE);
        Client c4 = Client.create(Preset.CHLOE);

        Client c5 = Client.create(Preset.MAX);
        Client c6 = Client.create(Preset.WARREN);

        String[] channels = new String[] { "Parents", "Dorms" };
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channels[0]);
            c2.authenticateAndJoin(channels[0]);

            c3.authenticateAndJoin(channels[1]);
            c4.authenticateAndJoin(channels[1]);

            c5.authenticate();
            c6.authenticate();

            c1.sendAndReceive(String.format("WHO #%s", channels[0]));
            Evaluation.who(c1, channels[0], 0, c1, c2);

            c1.sendAndReceive(String.format("WHO #%s", channels[1]));
            Evaluation.who(c1, channels[1], 2, c3, c4);
        }
    }
}
