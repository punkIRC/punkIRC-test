package de.rubenmaurer.punk.test.channel;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Session;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static de.rubenmaurer.punk.core.facade.Client.create;

public class AssigmentChannel extends BaseTest {

    @Test
    void updateNick() throws Exception {
        Client c = create(Client.Preset.MAX);

        if (Session.serverIsAlive()) {

        }
    }

    @Test
    void quit1() {

    }

    @Test
    void quit2() {

    }
}
