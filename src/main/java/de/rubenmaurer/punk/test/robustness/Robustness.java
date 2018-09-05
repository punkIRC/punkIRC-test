package de.rubenmaurer.punk.test.robustness;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.core.util.Settings;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Robustness extends BaseTest {

    @Test
    void whitespace1() throws Exception {
        Client c = Client.create(Preset.MAX);
        c.sendAndReceive("      ");

        Evaluation.empty(c);
    }

    @Test
    void whitespace2() throws Exception {
        Client c = Client.create(Preset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(String.format(" %s ", ClientUtils.user(c)));
            c.sendAndReceive(String.format(" %s ", ClientUtils.nick(c)), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void whitespace3() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(String.format("NICK   %s", c.nickname()));
            c.sendAndReceive(String.format("USER  %s * *   :%s", c.username(), c.realname()), Settings.authLines());
        }

        Evaluation.welcome(c);
    }

    @Test
    void whitespace4() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(String.format("  NICK   %s  ", c.nickname()));
            c.sendAndReceive(String.format("  USER  %s * *   :%s  ", c.username(), c.realname()), Settings.authLines());
        }

        Evaluation.welcome(c);
    }
}
