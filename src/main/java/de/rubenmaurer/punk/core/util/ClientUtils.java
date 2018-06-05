package de.rubenmaurer.punk.core.util;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.messages.Template;

import java.util.ArrayList;
import java.util.List;

public class ClientUtils {
    public static String nick(Client client) {
        return Template.get("nick").single("nickname", client.nickname()).render();
    }

    public static String user(Client client) {
        return Template.get("user")
                .single("username", client.username())
                .single("realname", client.realname()).render();
    }

    public static String auth(Client client) {
        return String.format("%s%s", nick(client), user(client));
    }

    public static List<String> authPartitioned(Client client, int splits, int splitLength) {
        String auth = auth(client);
        List<String> messages = new ArrayList<>();

        for (int i = 0; i < splits; i++) {
            if (splitLength <= auth.length()) {
                messages.add(auth.substring(0, splitLength));
                auth = auth.substring(splitLength);
            }
        }

        if (auth.length() != 0) messages.add(auth);
        return messages;
    }
}
