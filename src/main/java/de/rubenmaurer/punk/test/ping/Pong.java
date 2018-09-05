package de.rubenmaurer.punk.test.ping;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.facade.Client.Preset;
import de.rubenmaurer.punk.evaluation.Evaluation;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

public class Pong extends BaseTest {

    @Test
    void testPong() throws Exception {
        Client c = Client.create(Preset.CHLOE);

        c.authenticate();
        c.sendAndReceive("PONG", 0);

        Evaluation.empty(c);
    }
}
