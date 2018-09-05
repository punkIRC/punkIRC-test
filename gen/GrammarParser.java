// Generated from D:/Projekte/Pricefield/src/main/antlr/de.rubenmaurer.punk\Grammar.g4 by ANTLR 4.7
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, WHITESPACE=31, 
		PRIVMSG=32, NOTICE=33, TARGET=34, CHANNEL=35, WORD=36, TEXT=37, PLAIN=38, 
		CLIENT=39, SPECIAL=40, CHAR=41, INTEGER=42, DELIMITER=43, NUMBER=44, NAME=45;
	public static final int
		RULE_def = 0, RULE_server_response = 1, RULE_server = 2, RULE_code = 3, 
		RULE_nick = 4, RULE_welcome = 5, RULE_unknown = 6, RULE_noSuchNick = 7, 
		RULE_ping = 8, RULE_private_message = 9, RULE_notice = 10, RULE_rpl_WHOISUSER = 11, 
		RULE_rpl_WHOISSERVER = 12, RULE_rpl_ENDOFWHOIS = 13, RULE_rpl_LUSERCLIENT = 14, 
		RULE_rpl_LUSEROP = 15, RULE_rpl_LUSERUNKNOWN = 16, RULE_rpl_LUSERCHANNELS = 17, 
		RULE_rpl_LUSERME = 18, RULE_rpl_MOTDSTART = 19, RULE_rpl_MOTD = 20, RULE_rpl_ENDOFMOTD = 21, 
		RULE_rpl_QUIT = 22, RULE_join_reply = 23, RULE_join_name_reply = 24, RULE_join_name_end_reply = 25, 
		RULE_no_such_channel = 26, RULE_cannot_send_to_chnl = 27, RULE_err_NOMOTD = 28, 
		RULE_err_NICKNAMEINUSE = 29, RULE_channel_user_name = 30, RULE_host = 31, 
		RULE_user = 32, RULE_message = 33, RULE_delimiter = 34, RULE_address = 35, 
		RULE_command = 36, RULE_target = 37, RULE_fullname = 38, RULE_server_version = 39, 
		RULE_motd = 40;
	public static final String[] ruleNames = {
		"def", "server_response", "server", "code", "nick", "welcome", "unknown", 
		"noSuchNick", "ping", "private_message", "notice", "rpl_WHOISUSER", "rpl_WHOISSERVER", 
		"rpl_ENDOFWHOIS", "rpl_LUSERCLIENT", "rpl_LUSEROP", "rpl_LUSERUNKNOWN", 
		"rpl_LUSERCHANNELS", "rpl_LUSERME", "rpl_MOTDSTART", "rpl_MOTD", "rpl_ENDOFMOTD", 
		"rpl_QUIT", "join_reply", "join_name_reply", "join_name_end_reply", "no_such_channel", 
		"cannot_send_to_chnl", "err_NOMOTD", "err_NICKNAMEINUSE", "channel_user_name", 
		"host", "user", "message", "delimiter", "address", "command", "target", 
		"fullname", "server_version", "motd"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'PRICEFIELD'", "'Welcome to the Internet Relay Network'", "'Unknown command'", 
		"'No such nick/channel'", "'PONG'", "'*'", "'End of WHOIS list'", "'There are'", 
		"'users and'", "'services on'", "'servers'", "'operator(s) online'", "'unknown connection(s)'", 
		"'channels formed'", "'I have'", "'clients and'", "'-'", "'Message of the day'", 
		"'End of MOTD command'", "'ERROR'", "'Closing Link'", "'('", "')'", "'JOIN'", 
		"'='", "'End of NAMES list'", "'No such channel'", "'Cannot send to channel'", 
		"'MOTD File is missing'", "'Nickname is already in use'", null, null, 
		null, null, null, null, null, null, null, null, null, null, "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "WHITESPACE", "PRIVMSG", "NOTICE", 
		"TARGET", "CHANNEL", "WORD", "TEXT", "PLAIN", "CLIENT", "SPECIAL", "CHAR", 
		"INTEGER", "DELIMITER", "NUMBER", "NAME"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DefContext extends ParserRuleContext {
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Server_responseContext extends ParserRuleContext {
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public Server_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_server_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterServer_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitServer_response(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitServer_response(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Server_responseContext server_response() throws RecognitionException {
		Server_responseContext _localctx = new Server_responseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_server_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(DELIMITER);
			setState(85);
			server();
			setState(86);
			match(WHITESPACE);
			setState(87);
			code();
			setState(88);
			match(WHITESPACE);
			setState(89);
			nick();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServerContext extends ParserRuleContext {
		public List<TerminalNode> CHAR() { return getTokens(GrammarParser.CHAR); }
		public TerminalNode CHAR(int i) {
			return getToken(GrammarParser.CHAR, i);
		}
		public List<TerminalNode> SPECIAL() { return getTokens(GrammarParser.SPECIAL); }
		public TerminalNode SPECIAL(int i) {
			return getToken(GrammarParser.SPECIAL, i);
		}
		public ServerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_server; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterServer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitServer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitServer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServerContext server() throws RecognitionException {
		ServerContext _localctx = new ServerContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_server);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(91);
				_la = _input.LA(1);
				if ( !(_la==SPECIAL || _la==CHAR) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SPECIAL || _la==CHAR );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(GrammarParser.INTEGER, 0); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_code);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NickContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public NickContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nick; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNick(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNick(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNick(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NickContext nick() throws RecognitionException {
		NickContext _localctx = new NickContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nick);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WelcomeContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public AddressContext address() {
			return getRuleContext(AddressContext.class,0);
		}
		public WelcomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_welcome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterWelcome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitWelcome(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitWelcome(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WelcomeContext welcome() throws RecognitionException {
		WelcomeContext _localctx = new WelcomeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_welcome);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(DELIMITER);
			setState(101);
			server();
			setState(102);
			match(WHITESPACE);
			setState(103);
			code();
			setState(104);
			match(WHITESPACE);
			setState(105);
			nick();
			setState(106);
			match(WHITESPACE);
			setState(107);
			match(DELIMITER);
			setState(108);
			match(T__1);
			setState(109);
			match(WHITESPACE);
			setState(110);
			address();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnknownContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public UnknownContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unknown; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterUnknown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitUnknown(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitUnknown(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnknownContext unknown() throws RecognitionException {
		UnknownContext _localctx = new UnknownContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unknown);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(DELIMITER);
			setState(113);
			server();
			setState(114);
			match(WHITESPACE);
			setState(115);
			code();
			setState(116);
			match(WHITESPACE);
			setState(117);
			nick();
			setState(118);
			match(WHITESPACE);
			setState(119);
			command();
			setState(120);
			match(WHITESPACE);
			setState(121);
			match(DELIMITER);
			setState(122);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoSuchNickContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public NoSuchNickContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noSuchNick; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNoSuchNick(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNoSuchNick(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNoSuchNick(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoSuchNickContext noSuchNick() throws RecognitionException {
		NoSuchNickContext _localctx = new NoSuchNickContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_noSuchNick);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(DELIMITER);
			setState(125);
			server();
			setState(126);
			match(WHITESPACE);
			setState(127);
			code();
			setState(128);
			match(WHITESPACE);
			setState(129);
			nick();
			setState(130);
			match(WHITESPACE);
			setState(131);
			target();
			setState(132);
			match(WHITESPACE);
			setState(133);
			match(DELIMITER);
			setState(134);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PingContext extends ParserRuleContext {
		public PingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ping; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitPing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PingContext ping() throws RecognitionException {
		PingContext _localctx = new PingContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ping);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Private_messageContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public TerminalNode CLIENT() { return getToken(GrammarParser.CLIENT, 0); }
		public TerminalNode PRIVMSG() { return getToken(GrammarParser.PRIVMSG, 0); }
		public TerminalNode PLAIN() { return getToken(GrammarParser.PLAIN, 0); }
		public Private_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_private_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterPrivate_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitPrivate_message(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitPrivate_message(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Private_messageContext private_message() throws RecognitionException {
		Private_messageContext _localctx = new Private_messageContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_private_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(DELIMITER);
			setState(139);
			match(CLIENT);
			setState(140);
			match(PRIVMSG);
			setState(141);
			match(DELIMITER);
			setState(142);
			match(PLAIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoticeContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public TerminalNode CLIENT() { return getToken(GrammarParser.CLIENT, 0); }
		public TerminalNode NOTICE() { return getToken(GrammarParser.NOTICE, 0); }
		public TerminalNode PLAIN() { return getToken(GrammarParser.PLAIN, 0); }
		public NoticeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNotice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNotice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNotice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoticeContext notice() throws RecognitionException {
		NoticeContext _localctx = new NoticeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_notice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(DELIMITER);
			setState(145);
			match(CLIENT);
			setState(146);
			match(NOTICE);
			setState(147);
			match(DELIMITER);
			setState(148);
			match(PLAIN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_WHOISUSERContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public UserContext user() {
			return getRuleContext(UserContext.class,0);
		}
		public HostContext host() {
			return getRuleContext(HostContext.class,0);
		}
		public FullnameContext fullname() {
			return getRuleContext(FullnameContext.class,0);
		}
		public Rpl_WHOISUSERContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_WHOISUSER; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_WHOISUSER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_WHOISUSER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_WHOISUSER(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_WHOISUSERContext rpl_WHOISUSER() throws RecognitionException {
		Rpl_WHOISUSERContext _localctx = new Rpl_WHOISUSERContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rpl_WHOISUSER);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(DELIMITER);
			setState(151);
			server();
			setState(152);
			match(WHITESPACE);
			setState(153);
			code();
			setState(154);
			match(WHITESPACE);
			setState(155);
			nick();
			setState(156);
			match(WHITESPACE);
			setState(157);
			target();
			setState(158);
			match(WHITESPACE);
			setState(159);
			user();
			setState(160);
			match(WHITESPACE);
			setState(161);
			host();
			setState(162);
			match(WHITESPACE);
			setState(163);
			match(T__5);
			setState(164);
			match(WHITESPACE);
			setState(165);
			match(DELIMITER);
			setState(166);
			fullname();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_WHOISSERVERContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public List<ServerContext> server() {
			return getRuleContexts(ServerContext.class);
		}
		public ServerContext server(int i) {
			return getRuleContext(ServerContext.class,i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public Server_versionContext server_version() {
			return getRuleContext(Server_versionContext.class,0);
		}
		public Rpl_WHOISSERVERContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_WHOISSERVER; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_WHOISSERVER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_WHOISSERVER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_WHOISSERVER(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_WHOISSERVERContext rpl_WHOISSERVER() throws RecognitionException {
		Rpl_WHOISSERVERContext _localctx = new Rpl_WHOISSERVERContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_rpl_WHOISSERVER);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(DELIMITER);
			setState(169);
			server();
			setState(170);
			match(WHITESPACE);
			setState(171);
			code();
			setState(172);
			match(WHITESPACE);
			setState(173);
			nick();
			setState(174);
			match(WHITESPACE);
			setState(175);
			target();
			setState(176);
			match(WHITESPACE);
			setState(177);
			server();
			setState(178);
			match(WHITESPACE);
			setState(179);
			match(DELIMITER);
			setState(180);
			server_version();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_ENDOFWHOISContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public Rpl_ENDOFWHOISContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_ENDOFWHOIS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_ENDOFWHOIS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_ENDOFWHOIS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_ENDOFWHOIS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_ENDOFWHOISContext rpl_ENDOFWHOIS() throws RecognitionException {
		Rpl_ENDOFWHOISContext _localctx = new Rpl_ENDOFWHOISContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_rpl_ENDOFWHOIS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(DELIMITER);
			setState(183);
			server();
			setState(184);
			match(WHITESPACE);
			setState(185);
			code();
			setState(186);
			match(WHITESPACE);
			setState(187);
			nick();
			setState(188);
			match(WHITESPACE);
			setState(189);
			target();
			setState(190);
			match(WHITESPACE);
			setState(191);
			match(DELIMITER);
			setState(192);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_LUSERCLIENTContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(GrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(GrammarParser.NUMBER, i);
		}
		public Rpl_LUSERCLIENTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_LUSERCLIENT; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_LUSERCLIENT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_LUSERCLIENT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_LUSERCLIENT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_LUSERCLIENTContext rpl_LUSERCLIENT() throws RecognitionException {
		Rpl_LUSERCLIENTContext _localctx = new Rpl_LUSERCLIENTContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_rpl_LUSERCLIENT);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(DELIMITER);
			setState(195);
			server();
			setState(196);
			match(WHITESPACE);
			setState(197);
			code();
			setState(198);
			match(WHITESPACE);
			setState(199);
			nick();
			setState(200);
			match(WHITESPACE);
			setState(201);
			match(DELIMITER);
			setState(202);
			match(T__7);
			setState(203);
			match(WHITESPACE);
			setState(204);
			match(NUMBER);
			setState(205);
			match(WHITESPACE);
			setState(206);
			match(T__8);
			setState(207);
			match(WHITESPACE);
			setState(208);
			match(NUMBER);
			setState(209);
			match(WHITESPACE);
			setState(210);
			match(T__9);
			setState(211);
			match(WHITESPACE);
			setState(212);
			match(NUMBER);
			setState(213);
			match(WHITESPACE);
			setState(214);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_LUSEROPContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(GrammarParser.NUMBER, 0); }
		public Rpl_LUSEROPContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_LUSEROP; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_LUSEROP(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_LUSEROP(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_LUSEROP(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_LUSEROPContext rpl_LUSEROP() throws RecognitionException {
		Rpl_LUSEROPContext _localctx = new Rpl_LUSEROPContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_rpl_LUSEROP);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(DELIMITER);
			setState(217);
			server();
			setState(218);
			match(WHITESPACE);
			setState(219);
			code();
			setState(220);
			match(WHITESPACE);
			setState(221);
			nick();
			setState(222);
			match(WHITESPACE);
			setState(223);
			match(NUMBER);
			setState(224);
			match(WHITESPACE);
			setState(225);
			match(DELIMITER);
			setState(226);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_LUSERUNKNOWNContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(GrammarParser.NUMBER, 0); }
		public Rpl_LUSERUNKNOWNContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_LUSERUNKNOWN; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_LUSERUNKNOWN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_LUSERUNKNOWN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_LUSERUNKNOWN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_LUSERUNKNOWNContext rpl_LUSERUNKNOWN() throws RecognitionException {
		Rpl_LUSERUNKNOWNContext _localctx = new Rpl_LUSERUNKNOWNContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_rpl_LUSERUNKNOWN);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(DELIMITER);
			setState(229);
			server();
			setState(230);
			match(WHITESPACE);
			setState(231);
			code();
			setState(232);
			match(WHITESPACE);
			setState(233);
			nick();
			setState(234);
			match(WHITESPACE);
			setState(235);
			match(NUMBER);
			setState(236);
			match(WHITESPACE);
			setState(237);
			match(DELIMITER);
			setState(238);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_LUSERCHANNELSContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(GrammarParser.NUMBER, 0); }
		public Rpl_LUSERCHANNELSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_LUSERCHANNELS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_LUSERCHANNELS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_LUSERCHANNELS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_LUSERCHANNELS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_LUSERCHANNELSContext rpl_LUSERCHANNELS() throws RecognitionException {
		Rpl_LUSERCHANNELSContext _localctx = new Rpl_LUSERCHANNELSContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_rpl_LUSERCHANNELS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(DELIMITER);
			setState(241);
			server();
			setState(242);
			match(WHITESPACE);
			setState(243);
			code();
			setState(244);
			match(WHITESPACE);
			setState(245);
			nick();
			setState(246);
			match(WHITESPACE);
			setState(247);
			match(NUMBER);
			setState(248);
			match(WHITESPACE);
			setState(249);
			match(DELIMITER);
			setState(250);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_LUSERMEContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public List<TerminalNode> NUMBER() { return getTokens(GrammarParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(GrammarParser.NUMBER, i);
		}
		public Rpl_LUSERMEContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_LUSERME; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_LUSERME(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_LUSERME(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_LUSERME(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_LUSERMEContext rpl_LUSERME() throws RecognitionException {
		Rpl_LUSERMEContext _localctx = new Rpl_LUSERMEContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_rpl_LUSERME);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(DELIMITER);
			setState(253);
			server();
			setState(254);
			match(WHITESPACE);
			setState(255);
			code();
			setState(256);
			match(WHITESPACE);
			setState(257);
			nick();
			setState(258);
			match(WHITESPACE);
			setState(259);
			match(DELIMITER);
			setState(260);
			match(T__14);
			setState(261);
			match(WHITESPACE);
			setState(262);
			match(NUMBER);
			setState(263);
			match(WHITESPACE);
			setState(264);
			match(T__15);
			setState(265);
			match(WHITESPACE);
			setState(266);
			match(NUMBER);
			setState(267);
			match(WHITESPACE);
			setState(268);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_MOTDSTARTContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public List<ServerContext> server() {
			return getRuleContexts(ServerContext.class);
		}
		public ServerContext server(int i) {
			return getRuleContext(ServerContext.class,i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public Rpl_MOTDSTARTContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_MOTDSTART; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_MOTDSTART(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_MOTDSTART(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_MOTDSTART(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_MOTDSTARTContext rpl_MOTDSTART() throws RecognitionException {
		Rpl_MOTDSTARTContext _localctx = new Rpl_MOTDSTARTContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_rpl_MOTDSTART);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(DELIMITER);
			setState(271);
			server();
			setState(272);
			match(WHITESPACE);
			setState(273);
			code();
			setState(274);
			match(WHITESPACE);
			setState(275);
			nick();
			setState(276);
			match(WHITESPACE);
			setState(277);
			match(DELIMITER);
			setState(278);
			match(T__16);
			setState(279);
			match(WHITESPACE);
			setState(280);
			server();
			setState(281);
			match(WHITESPACE);
			setState(282);
			match(T__17);
			setState(283);
			match(WHITESPACE);
			setState(284);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_MOTDContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public MotdContext motd() {
			return getRuleContext(MotdContext.class,0);
		}
		public Rpl_MOTDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_MOTD; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_MOTD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_MOTD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_MOTD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_MOTDContext rpl_MOTD() throws RecognitionException {
		Rpl_MOTDContext _localctx = new Rpl_MOTDContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_rpl_MOTD);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(DELIMITER);
			setState(287);
			server();
			setState(288);
			match(WHITESPACE);
			setState(289);
			code();
			setState(290);
			match(WHITESPACE);
			setState(291);
			nick();
			setState(292);
			match(WHITESPACE);
			setState(293);
			match(DELIMITER);
			setState(294);
			match(T__16);
			setState(295);
			match(WHITESPACE);
			setState(296);
			motd();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_ENDOFMOTDContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public Rpl_ENDOFMOTDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_ENDOFMOTD; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_ENDOFMOTD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_ENDOFMOTD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_ENDOFMOTD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_ENDOFMOTDContext rpl_ENDOFMOTD() throws RecognitionException {
		Rpl_ENDOFMOTDContext _localctx = new Rpl_ENDOFMOTDContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_rpl_ENDOFMOTD);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(DELIMITER);
			setState(299);
			server();
			setState(300);
			match(WHITESPACE);
			setState(301);
			code();
			setState(302);
			match(WHITESPACE);
			setState(303);
			nick();
			setState(304);
			match(WHITESPACE);
			setState(305);
			match(DELIMITER);
			setState(306);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rpl_QUITContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public MessageContext message() {
			return getRuleContext(MessageContext.class,0);
		}
		public Rpl_QUITContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rpl_QUIT; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterRpl_QUIT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitRpl_QUIT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitRpl_QUIT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rpl_QUITContext rpl_QUIT() throws RecognitionException {
		Rpl_QUITContext _localctx = new Rpl_QUITContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rpl_QUIT);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(T__19);
			setState(309);
			match(WHITESPACE);
			setState(310);
			match(DELIMITER);
			setState(311);
			match(T__20);
			setState(312);
			match(DELIMITER);
			setState(313);
			match(WHITESPACE);
			setState(314);
			server();
			setState(315);
			match(WHITESPACE);
			setState(316);
			match(T__21);
			setState(317);
			message();
			setState(318);
			match(T__22);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_replyContext extends ParserRuleContext {
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public Join_replyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_reply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterJoin_reply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitJoin_reply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitJoin_reply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_replyContext join_reply() throws RecognitionException {
		Join_replyContext _localctx = new Join_replyContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_join_reply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(DELIMITER);
			setState(321);
			match(WORD);
			setState(322);
			match(T__23);
			setState(323);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_name_replyContext extends ParserRuleContext {
		public Server_responseContext server_response() {
			return getRuleContext(Server_responseContext.class,0);
		}
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public Join_name_replyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_name_reply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterJoin_name_reply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitJoin_name_reply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitJoin_name_reply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_name_replyContext join_name_reply() throws RecognitionException {
		Join_name_replyContext _localctx = new Join_name_replyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_join_name_reply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			server_response();
			setState(326);
			match(T__24);
			setState(327);
			match(WORD);
			setState(328);
			match(DELIMITER);
			setState(329);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Join_name_end_replyContext extends ParserRuleContext {
		public Server_responseContext server_response() {
			return getRuleContext(Server_responseContext.class,0);
		}
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public Join_name_end_replyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_name_end_reply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterJoin_name_end_reply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitJoin_name_end_reply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitJoin_name_end_reply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Join_name_end_replyContext join_name_end_reply() throws RecognitionException {
		Join_name_end_replyContext _localctx = new Join_name_end_replyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_join_name_end_reply);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			server_response();
			setState(332);
			match(WORD);
			setState(333);
			match(DELIMITER);
			setState(334);
			match(T__25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class No_such_channelContext extends ParserRuleContext {
		public Server_responseContext server_response() {
			return getRuleContext(Server_responseContext.class,0);
		}
		public TerminalNode WHITESPACE() { return getToken(GrammarParser.WHITESPACE, 0); }
		public TerminalNode CHANNEL() { return getToken(GrammarParser.CHANNEL, 0); }
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public No_such_channelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_such_channel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterNo_such_channel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitNo_such_channel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitNo_such_channel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final No_such_channelContext no_such_channel() throws RecognitionException {
		No_such_channelContext _localctx = new No_such_channelContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_no_such_channel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			server_response();
			setState(337);
			match(WHITESPACE);
			setState(338);
			match(CHANNEL);
			setState(339);
			match(DELIMITER);
			setState(340);
			match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cannot_send_to_chnlContext extends ParserRuleContext {
		public Server_responseContext server_response() {
			return getRuleContext(Server_responseContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public TerminalNode CHANNEL() { return getToken(GrammarParser.CHANNEL, 0); }
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public Cannot_send_to_chnlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cannot_send_to_chnl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCannot_send_to_chnl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCannot_send_to_chnl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCannot_send_to_chnl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cannot_send_to_chnlContext cannot_send_to_chnl() throws RecognitionException {
		Cannot_send_to_chnlContext _localctx = new Cannot_send_to_chnlContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_cannot_send_to_chnl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			server_response();
			setState(343);
			match(WHITESPACE);
			setState(344);
			match(CHANNEL);
			setState(345);
			match(WHITESPACE);
			setState(346);
			match(DELIMITER);
			setState(347);
			match(T__27);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Err_NOMOTDContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public Err_NOMOTDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_err_NOMOTD; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterErr_NOMOTD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitErr_NOMOTD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitErr_NOMOTD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Err_NOMOTDContext err_NOMOTD() throws RecognitionException {
		Err_NOMOTDContext _localctx = new Err_NOMOTDContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_err_NOMOTD);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(DELIMITER);
			setState(350);
			server();
			setState(351);
			match(WHITESPACE);
			setState(352);
			code();
			setState(353);
			match(WHITESPACE);
			setState(354);
			nick();
			setState(355);
			match(WHITESPACE);
			setState(356);
			match(DELIMITER);
			setState(357);
			match(T__28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Err_NICKNAMEINUSEContext extends ParserRuleContext {
		public List<TerminalNode> DELIMITER() { return getTokens(GrammarParser.DELIMITER); }
		public TerminalNode DELIMITER(int i) {
			return getToken(GrammarParser.DELIMITER, i);
		}
		public ServerContext server() {
			return getRuleContext(ServerContext.class,0);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public NickContext nick() {
			return getRuleContext(NickContext.class,0);
		}
		public Err_NICKNAMEINUSEContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_err_NICKNAMEINUSE; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterErr_NICKNAMEINUSE(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitErr_NICKNAMEINUSE(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitErr_NICKNAMEINUSE(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Err_NICKNAMEINUSEContext err_NICKNAMEINUSE() throws RecognitionException {
		Err_NICKNAMEINUSEContext _localctx = new Err_NICKNAMEINUSEContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_err_NICKNAMEINUSE);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(DELIMITER);
			setState(360);
			server();
			setState(361);
			match(WHITESPACE);
			setState(362);
			code();
			setState(363);
			match(WHITESPACE);
			setState(364);
			match(T__5);
			setState(365);
			match(WHITESPACE);
			setState(366);
			nick();
			setState(367);
			match(WHITESPACE);
			setState(368);
			match(DELIMITER);
			setState(369);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Channel_user_nameContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public Channel_user_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_channel_user_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterChannel_user_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitChannel_user_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitChannel_user_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Channel_user_nameContext channel_user_name() throws RecognitionException {
		Channel_user_nameContext _localctx = new Channel_user_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_channel_user_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HostContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public HostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_host; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterHost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitHost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitHost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HostContext host() throws RecognitionException {
		HostContext _localctx = new HostContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_host);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public UserContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_user; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterUser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitUser(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitUser(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserContext user() throws RecognitionException {
		UserContext _localctx = new UserContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_user);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MessageContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_message);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(377);
					_la = _input.LA(1);
					if ( !(_la==WHITESPACE || _la==WORD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(380); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DelimiterContext extends ParserRuleContext {
		public TerminalNode DELIMITER() { return getToken(GrammarParser.DELIMITER, 0); }
		public DelimiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delimiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterDelimiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitDelimiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitDelimiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelimiterContext delimiter() throws RecognitionException {
		DelimiterContext _localctx = new DelimiterContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_delimiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(DELIMITER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddressContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public AddressContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_address; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterAddress(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitAddress(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitAddress(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddressContext address() throws RecognitionException {
		AddressContext _localctx = new AddressContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_address);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TargetContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(GrammarParser.WORD, 0); }
		public TargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TargetContext target() throws RecognitionException {
		TargetContext _localctx = new TargetContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_target);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FullnameContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public FullnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullname; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFullname(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFullname(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitFullname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullnameContext fullname() throws RecognitionException {
		FullnameContext _localctx = new FullnameContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_fullname);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(391); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(390);
					_la = _input.LA(1);
					if ( !(_la==WHITESPACE || _la==WORD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(393); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Server_versionContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public Server_versionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_server_version; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterServer_version(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitServer_version(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitServer_version(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Server_versionContext server_version() throws RecognitionException {
		Server_versionContext _localctx = new Server_versionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_server_version);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(396); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(395);
					_la = _input.LA(1);
					if ( !(_la==WHITESPACE || _la==WORD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(398); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MotdContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(GrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(GrammarParser.WORD, i);
		}
		public List<TerminalNode> WHITESPACE() { return getTokens(GrammarParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(GrammarParser.WHITESPACE, i);
		}
		public MotdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_motd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterMotd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitMotd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor ) return ((GrammarVisitor<? extends T>)visitor).visitMotd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MotdContext motd() throws RecognitionException {
		MotdContext _localctx = new MotdContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_motd);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401); 
			_errHandler.sync(this);
			_alt = 1+1;
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(400);
					_la = _input.LA(1);
					if ( !(_la==WHITESPACE || _la==WORD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(403); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u0198\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\6\4_\n\4\r\4\16\4`\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\6#\u017d\n#\r#\16"+
		"#\u017e\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\6(\u018a\n(\r(\16(\u018b\3)\6)\u018f"+
		"\n)\r)\16)\u0190\3*\6*\u0194\n*\r*\16*\u0195\3*\6\u017e\u018b\u0190\u0195"+
		"\2+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B"+
		"DFHJLNPR\2\4\3\2*+\4\2!!&&\2\u0173\2T\3\2\2\2\4V\3\2\2\2\6^\3\2\2\2\b"+
		"b\3\2\2\2\nd\3\2\2\2\ff\3\2\2\2\16r\3\2\2\2\20~\3\2\2\2\22\u008a\3\2\2"+
		"\2\24\u008c\3\2\2\2\26\u0092\3\2\2\2\30\u0098\3\2\2\2\32\u00aa\3\2\2\2"+
		"\34\u00b8\3\2\2\2\36\u00c4\3\2\2\2 \u00da\3\2\2\2\"\u00e6\3\2\2\2$\u00f2"+
		"\3\2\2\2&\u00fe\3\2\2\2(\u0110\3\2\2\2*\u0120\3\2\2\2,\u012c\3\2\2\2."+
		"\u0136\3\2\2\2\60\u0142\3\2\2\2\62\u0147\3\2\2\2\64\u014d\3\2\2\2\66\u0152"+
		"\3\2\2\28\u0158\3\2\2\2:\u015f\3\2\2\2<\u0169\3\2\2\2>\u0175\3\2\2\2@"+
		"\u0177\3\2\2\2B\u0179\3\2\2\2D\u017c\3\2\2\2F\u0180\3\2\2\2H\u0182\3\2"+
		"\2\2J\u0184\3\2\2\2L\u0186\3\2\2\2N\u0189\3\2\2\2P\u018e\3\2\2\2R\u0193"+
		"\3\2\2\2TU\7\3\2\2U\3\3\2\2\2VW\7-\2\2WX\5\6\4\2XY\7!\2\2YZ\5\b\5\2Z["+
		"\7!\2\2[\\\5\n\6\2\\\5\3\2\2\2]_\t\2\2\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2"+
		"`a\3\2\2\2a\7\3\2\2\2bc\7,\2\2c\t\3\2\2\2de\7&\2\2e\13\3\2\2\2fg\7-\2"+
		"\2gh\5\6\4\2hi\7!\2\2ij\5\b\5\2jk\7!\2\2kl\5\n\6\2lm\7!\2\2mn\7-\2\2n"+
		"o\7\4\2\2op\7!\2\2pq\5H%\2q\r\3\2\2\2rs\7-\2\2st\5\6\4\2tu\7!\2\2uv\5"+
		"\b\5\2vw\7!\2\2wx\5\n\6\2xy\7!\2\2yz\5J&\2z{\7!\2\2{|\7-\2\2|}\7\5\2\2"+
		"}\17\3\2\2\2~\177\7-\2\2\177\u0080\5\6\4\2\u0080\u0081\7!\2\2\u0081\u0082"+
		"\5\b\5\2\u0082\u0083\7!\2\2\u0083\u0084\5\n\6\2\u0084\u0085\7!\2\2\u0085"+
		"\u0086\5L\'\2\u0086\u0087\7!\2\2\u0087\u0088\7-\2\2\u0088\u0089\7\6\2"+
		"\2\u0089\21\3\2\2\2\u008a\u008b\7\7\2\2\u008b\23\3\2\2\2\u008c\u008d\7"+
		"-\2\2\u008d\u008e\7)\2\2\u008e\u008f\7\"\2\2\u008f\u0090\7-\2\2\u0090"+
		"\u0091\7(\2\2\u0091\25\3\2\2\2\u0092\u0093\7-\2\2\u0093\u0094\7)\2\2\u0094"+
		"\u0095\7#\2\2\u0095\u0096\7-\2\2\u0096\u0097\7(\2\2\u0097\27\3\2\2\2\u0098"+
		"\u0099\7-\2\2\u0099\u009a\5\6\4\2\u009a\u009b\7!\2\2\u009b\u009c\5\b\5"+
		"\2\u009c\u009d\7!\2\2\u009d\u009e\5\n\6\2\u009e\u009f\7!\2\2\u009f\u00a0"+
		"\5L\'\2\u00a0\u00a1\7!\2\2\u00a1\u00a2\5B\"\2\u00a2\u00a3\7!\2\2\u00a3"+
		"\u00a4\5@!\2\u00a4\u00a5\7!\2\2\u00a5\u00a6\7\b\2\2\u00a6\u00a7\7!\2\2"+
		"\u00a7\u00a8\7-\2\2\u00a8\u00a9\5N(\2\u00a9\31\3\2\2\2\u00aa\u00ab\7-"+
		"\2\2\u00ab\u00ac\5\6\4\2\u00ac\u00ad\7!\2\2\u00ad\u00ae\5\b\5\2\u00ae"+
		"\u00af\7!\2\2\u00af\u00b0\5\n\6\2\u00b0\u00b1\7!\2\2\u00b1\u00b2\5L\'"+
		"\2\u00b2\u00b3\7!\2\2\u00b3\u00b4\5\6\4\2\u00b4\u00b5\7!\2\2\u00b5\u00b6"+
		"\7-\2\2\u00b6\u00b7\5P)\2\u00b7\33\3\2\2\2\u00b8\u00b9\7-\2\2\u00b9\u00ba"+
		"\5\6\4\2\u00ba\u00bb\7!\2\2\u00bb\u00bc\5\b\5\2\u00bc\u00bd\7!\2\2\u00bd"+
		"\u00be\5\n\6\2\u00be\u00bf\7!\2\2\u00bf\u00c0\5L\'\2\u00c0\u00c1\7!\2"+
		"\2\u00c1\u00c2\7-\2\2\u00c2\u00c3\7\t\2\2\u00c3\35\3\2\2\2\u00c4\u00c5"+
		"\7-\2\2\u00c5\u00c6\5\6\4\2\u00c6\u00c7\7!\2\2\u00c7\u00c8\5\b\5\2\u00c8"+
		"\u00c9\7!\2\2\u00c9\u00ca\5\n\6\2\u00ca\u00cb\7!\2\2\u00cb\u00cc\7-\2"+
		"\2\u00cc\u00cd\7\n\2\2\u00cd\u00ce\7!\2\2\u00ce\u00cf\7.\2\2\u00cf\u00d0"+
		"\7!\2\2\u00d0\u00d1\7\13\2\2\u00d1\u00d2\7!\2\2\u00d2\u00d3\7.\2\2\u00d3"+
		"\u00d4\7!\2\2\u00d4\u00d5\7\f\2\2\u00d5\u00d6\7!\2\2\u00d6\u00d7\7.\2"+
		"\2\u00d7\u00d8\7!\2\2\u00d8\u00d9\7\r\2\2\u00d9\37\3\2\2\2\u00da\u00db"+
		"\7-\2\2\u00db\u00dc\5\6\4\2\u00dc\u00dd\7!\2\2\u00dd\u00de\5\b\5\2\u00de"+
		"\u00df\7!\2\2\u00df\u00e0\5\n\6\2\u00e0\u00e1\7!\2\2\u00e1\u00e2\7.\2"+
		"\2\u00e2\u00e3\7!\2\2\u00e3\u00e4\7-\2\2\u00e4\u00e5\7\16\2\2\u00e5!\3"+
		"\2\2\2\u00e6\u00e7\7-\2\2\u00e7\u00e8\5\6\4\2\u00e8\u00e9\7!\2\2\u00e9"+
		"\u00ea\5\b\5\2\u00ea\u00eb\7!\2\2\u00eb\u00ec\5\n\6\2\u00ec\u00ed\7!\2"+
		"\2\u00ed\u00ee\7.\2\2\u00ee\u00ef\7!\2\2\u00ef\u00f0\7-\2\2\u00f0\u00f1"+
		"\7\17\2\2\u00f1#\3\2\2\2\u00f2\u00f3\7-\2\2\u00f3\u00f4\5\6\4\2\u00f4"+
		"\u00f5\7!\2\2\u00f5\u00f6\5\b\5\2\u00f6\u00f7\7!\2\2\u00f7\u00f8\5\n\6"+
		"\2\u00f8\u00f9\7!\2\2\u00f9\u00fa\7.\2\2\u00fa\u00fb\7!\2\2\u00fb\u00fc"+
		"\7-\2\2\u00fc\u00fd\7\20\2\2\u00fd%\3\2\2\2\u00fe\u00ff\7-\2\2\u00ff\u0100"+
		"\5\6\4\2\u0100\u0101\7!\2\2\u0101\u0102\5\b\5\2\u0102\u0103\7!\2\2\u0103"+
		"\u0104\5\n\6\2\u0104\u0105\7!\2\2\u0105\u0106\7-\2\2\u0106\u0107\7\21"+
		"\2\2\u0107\u0108\7!\2\2\u0108\u0109\7.\2\2\u0109\u010a\7!\2\2\u010a\u010b"+
		"\7\22\2\2\u010b\u010c\7!\2\2\u010c\u010d\7.\2\2\u010d\u010e\7!\2\2\u010e"+
		"\u010f\7\r\2\2\u010f\'\3\2\2\2\u0110\u0111\7-\2\2\u0111\u0112\5\6\4\2"+
		"\u0112\u0113\7!\2\2\u0113\u0114\5\b\5\2\u0114\u0115\7!\2\2\u0115\u0116"+
		"\5\n\6\2\u0116\u0117\7!\2\2\u0117\u0118\7-\2\2\u0118\u0119\7\23\2\2\u0119"+
		"\u011a\7!\2\2\u011a\u011b\5\6\4\2\u011b\u011c\7!\2\2\u011c\u011d\7\24"+
		"\2\2\u011d\u011e\7!\2\2\u011e\u011f\7\23\2\2\u011f)\3\2\2\2\u0120\u0121"+
		"\7-\2\2\u0121\u0122\5\6\4\2\u0122\u0123\7!\2\2\u0123\u0124\5\b\5\2\u0124"+
		"\u0125\7!\2\2\u0125\u0126\5\n\6\2\u0126\u0127\7!\2\2\u0127\u0128\7-\2"+
		"\2\u0128\u0129\7\23\2\2\u0129\u012a\7!\2\2\u012a\u012b\5R*\2\u012b+\3"+
		"\2\2\2\u012c\u012d\7-\2\2\u012d\u012e\5\6\4\2\u012e\u012f\7!\2\2\u012f"+
		"\u0130\5\b\5\2\u0130\u0131\7!\2\2\u0131\u0132\5\n\6\2\u0132\u0133\7!\2"+
		"\2\u0133\u0134\7-\2\2\u0134\u0135\7\25\2\2\u0135-\3\2\2\2\u0136\u0137"+
		"\7\26\2\2\u0137\u0138\7!\2\2\u0138\u0139\7-\2\2\u0139\u013a\7\27\2\2\u013a"+
		"\u013b\7-\2\2\u013b\u013c\7!\2\2\u013c\u013d\5\6\4\2\u013d\u013e\7!\2"+
		"\2\u013e\u013f\7\30\2\2\u013f\u0140\5D#\2\u0140\u0141\7\31\2\2\u0141/"+
		"\3\2\2\2\u0142\u0143\7-\2\2\u0143\u0144\7&\2\2\u0144\u0145\7\32\2\2\u0145"+
		"\u0146\7&\2\2\u0146\61\3\2\2\2\u0147\u0148\5\4\3\2\u0148\u0149\7\33\2"+
		"\2\u0149\u014a\7&\2\2\u014a\u014b\7-\2\2\u014b\u014c\7&\2\2\u014c\63\3"+
		"\2\2\2\u014d\u014e\5\4\3\2\u014e\u014f\7&\2\2\u014f\u0150\7-\2\2\u0150"+
		"\u0151\7\34\2\2\u0151\65\3\2\2\2\u0152\u0153\5\4\3\2\u0153\u0154\7!\2"+
		"\2\u0154\u0155\7%\2\2\u0155\u0156\7-\2\2\u0156\u0157\7\35\2\2\u0157\67"+
		"\3\2\2\2\u0158\u0159\5\4\3\2\u0159\u015a\7!\2\2\u015a\u015b\7%\2\2\u015b"+
		"\u015c\7!\2\2\u015c\u015d\7-\2\2\u015d\u015e\7\36\2\2\u015e9\3\2\2\2\u015f"+
		"\u0160\7-\2\2\u0160\u0161\5\6\4\2\u0161\u0162\7!\2\2\u0162\u0163\5\b\5"+
		"\2\u0163\u0164\7!\2\2\u0164\u0165\5\n\6\2\u0165\u0166\7!\2\2\u0166\u0167"+
		"\7-\2\2\u0167\u0168\7\37\2\2\u0168;\3\2\2\2\u0169\u016a\7-\2\2\u016a\u016b"+
		"\5\6\4\2\u016b\u016c\7!\2\2\u016c\u016d\5\b\5\2\u016d\u016e\7!\2\2\u016e"+
		"\u016f\7\b\2\2\u016f\u0170\7!\2\2\u0170\u0171\5\n\6\2\u0171\u0172\7!\2"+
		"\2\u0172\u0173\7-\2\2\u0173\u0174\7 \2\2\u0174=\3\2\2\2\u0175\u0176\7"+
		"&\2\2\u0176?\3\2\2\2\u0177\u0178\7&\2\2\u0178A\3\2\2\2\u0179\u017a\7&"+
		"\2\2\u017aC\3\2\2\2\u017b\u017d\t\3\2\2\u017c\u017b\3\2\2\2\u017d\u017e"+
		"\3\2\2\2\u017e\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017fE\3\2\2\2\u0180"+
		"\u0181\7-\2\2\u0181G\3\2\2\2\u0182\u0183\7&\2\2\u0183I\3\2\2\2\u0184\u0185"+
		"\7&\2\2\u0185K\3\2\2\2\u0186\u0187\7&\2\2\u0187M\3\2\2\2\u0188\u018a\t"+
		"\3\2\2\u0189\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018b"+
		"\u0189\3\2\2\2\u018cO\3\2\2\2\u018d\u018f\t\3\2\2\u018e\u018d\3\2\2\2"+
		"\u018f\u0190\3\2\2\2\u0190\u0191\3\2\2\2\u0190\u018e\3\2\2\2\u0191Q\3"+
		"\2\2\2\u0192\u0194\t\3\2\2\u0193\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0195\u0193\3\2\2\2\u0196S\3\2\2\2\7`\u017e\u018b\u0190"+
		"\u0195";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}