package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.facade.Client.Utilities;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.*;
import static de.rubenmaurer.punk.core.facade.Client.create;

public class AssignmentChannel extends BaseTest {

    @Test
    void updateNick() throws Exception {
        Client c1 = create(Preset.MAX);
        Client c2 = create(Preset.WARREN);

        String channel = "Blackhell";
        String nickname = "Noir Angel";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(String.format("NICK %s", nickname));
        }
    }

    @Test
    void quit1() throws Exception {
        Client c1 = create(Preset.MAX);
        Client c2 = create(Preset.WARREN);

        String channel = "Blackhell";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive(Utilities.quit("Bye!"));
        }

        Evaluation.quitRelay(c1, "Bye!");
    }

    @Test
    void quit2() throws Exception {
        Client c1 = create(Preset.MAX);
        Client c2 = create(Preset.WARREN);

        String channel = "Blackhell";
        if (Session.serverIsAlive()) {
            c1.authenticateAndJoin(channel);
            c2.authenticateAndJoin(channel);

            c1.sendAndReceive("QUIT");
        }

        Evaluation.quitRelay(c1, "Client Quit");
    }
}
