import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.core.util.ClientPreset;
import de.rubenmaurer.punk.core.util.ClientUtils;
import de.rubenmaurer.punk.test.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameworkTest extends BaseTest {

    @Test
    void authTest() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);
        c.sendAndReceive(ClientUtils.auth(c), 4);
    }

    @Test
    void nickTest() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);
        c.sendAndReceive(ClientUtils.nick(c), 0);

        assertEquals("", c.lastResponse());
    }

    @Test()
    void userTest() throws Exception {
        Client c = Client.create(ClientPreset.CHLOE);
        c.sendAndReceive(ClientUtils.user(c), 1);
        c.disconnect();

        assertTrue(!"Answer".equals(c.lastResponse()));
    }
}
