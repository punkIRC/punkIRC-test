package de.rubenmaurer.punk.core.facade;

import de.rubenmaurer.punk.Settings;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void lastResponse() throws Exception {
        Settings.storeOverride("debug", "true");

        Client c = Client.create(Client.Preset.CHLOE);
        c.lastResponse = "Hello There\nPONG";

        System.out.println(c.lastResponse());
    }
}