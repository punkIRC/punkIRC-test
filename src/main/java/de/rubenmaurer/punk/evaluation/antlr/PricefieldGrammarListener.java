package de.rubenmaurer.punk.evaluation.antlr;

import de.rubenmaurer.punk.IRCBaseListener;
import de.rubenmaurer.punk.core.facade.Client;
import de.rubenmaurer.punk.evaluation.Response;

import java.util.Map;

import static de.rubenmaurer.punk.IRCParser.*;

class PricefieldGrammarListener extends IRCBaseListener {

    private Client sender;

    private Client receiver;

    private Response code;

    private Map<String, String> values;

    PricefieldGrammarListener(Client sender, Client receiver, Response code, Map<String, String> values) {
        this.sender = sender;
        this.code = code;
        this.values = values;
        this.receiver = receiver;
    }

    /* === UTILS === */
    private void codeCheck(String expected, Response real) {
        intCheck(expected, real.value);
    }

    private void intCheck(String excpected, int real) {
        intCheck(excpected, String.valueOf(real));
    }

    private void intCheck(String expected, String real) {
        if (Integer.parseInt(expected) != Integer.parseInt(real)) {
            throw new RuntimeException(String.format("Expected: '%s', but got '%s'.", expected, real));
        }
    }

    private void stringCheck(String expected, String real) {
        if (!expected.equals(real)) {
            throw new RuntimeException(String.format("Expected: '%s', but got '%s'.", expected, real));
        }
    }

    /* === IRC SERVER RESPONSE === */

    @Override
    public void enterServer_response(Server_responseContext ctx) {
        super.enterServer_response(ctx);
    }

    @Override
    public void enterServer_response_long(Server_response_longContext ctx) {
        codeCheck(ctx.code().getText(), code);
        stringCheck(ctx.nick().getText(), sender.nickname());
    }

    @Override
    public void enterServer_response_short(Server_response_shortContext ctx) {
        stringCheck(ctx.nick().getText(), sender.nickname());
        stringCheck(ctx.user().getText(), sender.username());
    }

    /* === LUSER === */

    @Override
    public void enterLuser_client(Luser_clientContext ctx) {
        intCheck(ctx.INTEGER(0).getText(), values.getOrDefault("user", "1"));
        intCheck(ctx.INTEGER(1).getText(), values.getOrDefault("services", "0"));
        intCheck(ctx.INTEGER(2).getText(), values.getOrDefault("server", "1"));
    }

    @Override
    public void enterLuser_op(Luser_opContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("op", "0"));
    }

    @Override
    public void enterLuser_unknown(Luser_unknownContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("unknown", "0"));
    }

    @Override
    public void enterLuser_channel(Luser_channelContext ctx) {
        intCheck(ctx.INTEGER().getText(), values.getOrDefault("channels", "0"));
    }

    @Override
    public void enterLuser_me(Luser_meContext ctx) {
        intCheck(ctx.INTEGER(0).getText(), values.getOrDefault("clients", "1"));
        intCheck(ctx.INTEGER(1).getText(), values.getOrDefault("server", "1"));
    }

    /* === MOTD === */

    @Override
    public void enterMotd(MotdContext ctx) {
        stringCheck(ctx.message().getText(), values.get("message"));
    }

    /* === PRIVMSG & NOTICE === */

    @Override
    public void enterPrivate_message(Private_messageContext ctx) {
        stringCheck(ctx.text().getText(), values.get("message"));
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    @Override
    public void enterNotice(NoticeContext ctx) {
        stringCheck(ctx.text().getText(), values.get("message"));
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    /* === WHOIS === */

    @Override
    public void enterWho_is_user(Who_is_userContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
        stringCheck(ctx.user().getText(), receiver.username());
        stringCheck(ctx.fullname().getText(), receiver.realname());
    }

    @Override
    public void enterWho_is_server(Who_is_serverContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
    }

    @Override
    public void enterEnd_of_who_is(End_of_who_isContext ctx) {
        stringCheck(ctx.nick().getText(), receiver.nickname());
    }

    /* === IRC ERROR's === */

    @Override
    public void enterNo_such_nick_channel(No_such_nick_channelContext ctx) {
        stringCheck(ctx.target().getText(), values.get("target"));
    }

    @Override
    public void enterCannot_send_to_channel(Cannot_send_to_channelContext ctx) {
        stringCheck(ctx.channel().getText(), values.get("channel"));
    }

    @Override
    public void enterUnknown_command(Unknown_commandContext ctx) {
        stringCheck(ctx.command().getText(), values.get("command"));
    }
}
