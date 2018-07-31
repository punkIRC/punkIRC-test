package de.rubenmaurer.punk.test.robustness;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Robustness extends BaseTest {

    @Test
    void whitespace1() throws Exception {
        Client c = Client.create(ClientPreset.MAX);

        if (Session.serverIsAlive()) {
            c.sendAndReceive("    ", 0);
        }

        assertTrue(Evaluation.noReply(c));
    }

    @Test
    void whitespace2() throws Exception {
        Client c = Client.create(ClientPreset.SCHROTTY);

        if (Session.serverIsAlive()) {
            c.send(String.format(" %s ", ClientUtils.user(c)));
            c.sendAndReceive(String.format(" %s ", ClientUtils.nick(c)), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void whitespace3() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(String.format("NICK   %s", c.nickname()));
            c.sendAndReceive(String.format("USER  %s * *   :%s", c.username(), c.realname()), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }

    @Test
    void whitespace4() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);

        if (Session.serverIsAlive()) {
            c.send(String.format("  NICK   %s  ", c.nickname()));
            c.sendAndReceive(String.format("  USER  %s * *   :%s  ", c.username(), c.realname()), 4);
        }

        assertTrue(Evaluation.replyWelcome(c));
    }
}
