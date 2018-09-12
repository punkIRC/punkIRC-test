package de.rubenmaurer.punk.evaluation.antlr;

import de.rubenmaurer.punk.IRCBaseListener;
import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.evaluation.Response;

import java.util.Map;

import static de.rubenmaurer.punk.IRCParser.*;

/**
 * Listener for walking the IRCTree.
 *
 * @author Ruben Maurer
 * @version 1.0
 * @since 1.0
 */
class PricefieldGrammarListener extends IRCBaseListener {

    /**
     * The message sender.
     */
    private Client sender;

    /**
     * The message receiver.
     */
    private Client receiver;

    /**
     * The expected response code.
     */
    private Response code;

    /**
     * Collection of values used for evaluation.
     */
    private Map<String, String> values;

    /**
     * Created a new Listener.
     *
     * @param sender the message sender
     * @param receiver the message receiver
     * @param code the expected {@link Response}
     * @param values the collection of needed eval. values
     */
    PricefieldGrammarListener(Client sender, Client receiver, Response code, Map<String, String> values) {
        this.sender = sender;
        this.code = code;
        this.values = values;
        this.receiver = receiver;
    }

    /* === UTILS === */

    /**
     * Compares a string and a {@link Response} value.
     *
     * @param expected the expected value
     * @param real the actual value
     */
    private void codeCheck(String expected, Response real) {
        intCheck(expected, real.value);
    }

    /**
     * Compares a string an an integer.
     *
     * @param expected the expected value
     * @param real the actual vaalue
     */
    private void intCheck(String expected, int real) {
        intCheck(expected, String.valueOf(real));
    }

    /**
     * Compares two strings.
     *
     * @param expected the expected value
     * @param real the actual value
     */
    private void intCheck(String expected, String real) {
        if (Integer.parseInt(expected) != Integer.parseInt(real)) {
            throw new RuntimeException(String.format("Expected: '%s', but got '%s'.", expected, real));
        }
    }

    /**
     * Compares two strings.
     *
     * @param expected the expected value
     * @param real the actual value
     */
    private void stringCheck(String expected, String real) {
        if (!expected.equals(real)) {
            throw new RuntimeException(String.format("Expected: '%s', but got '%s'.", expected, real));
        }
    }

    /* === IRC SERVER RESPONSE === */

    /**
     * Compares the response code/ nickname with the expected ones.
     *
     * @param ctx the context
     */
    @Override
    public void enterServer_response_long(Server_response_longContext ctx) {
        codeCheck(ctx.code().getText(), code);
        stringCheck(ctx.nick().getText(), sender.nickname());
    }

    /**
     * Compares the nickname/ username with the expected ones.
     *
     * @param ctx the context
     */
    @Override
    public void enterServer_response_short(Server_response_shortContext ctx) {
        stringCheck(ctx.nick().getText(), sender.nickname());
        stringCheck(ctx.user().getText(), sender.username());
    }

    /* === LUSER === */

    /**
     * Compares the amount of connected users/ services and servers with the expected ones.
     *
     * @param ctx the context
     */
    @Override
    public void enterLuser_client(Luser_clientContext ctx) {
        intCheck(ctx.INTEGER(0).getText(), values.getOrDefault("user", "1"));
        intCheck(ctx.INTEGER(1).getText(), values.getOrDefault("services", "0"));
        intCheck(ctx.INTEGER(2).getText(), values.getOrDefault("server", "1"));
    }

    /**
     * Compares the amount of connected ops with the actual one.
     *
     * @param ctx the context
     */
    @Override
    public void enterLuser_op(Luser_opContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("op", "0"));
    }

    /**
     * Compares the amount of unknown connections with the actual one.
     *
     * @param ctx the context
     */
    @Override
    public void enterLuser_unknown(Luser_unknownContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("unknown", "0"));
    }

    /**
     * Compares the amount of channels with the actual one.
     *
     * @param ctx the context
     */
    @Override
    public void enterLuser_channel(Luser_channelContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("channels", "0"));
    }

    /**
     * Compares the amount of connected clients and servers with the actual one.
     *
     * @param ctx the context
     */
    @Override
    public void enterLuser_me(Luser_meContext ctx) {
        intCheck(ctx.INTEGER(0).getText(), values.getOrDefault("clients", "1"));
        intCheck(ctx.INTEGER(1).getText(), values.getOrDefault("server", "1"));
    }

    /* === MOTD === */

    /**
     * Compare the actual motd with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterMotd(MotdContext ctx) {
        stringCheck(ctx.message().getText(), values.get("message"));
    }

    /* === CHANNEL's === */

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterNamelist(NamelistContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    /**
     * Compare the actual nickname with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterName_reply(Name_replyContext ctx) {
        stringCheck(ctx.nicknames().getText(), values.get("names"));
    }

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterEnd_of_names(End_of_namesContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterPart(PartContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    /**
     * Compare the actual channel name and the actual topic with
     * the expected ones.
     *
     * @param ctx the context
     */
    @Override
    public void enterTopic(TopicContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
        stringCheck(ctx.message().getText(), values.get("topic"));
    }

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterNo_topic(No_topicContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    /* === LIST === */

    /**
     * Compare the actual channel name/ topic and user count
     * with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterList(ListContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
        stringCheck(ctx.message().getText(), values.get("topic"));
        intCheck(ctx.INTEGER().getText(), values.get("user"));
    }

    /* === PRIVMSG & NOTICE === */

    /**
     * Compare the actual message and target with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterPrivate_message(Private_messageContext ctx) {
        stringCheck(ctx.message().getText(), values.get("message"));
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    /**
     * Compare the actual message and target with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterNotice(NoticeContext ctx) {
        stringCheck(ctx.message().getText(), values.get("message"));
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    /* === WHOIS === */

    /**
     * Compare the actual nickname/ username and fullname
     * with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterWho_is_user(Who_is_userContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
        stringCheck(ctx.user().getText(), receiver.username());
        stringCheck(ctx.fullname().getText(), receiver.fullname());
    }

    /**
     * Compare the actual nickname with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterWho_is_server(Who_is_serverContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
    }

    /**
     * Compare the actual nickname with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterEnd_of_who_is(End_of_who_isContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
    }

    /* === WHO === */

    /**
     * Compare the actual target/ username/ nickname and fullname
     * with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterWho(WhoContext ctx) {
        stringCheck(ctx.target().getText(), values.get("target"));
        stringCheck(ctx.user().getText(), values.get("user_who"));
        stringCheck(ctx.nick().getText(), values.get("nick_who"));
        stringCheck(ctx.fullname().getText(), values.get("fullname_who"));
    }

    /**
     * Compare the actual target with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterEnd_of_who(End_of_whoContext ctx) {
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    /* === GENERAL === */

    /**
     * Compare the actual message with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterQuit(QuitContext ctx) {
        stringCheck(ctx.message().getText(), values.get("message"));
    }

    /* === IRC ERROR's === */

    /**
     * Compare the actual target with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterNo_such_nick_channel(No_such_nick_channelContext ctx) {
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterCannot_send_to_channel(Cannot_send_to_channelContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    /**
     * Compare the actual command with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterUnknown_command(Unknown_commandContext ctx) {
        stringCheck(ctx.command().getText(), values.get("command"));
    }

    /**
     * Compare the actual channel name with the expected one.
     *
     * @param ctx the context
     */
    @Override
    public void enterNot_on_channel(Not_on_channelContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }
}
