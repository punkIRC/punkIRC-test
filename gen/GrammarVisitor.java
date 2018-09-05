// Generated from D:/Projekte/Pricefield/src/main/antlr/de.rubenmaurer.punk\Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(GrammarParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#server_response}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServer_response(GrammarParser.Server_responseContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#server}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServer(GrammarParser.ServerContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(GrammarParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#nick}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNick(GrammarParser.NickContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#welcome}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWelcome(GrammarParser.WelcomeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#unknown}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknown(GrammarParser.UnknownContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#noSuchNick}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoSuchNick(GrammarParser.NoSuchNickContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#ping}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPing(GrammarParser.PingContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#private_message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivate_message(GrammarParser.Private_messageContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#notice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotice(GrammarParser.NoticeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_WHOISUSER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_WHOISUSER(GrammarParser.Rpl_WHOISUSERContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_WHOISSERVER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_WHOISSERVER(GrammarParser.Rpl_WHOISSERVERContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_ENDOFWHOIS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_ENDOFWHOIS(GrammarParser.Rpl_ENDOFWHOISContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_LUSERCLIENT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_LUSERCLIENT(GrammarParser.Rpl_LUSERCLIENTContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_LUSEROP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_LUSEROP(GrammarParser.Rpl_LUSEROPContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_LUSERUNKNOWN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_LUSERUNKNOWN(GrammarParser.Rpl_LUSERUNKNOWNContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_LUSERCHANNELS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_LUSERCHANNELS(GrammarParser.Rpl_LUSERCHANNELSContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_LUSERME}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_LUSERME(GrammarParser.Rpl_LUSERMEContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_MOTDSTART}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_MOTDSTART(GrammarParser.Rpl_MOTDSTARTContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_MOTD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_MOTD(GrammarParser.Rpl_MOTDContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_ENDOFMOTD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_ENDOFMOTD(GrammarParser.Rpl_ENDOFMOTDContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#rpl_QUIT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpl_QUIT(GrammarParser.Rpl_QUITContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#join_reply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_reply(GrammarParser.Join_replyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#join_name_reply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_name_reply(GrammarParser.Join_name_replyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#join_name_end_reply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin_name_end_reply(GrammarParser.Join_name_end_replyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#no_such_channel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNo_such_channel(GrammarParser.No_such_channelContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#cannot_send_to_chnl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCannot_send_to_chnl(GrammarParser.Cannot_send_to_chnlContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#err_NOMOTD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErr_NOMOTD(GrammarParser.Err_NOMOTDContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#err_NICKNAMEINUSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErr_NICKNAMEINUSE(GrammarParser.Err_NICKNAMEINUSEContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#channel_user_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannel_user_name(GrammarParser.Channel_user_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#host}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHost(GrammarParser.HostContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#user}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUser(GrammarParser.UserContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(GrammarParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#delimiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelimiter(GrammarParser.DelimiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#address}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddress(GrammarParser.AddressContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(GrammarParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget(GrammarParser.TargetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#fullname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullname(GrammarParser.FullnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#server_version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServer_version(GrammarParser.Server_versionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#motd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMotd(GrammarParser.MotdContext ctx);
}