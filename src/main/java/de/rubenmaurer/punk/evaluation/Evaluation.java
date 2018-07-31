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

    public static boolean replyPing(Client client) {
        return Template.get("PING").render().equals(client.lastLines()[0]);
    }

    public static boolean replyUnknown(Client client) {
        return Template.get("UNKNOWN").render().equals(client.lastLines()[0]);
    }

    public static boolean replyWhoIs(Client sender, Client target) {
        return false;
    }

    public static boolean replyWhoIs(Client sender, String target) {
        return false;
    }

    public static boolean noReply(Client client) {
        return client.lastResponse().isEmpty();
    }

    public static boolean noSuchNick(Client client) {
        return false;
    }

    public static boolean nicknameInUse(Client client) {
        return false;
    }

    public static boolean replyQuit(Client client) {
        return false;
    }
}
