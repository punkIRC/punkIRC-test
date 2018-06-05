package de.rubenmaurer.punk.evaluation;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.messages.Template;

public class Evaluation {
    public static boolean replyWelcome(Client client) {
        String expected = Template.get("RPL_WELCOME")
                .single("nick", client.nickname())
                .single("user", client.username())
                .single("host", client.hostname()).render();

        return expected.equals(client.lastLines()[0]);
    }

    public static boolean noReply(Client client) {
        return client.lastResponse().isEmpty();
    }
}
