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

    public static List<String> auth(Client client) {
        List<String> lst = new ArrayList<>();
        lst.add(user(client));
        lst.add(nick(client));

        return lst;
    }

    public static String privateMessage(Client target, String message) {
        return Template.get("privmsg")
                .single("nickname", target.nickname())
                .single("message", message).render();
    }

    public static String privateMessage(String channel, String message) {
        return Template.get("privmsgchannel")
                .single("channel", channel)
                .single("message", message).render();
    }

    public static String notice(Client target, String message) {
        return Template.get("notice")
                .single("nickname", target.nickname())
                .single("message", message).render();
    }

    public static String quit(String message) {
        return Template.get("quit").single("message", message).render();
    }

    public static String joinChannel(String channel) {
        return Template.get("join").single("channel", channel).render();
    }

    public static String whoIs(String user) {
        return Template.get("whois").single("nickname", user).render();
    }
}
