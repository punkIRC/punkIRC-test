package de.rubenmaurer.punk.evaluation;

import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.Settings;
import de.rubenmaurer.punk.evaluation.antlr.Parser;

import java.util.HashMap;

/**
 * Used for call the {@link Parser} and parse received messages.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
public class Evaluation {

    /**
     * Tries to evaluate a ping message.
     *
     * @param sender the sender
     */
    public static void ping(Client sender) {
        Parser.parse(sender, Response.NONE, sender.lastResponse());
    }

    /**
     * Tries to evaluate a welcome message.
     *
     * @param sender the sender
     */
    public static void welcome(Client sender) {
        welcome(sender, new HashMap<>());
    }

    /**
     * Tries to evaluate a welcome message.
     *
     * @param sender the sender
     * @param user the expected user count
     * @param unknown the expected unknown count
     */
    public static void welcome(Client sender, int user, int unknown) {
        HashMap<String, String> values = new HashMap<>();
        values.put("user", String.valueOf(user));
        values.put("unknown", String.valueOf(unknown));
        values.put("clients", String.valueOf(user + unknown));

        welcome(sender, values);
    }

    /**
     * Tries to evaluate a welcome message.
     *
     * @param sender the sender
     * @param values collection of values needed for evaluation
     */
    public static void welcome(Client sender, HashMap<String, String> values) {
        Parser.parse(sender, Response.WELCOME, sender.log(Response.WELCOME).getLast());
        Parser.parse(sender, Response.YOURHOST, sender.log(Response.YOURHOST).getLast());
        Parser.parse(sender, Response.CREATED, sender.log(Response.CREATED).getLast());
        Parser.parse(sender, Response.MY_INFO, sender.log(Response.MY_INFO).getLast());

        Parser.parse(sender, Response.LUSER_CLIENT, sender.log(Response.LUSER_CLIENT).getLast(), values);
        Parser.parse(sender, Response.LUSER_OP, sender.log(Response.LUSER_OP).getLast(), values);
        Parser.parse(sender, Response.LUSER_UNKNOWN, sender.log(Response.LUSER_UNKNOWN).getLast(), values);
        Parser.parse(sender, Response.LUSER_CHANNEL, sender.log(Response.LUSER_CHANNEL).getLast(), values);
        Parser.parse(sender, Response.LUSER_ME, sender.log(Response.LUSER_ME).getLast(), values);
    }

    /**
     * Evaluates the last received unknown message.
     *
     * @param sender the sender
     * @param command the unknown command
     */
    public static void unknown(Client sender, String command) {
        HashMap<String, String> map = new HashMap<>();
        map.put("command", command);

        Parser.parse(sender, Response.UNKNOWN_COMMAND, sender.lastResponse(), map);
    }

    /**
     * Evaluates the last received who message(s).
     *
     * @param sender the sender
     * @param target the who target
     */
    public static void whois(Client sender, Client target) {
        Parser.parse(sender, target, Response.WHO_IS_USER, sender.log(Response.WHO_IS_USER).getLast());
        Parser.parse(sender, target, Response.WHO_IS_SERVER, sender.log(Response.WHO_IS_SERVER).getLast());
        Parser.parse(sender, target, Response.END_OF_WHO_IS, sender.log(Response.END_OF_WHO_IS).getLast());
    }

    /**
     * Tries to evaluate the last private message.
     *
     * @param sender the sender
     * @param receiver the receiver
     * @param target the target nickname
     * @param message the message
     */
    public static void privateMessage(Client sender, Client receiver, String target, String message) {
        privateMessage(sender, receiver, target, message, false);
    }

    /**
     * Tries to evaluate the last private message.
     *
     * @param sender the sender
     * @param receiver the receiver
     * @param target the target
     * @param message the message
     * @param channel is a channel message?
     */
    public static void privateMessage(Client sender, Client receiver, String target, String message, boolean channel) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("target", channel ? String.format("#%s", target) : target);

        Parser.parse(sender, receiver, Response.NONE, receiver.trash(), map);
    }

    /**
     * Tries to evaluate the last notice message.
     *
     * @param sender the sender
     * @param receiver the receiver
     * @param target the target
     * @param message the message
     */
    public static void notice(Client sender, Client receiver, String target, String message) {
        notice(sender, receiver, target, message, false);
    }

    /**
     * Tries to evaluate the last notice message.
     *
     * @param sender the sender
     * @param receiver the receiver
     * @param target the target
     * @param message the message
     * @param channel is a channel message?
     */
    public static void notice(Client sender, Client receiver, String target, String message, boolean channel) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("target", channel ? String.format("#%s", target) : target);

        Parser.parse(sender, receiver, Response.NONE, receiver.trash(), map);
    }

    /**
     * Tries to evaluate the last noSuchNick message.
     *
     * @param sender the sender
     */
    public static void noSuchNick(Client sender) {
        //Parser.parse(client.lastResponse(), Parser.Type.NOSUCHNICK);
    }

    /**
     * Evaluate the last nicknameInUse message.
     *
     * @param sender the sender
     */
    public static void nicknameInUse(Client sender) {
        HashMap<String, String> values = new HashMap<>();
        values.put("nickname", sender.nickname());

        Parser.parse(sender, Response.NICKNAME_IN_USE, sender.log(Response.NICKNAME_IN_USE).getLast(), values);
    }

    /**
     * Evaluated the last luser messages.
     *
     * @param sender the sender
     */
    public static void luser(Client sender) {
        luser(sender, 1, 0, 0);
    }

    /**
     * Evaluated the last luser messages.
     *
     * @param sender the sender
     * @param user the user count
     */
    public static void luser(Client sender, int user) {
        luser(sender, user, 0, 0);
    }

    /**
     * Evaluated the last luser messages.
     *
     * @param sender the sender
     * @param user the user count
     * @param unknown the unknown count
     * @param channels the channel count
     */
    public static void luser(Client sender, int user, int unknown, int channels) {
        HashMap<String, String> values = new HashMap<>();
        values.put("user", String.valueOf(user));
        values.put("unknown", String.valueOf(unknown));
        values.put("channels", String.valueOf(channels));
        values.put("clients", String.valueOf(user + unknown));

        Parser.parse(sender, Response.LUSER_CLIENT, sender.log(Response.LUSER_CLIENT).getLast(), values);
        Parser.parse(sender, Response.LUSER_OP, sender.log(Response.LUSER_OP).getLast(), values);
        Parser.parse(sender, Response.LUSER_UNKNOWN, sender.log(Response.LUSER_UNKNOWN).getLast(), values);
        Parser.parse(sender, Response.LUSER_CHANNEL, sender.log(Response.LUSER_CHANNEL).getLast(), values);
        Parser.parse(sender, Response.LUSER_ME, sender.log(Response.LUSER_ME).getLast(), values);
    }

    /**
     * Evaluate the last motd message.
     *
     * @param sender the sender
     * @param message the message
     */
    public static void motd(Client sender, String message) {
        HashMap<String, String> values = new HashMap<>();
        values.put("message", message);

        Parser.parse(sender, Response.MOTD_START, sender.log(Response.MOTD_START).getLast(), values);
        Parser.parse(sender, Response.MOTD, sender.log(Response.MOTD).getLast(), values);
        Parser.parse(sender, Response.END_OF_MOTD, sender.log(Response.END_OF_MOTD).getLast(), values);
    }

    /**
     * Evaluate the last noMotd message.
     *
     * @param sender the sender
     */
    public static void noMotd(Client sender) {
        Parser.parse(sender, Response.NO_MOTD, sender.log(Response.NO_MOTD).getLast());
    }

    /**
     * Tries to evaluate the last quit message.
     *
     * @param sender the sender
     * @param message the quit message
     */
    public static void quit(Client sender, String message) {
        HashMap<String, String> values = new HashMap<>();
        values.put("message", message);

        Parser.parse(sender, Response.NONE, sender.lastResponse(), values);

        Settings.sleep();
    }

    /**
     * Tries to evaluate the last join message.
     *
     * @param sender the sender
     * @param channel the channel to join
     * @param names collection of expected user on channel
     */
    public static void join(Client sender, String channel, String... names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            if (i == 0) {
                sb.append(String.format("@%s", names[i]));
                continue;
            }

            sb.append(String.format(" %s", names[i]));
        }

        HashMap<String, String> values = new HashMap<>();
        values.put("names", sb.toString());
        values.put("channel", String.format("#%s", channel));

        Parser.parse(sender, Response.NONE, sender.lastLines()[0], values);
        Parser.parse(sender, Response.NAME_RPLY, sender.log(Response.NAME_RPLY).getLast(), values);
        Parser.parse(sender, Response.END_OF_NAMES, sender.log(Response.END_OF_NAMES).getLast(), values);
    }

    /**
     * Evaluate last topic message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param expected the expected topic
     * @param names collection of expected user on channel
     */
    public static void joinAndTopic(Client sender, String channel, String expected, String... names) {
        join(sender, channel, names);

        HashMap<String, String> values = new HashMap<>();
        values.put("topic", expected);
        values.put("channel", String.format("#%s", channel));

        Parser.parse(sender, Response.TOPIC, sender.log(Response.TOPIC).getLast(), values);
    }

    /**
     * Tries to evaluate the last join message.
     *
     * @param sender the sender
     * @param channel the channel
     */
    public static void channelRelay(Client sender, String channel) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));

        Parser.parse(sender, Response.NONE, sender.lastLines()[0], values);
    }

    /**
     * Expect an empty string as last response.
     *
     * @param client the client
     */
    public static void empty(Client client) {
        if (!client.last().isEmpty()) {
            throw new RuntimeException("Found a response where no should be!");
        }
    }

    /**
     * Evaluate the last noSuchNick/ noSuchChannel message.
     *
     * @param sender the sender
     * @param channel the channel
     */
    public static void noSuchChannel(Client sender, String channel) {
        Response r = Response.NO_SUCH_NICK;

        HashMap<String, String> map = new HashMap<>();
        map.put("target", String.format("#%s", channel));

        Parser.parse(sender, sender, r, sender.log(r).getLast(), map);
    }

    /**
     * Evaluate the last cannotSendToChannel message.
     *
     * @param sender the sender
     * @param channel the chaannel
     */
    public static void cannotSendToChannel(Client sender, String channel) {
        Response r = Response.CANNOT_SEND_TO_CHANNEL;

        HashMap<String, String> map = new HashMap<>();
        map.put("channel", String.format("#%s", channel));

        Parser.parse(sender, sender, r, sender.log(r).getLast(), map);
    }

    /**
     * Tries to evaluate the last part message.
     *
     * @param sender the sender
     * @param channel the channel
     */
    public static void part(Client sender, String channel) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));

        Parser.parse(sender, Response.NONE, sender.lastLines()[0], values);
    }

    /**
     * Evaluates the last notOnChannel message.
     *
     * @param sender the sender
     * @param channel the channel
     */
    public static void notOnChannel(Client sender, String channel) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));

        Parser.parse(sender, Response.NOT_ON_CHANNEL, sender.log(Response.NOT_ON_CHANNEL).getLast(), values);
    }

    /**
     * Tries to evaluate the last topic message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param topic the new topic
     */
    public static void setTopic(Client sender, String channel, String topic) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));
        values.put("topic", topic);

        Parser.parse(sender, Response.NONE, sender.lastResponse(), values);
    }

    /**
     * Tries to evaluate the last topic message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param expected the expected topic
     */
    public static void getTopic(Client sender, String channel, String expected) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));
        values.put("topic", expected);

        if (!expected.isEmpty()) {
            Parser.parse(sender, Response.TOPIC, sender.log(Response.TOPIC).getLast(), values);
            return;
        }

        Parser.parse(sender, Response.NO_TOPIC, sender.log(Response.NO_TOPIC).getLast(), values);
    }

    /**
     * Evaluates the last list message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param expected the expected topic
     * @param user the expected user count
     */
    public static void list(Client sender, String channel, String expected, int user) {
        HashMap<String, String> values = new HashMap<>();
        values.put("channel", String.format("#%s", channel));
        values.put("topic", expected);
        values.put("user", String.valueOf(user));

        Parser.parse(sender, Response.LIST, sender.log(Response.LIST).getLast(), values);
        Parser.parse(sender, Response.LIST_END, sender.log(Response.LIST_END).getLast());
    }

    /**
     * Evaluates the last who message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param offset the offset
     * @param clients collection of expected clients
     */
    public static void who(Client sender, String channel, int offset, Client... clients) {
        for (int i = 0; i < clients.length; i++) {
            who(sender, channel, clients[i], i + offset);
        }
    }

    /**
     * Evaluates the last who message.
     *
     * @param sender the sender
     * @param channel the channel
     * @param target the target
     * @param index the index
     */
    private static void who(Client sender, String channel, Client target, int index) {
        HashMap<String, String> values = new HashMap<>();
        values.put("target", channel.equals("*") ? "*" : String.format("#%s", channel));
        values.put("nick", sender.nickname());

        values.put("nick_who", target.nickname());
        values.put("user_who", target.username());
        values.put("fullname_who", target.fullname());

        Parser.parse(sender, Response.WHO_RPLY, sender.log(Response.WHO_RPLY).get(index), values);
        Parser.parse(sender, Response.END_OF_WHO, sender.log(Response.END_OF_WHO).getLast(), values);
    }

    /**
     * Tries to evaluate the last quit relay.
     *
     * @param sender the sender
     * @param message the quit message
     */
    public static void quitRelay(Client sender, String message) {
        HashMap<String, String> values = new HashMap<>();
        values.put("message", message);

        Parser.parse(sender, Response.NONE, sender.lastResponse(), values);
    }
}
