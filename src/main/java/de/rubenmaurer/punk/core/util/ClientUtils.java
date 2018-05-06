package de.rubenmaurer.punk.core.util;

import de.rubenmaurer.punk.core.facade.Client;

public class ClientUtils {
    public static String nick(Client client) {
        return String.format("NICK %s\r\n", client.nickname());
    }

    public static String user(Client client) {
        return String.format("USER %s * * :%s\r\n", client.username(), client.realname());
    }

    public static String auth(Client client) {
        return String.format("%s%s", nick(client), user(client));
    }
}
