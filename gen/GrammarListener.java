// Generated from D:/Projekte/Pricefield/src/main/antlr/de.rubenmaurer.punk\Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(GrammarParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(GrammarParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#server_response}.
	 * @param ctx the parse tree
	 */
	void enterServer_response(GrammarParser.Server_responseContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#server_response}.
	 * @param ctx the parse tree
	 */
	void exitServer_response(GrammarParser.Server_responseContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#server}.
	 * @param ctx the parse tree
	 */
	void enterServer(GrammarParser.ServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#server}.
	 * @param ctx the parse tree
	 */
	void exitServer(GrammarParser.ServerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(GrammarParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(GrammarParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#nick}.
	 * @param ctx the parse tree
	 */
	void enterNick(GrammarParser.NickContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#nick}.
	 * @param ctx the parse tree
	 */
	void exitNick(GrammarParser.NickContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#welcome}.
	 * @param ctx the parse tree
	 */
	void enterWelcome(GrammarParser.WelcomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#welcome}.
	 * @param ctx the parse tree
	 */
	void exitWelcome(GrammarParser.WelcomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#unknown}.
	 * @param ctx the parse tree
	 */
	void enterUnknown(GrammarParser.UnknownContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#unknown}.
	 * @param ctx the parse tree
	 */
	void exitUnknown(GrammarParser.UnknownContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#noSuchNick}.
	 * @param ctx the parse tree
	 */
	void enterNoSuchNick(GrammarParser.NoSuchNickContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#noSuchNick}.
	 * @param ctx the parse tree
	 */
	void exitNoSuchNick(GrammarParser.NoSuchNickContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#ping}.
	 * @param ctx the parse tree
	 */
	void enterPing(GrammarParser.PingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#ping}.
	 * @param ctx the parse tree
	 */
	void exitPing(GrammarParser.PingContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#private_message}.
	 * @param ctx the parse tree
	 */
	void enterPrivate_message(GrammarParser.Private_messageContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#private_message}.
	 * @param ctx the parse tree
	 */
	void exitPrivate_message(GrammarParser.Private_messageContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#notice}.
	 * @param ctx the parse tree
	 */
	void enterNotice(GrammarParser.NoticeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#notice}.
	 * @param ctx the parse tree
	 */
	void exitNotice(GrammarParser.NoticeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_WHOISUSER}.
	 * @param ctx the parse tree
	 */
	void enterRpl_WHOISUSER(GrammarParser.Rpl_WHOISUSERContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_WHOISUSER}.
	 * @param ctx the parse tree
	 */
	void exitRpl_WHOISUSER(GrammarParser.Rpl_WHOISUSERContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_WHOISSERVER}.
	 * @param ctx the parse tree
	 */
	void enterRpl_WHOISSERVER(GrammarParser.Rpl_WHOISSERVERContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_WHOISSERVER}.
	 * @param ctx the parse tree
	 */
	void exitRpl_WHOISSERVER(GrammarParser.Rpl_WHOISSERVERContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_ENDOFWHOIS}.
	 * @param ctx the parse tree
	 */
	void enterRpl_ENDOFWHOIS(GrammarParser.Rpl_ENDOFWHOISContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_ENDOFWHOIS}.
	 * @param ctx the parse tree
	 */
	void exitRpl_ENDOFWHOIS(GrammarParser.Rpl_ENDOFWHOISContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_LUSERCLIENT}.
	 * @param ctx the parse tree
	 */
	void enterRpl_LUSERCLIENT(GrammarParser.Rpl_LUSERCLIENTContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_LUSERCLIENT}.
	 * @param ctx the parse tree
	 */
	void exitRpl_LUSERCLIENT(GrammarParser.Rpl_LUSERCLIENTContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_LUSEROP}.
	 * @param ctx the parse tree
	 */
	void enterRpl_LUSEROP(GrammarParser.Rpl_LUSEROPContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_LUSEROP}.
	 * @param ctx the parse tree
	 */
	void exitRpl_LUSEROP(GrammarParser.Rpl_LUSEROPContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_LUSERUNKNOWN}.
	 * @param ctx the parse tree
	 */
	void enterRpl_LUSERUNKNOWN(GrammarParser.Rpl_LUSERUNKNOWNContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_LUSERUNKNOWN}.
	 * @param ctx the parse tree
	 */
	void exitRpl_LUSERUNKNOWN(GrammarParser.Rpl_LUSERUNKNOWNContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_LUSERCHANNELS}.
	 * @param ctx the parse tree
	 */
	void enterRpl_LUSERCHANNELS(GrammarParser.Rpl_LUSERCHANNELSContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_LUSERCHANNELS}.
	 * @param ctx the parse tree
	 */
	void exitRpl_LUSERCHANNELS(GrammarParser.Rpl_LUSERCHANNELSContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_LUSERME}.
	 * @param ctx the parse tree
	 */
	void enterRpl_LUSERME(GrammarParser.Rpl_LUSERMEContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_LUSERME}.
	 * @param ctx the parse tree
	 */
	void exitRpl_LUSERME(GrammarParser.Rpl_LUSERMEContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_MOTDSTART}.
	 * @param ctx the parse tree
	 */
	void enterRpl_MOTDSTART(GrammarParser.Rpl_MOTDSTARTContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_MOTDSTART}.
	 * @param ctx the parse tree
	 */
	void exitRpl_MOTDSTART(GrammarParser.Rpl_MOTDSTARTContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_MOTD}.
	 * @param ctx the parse tree
	 */
	void enterRpl_MOTD(GrammarParser.Rpl_MOTDContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_MOTD}.
	 * @param ctx the parse tree
	 */
	void exitRpl_MOTD(GrammarParser.Rpl_MOTDContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_ENDOFMOTD}.
	 * @param ctx the parse tree
	 */
	void enterRpl_ENDOFMOTD(GrammarParser.Rpl_ENDOFMOTDContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_ENDOFMOTD}.
	 * @param ctx the parse tree
	 */
	void exitRpl_ENDOFMOTD(GrammarParser.Rpl_ENDOFMOTDContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#rpl_QUIT}.
	 * @param ctx the parse tree
	 */
	void enterRpl_QUIT(GrammarParser.Rpl_QUITContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#rpl_QUIT}.
	 * @param ctx the parse tree
	 */
	void exitRpl_QUIT(GrammarParser.Rpl_QUITContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#join_reply}.
	 * @param ctx the parse tree
	 */
	void enterJoin_reply(GrammarParser.Join_replyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#join_reply}.
	 * @param ctx the parse tree
	 */
	void exitJoin_reply(GrammarParser.Join_replyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#join_name_reply}.
	 * @param ctx the parse tree
	 */
	void enterJoin_name_reply(GrammarParser.Join_name_replyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#join_name_reply}.
	 * @param ctx the parse tree
	 */
	void exitJoin_name_reply(GrammarParser.Join_name_replyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#join_name_end_reply}.
	 * @param ctx the parse tree
	 */
	void enterJoin_name_end_reply(GrammarParser.Join_name_end_replyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#join_name_end_reply}.
	 * @param ctx the parse tree
	 */
	void exitJoin_name_end_reply(GrammarParser.Join_name_end_replyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#no_such_channel}.
	 * @param ctx the parse tree
	 */
	void enterNo_such_channel(GrammarParser.No_such_channelContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#no_such_channel}.
	 * @param ctx the parse tree
	 */
	void exitNo_such_channel(GrammarParser.No_such_channelContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#cannot_send_to_chnl}.
	 * @param ctx the parse tree
	 */
	void enterCannot_send_to_chnl(GrammarParser.Cannot_send_to_chnlContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#cannot_send_to_chnl}.
	 * @param ctx the parse tree
	 */
	void exitCannot_send_to_chnl(GrammarParser.Cannot_send_to_chnlContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#err_NOMOTD}.
	 * @param ctx the parse tree
	 */
	void enterErr_NOMOTD(GrammarParser.Err_NOMOTDContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#err_NOMOTD}.
	 * @param ctx the parse tree
	 */
	void exitErr_NOMOTD(GrammarParser.Err_NOMOTDContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#err_NICKNAMEINUSE}.
	 * @param ctx the parse tree
	 */
	void enterErr_NICKNAMEINUSE(GrammarParser.Err_NICKNAMEINUSEContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#err_NICKNAMEINUSE}.
	 * @param ctx the parse tree
	 */
	void exitErr_NICKNAMEINUSE(GrammarParser.Err_NICKNAMEINUSEContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#channel_user_name}.
	 * @param ctx the parse tree
	 */
	void enterChannel_user_name(GrammarParser.Channel_user_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#channel_user_name}.
	 * @param ctx the parse tree
	 */
	void exitChannel_user_name(GrammarParser.Channel_user_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#host}.
	 * @param ctx the parse tree
	 */
	void enterHost(GrammarParser.HostContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#host}.
	 * @param ctx the parse tree
	 */
	void exitHost(GrammarParser.HostContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#user}.
	 * @param ctx the parse tree
	 */
	void enterUser(GrammarParser.UserContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#user}.
	 * @param ctx the parse tree
	 */
	void exitUser(GrammarParser.UserContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(GrammarParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(GrammarParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void enterDelimiter(GrammarParser.DelimiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#delimiter}.
	 * @param ctx the parse tree
	 */
	void exitDelimiter(GrammarParser.DelimiterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#address}.
	 * @param ctx the parse tree
	 */
	void enterAddress(GrammarParser.AddressContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#address}.
	 * @param ctx the parse tree
	 */
	void exitAddress(GrammarParser.AddressContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(GrammarParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(GrammarParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#target}.
	 * @param ctx the parse tree
	 */
	void enterTarget(GrammarParser.TargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#target}.
	 * @param ctx the parse tree
	 */
	void exitTarget(GrammarParser.TargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#fullname}.
	 * @param ctx the parse tree
	 */
	void enterFullname(GrammarParser.FullnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#fullname}.
	 * @param ctx the parse tree
	 */
	void exitFullname(GrammarParser.FullnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#server_version}.
	 * @param ctx the parse tree
	 */
	void enterServer_version(GrammarParser.Server_versionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#server_version}.
	 * @param ctx the parse tree
	 */
	void exitServer_version(GrammarParser.Server_versionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#motd}.
	 * @param ctx the parse tree
	 */
	void enterMotd(GrammarParser.MotdContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#motd}.
	 * @param ctx the parse tree
	 */
	void exitMotd(GrammarParser.MotdContext ctx);
}