// Generated from R.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__52=1, T__51=2, T__50=3, T__49=4, T__48=5, T__47=6, T__46=7, T__45=8, 
		T__44=9, T__43=10, T__42=11, T__41=12, T__40=13, T__39=14, T__38=15, T__37=16, 
		T__36=17, T__35=18, T__34=19, T__33=20, T__32=21, T__31=22, T__30=23, 
		T__29=24, T__28=25, T__27=26, T__26=27, T__25=28, T__24=29, T__23=30, 
		T__22=31, T__21=32, T__20=33, T__19=34, T__18=35, T__17=36, T__16=37, 
		T__15=38, T__14=39, T__13=40, T__12=41, T__11=42, T__10=43, T__9=44, T__8=45, 
		T__7=46, T__6=47, T__5=48, T__4=49, T__3=50, T__2=51, T__1=52, T__0=53, 
		HEX=54, INT=55, FLOAT=56, COMPLEX=57, STRING=58, ID=59, USER_OP=60, NL=61, 
		WS=62;
	public static final String[] tokenNames = {
		"<INVALID>", "'&'", "'['", "'*'", "'<'", "'!='", "'<='", "'<<-'", "'next'", 
		"'TRUE'", "'}'", "'[['", "'->'", "'->>'", "')'", "'NaN'", "'::'", "'@'", 
		"'='", "'repeat'", "'NA'", "'|'", "'!'", "']'", "'in'", "','", "'while'", 
		"'-'", "'('", "':'", "'if'", "'?'", "'{'", "'...'", "'break'", "'else'", 
		"'<-'", "'$'", "':::'", "'FALSE'", "'NULL'", "'function'", "'+'", "'for'", 
		"';'", "'&&'", "'||'", "'>'", "':='", "'/'", "'=='", "'Inf'", "'~'", "'>='", 
		"HEX", "INT", "FLOAT", "COMPLEX", "STRING", "ID", "USER_OP", "NL", "WS"
	};
	public static final int
		RULE_prog = 0, RULE_expr_or_assign = 1, RULE_expr = 2, RULE_exprlist = 3, 
		RULE_formlist = 4, RULE_form = 5, RULE_sublist = 6, RULE_sub = 7;
	public static final String[] ruleNames = {
		"prog", "expr_or_assign", "expr", "exprlist", "formlist", "form", "sublist", 
		"sub"
	};

	@Override
	public String getGrammarFileName() { return "R.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode EOF() { return getToken(RParser.EOF, 0); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public List<Expr_or_assignContext> expr_or_assign() {
			return getRuleContexts(Expr_or_assignContext.class);
		}
		public Expr_or_assignContext expr_or_assign(int i) {
			return getRuleContext(Expr_or_assignContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__45) | (1L << T__44) | (1L << T__38) | (1L << T__34) | (1L << T__33) | (1L << T__31) | (1L << T__27) | (1L << T__26) | (1L << T__25) | (1L << T__23) | (1L << T__22) | (1L << T__21) | (1L << T__19) | (1L << T__14) | (1L << T__13) | (1L << T__12) | (1L << T__11) | (1L << T__10) | (1L << T__2) | (1L << T__1) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID) | (1L << NL))) != 0)) {
				{
				setState(20);
				switch (_input.LA(1)) {
				case T__45:
				case T__44:
				case T__38:
				case T__34:
				case T__33:
				case T__31:
				case T__27:
				case T__26:
				case T__25:
				case T__23:
				case T__22:
				case T__21:
				case T__19:
				case T__14:
				case T__13:
				case T__12:
				case T__11:
				case T__10:
				case T__2:
				case T__1:
				case HEX:
				case INT:
				case FLOAT:
				case COMPLEX:
				case STRING:
				case ID:
					{
					setState(16); expr_or_assign();
					setState(17);
					_la = _input.LA(1);
					if ( !(_la==T__9 || _la==NL) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					break;
				case NL:
					{
					setState(19); match(NL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25); match(EOF);
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

	public static class Expr_or_assignContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_or_assignContext expr_or_assign() {
			return getRuleContext(Expr_or_assignContext.class,0);
		}
		public Expr_or_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_or_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExpr_or_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExpr_or_assign(this);
		}
	}

	public final Expr_or_assignContext expr_or_assign() throws RecognitionException {
		Expr_or_assignContext _localctx = new Expr_or_assignContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr_or_assign);
		int _la;
		try {
			setState(32);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(27); expr(0);
				setState(28);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__35) | (1L << T__17))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(29); expr_or_assign();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(31); expr(0);
				}
				break;
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

	public static class ExprContext extends ParserRuleContext {
		public ExprlistContext exprlist() {
			return getRuleContext(ExprlistContext.class,0);
		}
		public TerminalNode FLOAT() { return getToken(RParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(RParser.INT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FormlistContext formlist() {
			return getRuleContext(FormlistContext.class,0);
		}
		public SublistContext sublist() {
			return getRuleContext(SublistContext.class,0);
		}
		public TerminalNode STRING() { return getToken(RParser.STRING, 0); }
		public TerminalNode USER_OP() { return getToken(RParser.USER_OP, 0); }
		public TerminalNode COMPLEX() { return getToken(RParser.COMPLEX, 0); }
		public TerminalNode HEX() { return getToken(RParser.HEX, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(35);
				_la = _input.LA(1);
				if ( !(_la==T__26 || _la==T__11) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(36); expr(36);
				}
				break;
			case 2:
				{
				setState(37); match(T__31);
				setState(38); expr(30);
				}
				break;
			case 3:
				{
				setState(39); match(T__1);
				setState(40); expr(27);
				}
				break;
			case 4:
				{
				setState(41); match(T__12);
				setState(42); match(T__25);
				setState(44);
				_la = _input.LA(1);
				if (_la==T__20 || _la==ID) {
					{
					setState(43); formlist();
					}
				}

				setState(46); match(T__39);
				setState(47); expr(24);
				}
				break;
			case 5:
				{
				setState(48); match(T__34);
				setState(49); expr(17);
				}
				break;
			case 6:
				{
				setState(50); match(T__22);
				setState(51); expr(16);
				}
				break;
			case 7:
				{
				setState(52); match(T__21);
				setState(53); exprlist();
				setState(54); match(T__43);
				}
				break;
			case 8:
				{
				setState(56); match(T__23);
				setState(57); match(T__25);
				setState(58); expr(0);
				setState(59); match(T__39);
				setState(60); expr(0);
				}
				break;
			case 9:
				{
				setState(62); match(T__23);
				setState(63); match(T__25);
				setState(64); expr(0);
				setState(65); match(T__39);
				setState(66); expr(0);
				setState(67); match(T__18);
				setState(68); expr(0);
				}
				break;
			case 10:
				{
				setState(70); match(T__10);
				setState(71); match(T__25);
				setState(72); match(ID);
				setState(73); match(T__29);
				setState(74); expr(0);
				setState(75); match(T__39);
				setState(76); expr(0);
				}
				break;
			case 11:
				{
				setState(78); match(T__27);
				setState(79); match(T__25);
				setState(80); expr(0);
				setState(81); match(T__39);
				setState(82); expr(0);
				}
				break;
			case 12:
				{
				setState(84); match(T__45);
				}
				break;
			case 13:
				{
				setState(85); match(T__19);
				}
				break;
			case 14:
				{
				setState(86); match(T__25);
				setState(87); expr(0);
				setState(88); match(T__39);
				}
				break;
			case 15:
				{
				setState(90); match(ID);
				}
				break;
			case 16:
				{
				setState(91); match(STRING);
				}
				break;
			case 17:
				{
				setState(92); match(HEX);
				}
				break;
			case 18:
				{
				setState(93); match(INT);
				}
				break;
			case 19:
				{
				setState(94); match(FLOAT);
				}
				break;
			case 20:
				{
				setState(95); match(COMPLEX);
				}
				break;
			case 21:
				{
				setState(96); match(T__13);
				}
				break;
			case 22:
				{
				setState(97); match(T__33);
				}
				break;
			case 23:
				{
				setState(98); match(T__2);
				}
				break;
			case 24:
				{
				setState(99); match(T__38);
				}
				break;
			case 25:
				{
				setState(100); match(T__44);
				}
				break;
			case 26:
				{
				setState(101); match(T__14);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(155);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(153);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(104);
						if (!(precpred(_ctx, 38))) throw new FailedPredicateException(this, "precpred(_ctx, 38)");
						setState(105);
						_la = _input.LA(1);
						if ( !(_la==T__37 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(106); expr(39);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(107);
						if (!(precpred(_ctx, 37))) throw new FailedPredicateException(this, "precpred(_ctx, 37)");
						setState(108);
						_la = _input.LA(1);
						if ( !(_la==T__36 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(109); expr(38);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(110);
						if (!(precpred(_ctx, 35))) throw new FailedPredicateException(this, "precpred(_ctx, 35)");
						setState(111); match(T__24);
						setState(112); expr(36);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(113);
						if (!(precpred(_ctx, 34))) throw new FailedPredicateException(this, "precpred(_ctx, 34)");
						setState(114); match(USER_OP);
						setState(115); expr(35);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(116);
						if (!(precpred(_ctx, 33))) throw new FailedPredicateException(this, "precpred(_ctx, 33)");
						setState(117);
						_la = _input.LA(1);
						if ( !(_la==T__50 || _la==T__4) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(118); expr(34);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(119);
						if (!(precpred(_ctx, 32))) throw new FailedPredicateException(this, "precpred(_ctx, 32)");
						setState(120);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__11) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(121); expr(33);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(122);
						if (!(precpred(_ctx, 31))) throw new FailedPredicateException(this, "precpred(_ctx, 31)");
						setState(123);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__49) | (1L << T__48) | (1L << T__47) | (1L << T__6) | (1L << T__3) | (1L << T__0))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(124); expr(32);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(125);
						if (!(precpred(_ctx, 29))) throw new FailedPredicateException(this, "precpred(_ctx, 29)");
						setState(126);
						_la = _input.LA(1);
						if ( !(_la==T__52 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(127); expr(30);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(128);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(129);
						_la = _input.LA(1);
						if ( !(_la==T__32 || _la==T__7) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(130); expr(29);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(131);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(132); match(T__1);
						setState(133); expr(27);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(134);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(135);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__41) | (1L << T__40) | (1L << T__5))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(136); expr(26);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(137);
						if (!(precpred(_ctx, 40))) throw new FailedPredicateException(this, "precpred(_ctx, 40)");
						setState(138); match(T__42);
						setState(139); sublist();
						setState(140); match(T__30);
						setState(141); match(T__30);
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(143);
						if (!(precpred(_ctx, 39))) throw new FailedPredicateException(this, "precpred(_ctx, 39)");
						setState(144); match(T__51);
						setState(145); sublist();
						setState(146); match(T__30);
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(149); match(T__25);
						setState(150); sublist();
						setState(151); match(T__39);
						}
						break;
					}
					} 
				}
				setState(157);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExprlistContext extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(RParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(RParser.NL, i);
		}
		public List<Expr_or_assignContext> expr_or_assign() {
			return getRuleContexts(Expr_or_assignContext.class);
		}
		public Expr_or_assignContext expr_or_assign(int i) {
			return getRuleContext(Expr_or_assignContext.class,i);
		}
		public ExprlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterExprlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitExprlist(this);
		}
	}

	public final ExprlistContext exprlist() throws RecognitionException {
		ExprlistContext _localctx = new ExprlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprlist);
		int _la;
		try {
			setState(169);
			switch (_input.LA(1)) {
			case T__45:
			case T__44:
			case T__38:
			case T__34:
			case T__33:
			case T__31:
			case T__27:
			case T__26:
			case T__25:
			case T__23:
			case T__22:
			case T__21:
			case T__19:
			case T__14:
			case T__13:
			case T__12:
			case T__11:
			case T__10:
			case T__2:
			case T__1:
			case HEX:
			case INT:
			case FLOAT:
			case COMPLEX:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(158); expr_or_assign();
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9 || _la==NL) {
					{
					{
					setState(159);
					_la = _input.LA(1);
					if ( !(_la==T__9 || _la==NL) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(161);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__45) | (1L << T__44) | (1L << T__38) | (1L << T__34) | (1L << T__33) | (1L << T__31) | (1L << T__27) | (1L << T__26) | (1L << T__25) | (1L << T__23) | (1L << T__22) | (1L << T__21) | (1L << T__19) | (1L << T__14) | (1L << T__13) | (1L << T__12) | (1L << T__11) | (1L << T__10) | (1L << T__2) | (1L << T__1) | (1L << HEX) | (1L << INT) | (1L << FLOAT) | (1L << COMPLEX) | (1L << STRING) | (1L << ID))) != 0)) {
						{
						setState(160); expr_or_assign();
						}
					}

					}
					}
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__43:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class FormlistContext extends ParserRuleContext {
		public List<FormContext> form() {
			return getRuleContexts(FormContext.class);
		}
		public FormContext form(int i) {
			return getRuleContext(FormContext.class,i);
		}
		public FormlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterFormlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitFormlist(this);
		}
	}

	public final FormlistContext formlist() throws RecognitionException {
		FormlistContext _localctx = new FormlistContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_formlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); form();
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(172); match(T__28);
				setState(173); form();
				}
				}
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class FormContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public FormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_form; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitForm(this);
		}
	}

	public final FormContext form() throws RecognitionException {
		FormContext _localctx = new FormContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_form);
		try {
			setState(184);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(179); match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180); match(ID);
				setState(181); match(T__35);
				setState(182); expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(183); match(T__20);
				}
				break;
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

	public static class SublistContext extends ParserRuleContext {
		public SubContext sub(int i) {
			return getRuleContext(SubContext.class,i);
		}
		public List<SubContext> sub() {
			return getRuleContexts(SubContext.class);
		}
		public SublistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sublist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterSublist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitSublist(this);
		}
	}

	public final SublistContext sublist() throws RecognitionException {
		SublistContext _localctx = new SublistContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_sublist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); sub();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(187); match(T__28);
				setState(188); sub();
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class SubContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(RParser.ID, 0); }
		public TerminalNode STRING() { return getToken(RParser.STRING, 0); }
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RListener ) ((RListener)listener).exitSub(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sub);
		try {
			setState(212);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(194); expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(195); match(ID);
				setState(196); match(T__35);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(197); match(ID);
				setState(198); match(T__35);
				setState(199); expr(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(200); match(STRING);
				setState(201); match(T__35);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(202); match(STRING);
				setState(203); match(T__35);
				setState(204); expr(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(205); match(T__13);
				setState(206); match(T__35);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(207); match(T__13);
				setState(208); match(T__35);
				setState(209); expr(0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(210); match(T__20);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				}
				break;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 38);
		case 1: return precpred(_ctx, 37);
		case 2: return precpred(_ctx, 35);
		case 3: return precpred(_ctx, 34);
		case 4: return precpred(_ctx, 33);
		case 5: return precpred(_ctx, 32);
		case 6: return precpred(_ctx, 31);
		case 7: return precpred(_ctx, 29);
		case 8: return precpred(_ctx, 28);
		case 9: return precpred(_ctx, 26);
		case 10: return precpred(_ctx, 25);
		case 11: return precpred(_ctx, 40);
		case 12: return precpred(_ctx, 39);
		case 13: return precpred(_ctx, 23);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3@\u00d9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2"+
		"\7\2\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3#\n\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4/\n\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4i\n\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u009c\n\4"+
		"\f\4\16\4\u009f\13\4\3\5\3\5\3\5\5\5\u00a4\n\5\7\5\u00a6\n\5\f\5\16\5"+
		"\u00a9\13\5\3\5\5\5\u00ac\n\5\3\6\3\6\3\6\7\6\u00b1\n\6\f\6\16\6\u00b4"+
		"\13\6\3\7\3\7\3\7\3\7\3\7\5\7\u00bb\n\7\3\b\3\b\3\b\7\b\u00c0\n\b\f\b"+
		"\16\b\u00c3\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u00d7\n\t\3\t\2\3\6\n\2\4\6\b\n\f\16\20\2\f\4\2"+
		"..??\5\2\t\t\24\24&&\4\2\35\35,,\4\2\22\22((\4\2\23\23\'\'\4\2\5\5\63"+
		"\63\6\2\6\b\61\61\64\64\67\67\4\2\3\3//\4\2\27\27\60\60\4\2\16\17\62\62"+
		"\u010a\2\30\3\2\2\2\4\"\3\2\2\2\6h\3\2\2\2\b\u00ab\3\2\2\2\n\u00ad\3\2"+
		"\2\2\f\u00ba\3\2\2\2\16\u00bc\3\2\2\2\20\u00d6\3\2\2\2\22\23\5\4\3\2\23"+
		"\24\t\2\2\2\24\27\3\2\2\2\25\27\7?\2\2\26\22\3\2\2\2\26\25\3\2\2\2\27"+
		"\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33"+
		"\34\7\2\2\3\34\3\3\2\2\2\35\36\5\6\4\2\36\37\t\3\2\2\37 \5\4\3\2 #\3\2"+
		"\2\2!#\5\6\4\2\"\35\3\2\2\2\"!\3\2\2\2#\5\3\2\2\2$%\b\4\1\2%&\t\4\2\2"+
		"&i\5\6\4&\'(\7\30\2\2(i\5\6\4 )*\7\66\2\2*i\5\6\4\35+,\7+\2\2,.\7\36\2"+
		"\2-/\5\n\6\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\20\2\2\61i\5\6\4"+
		"\32\62\63\7\25\2\2\63i\5\6\4\23\64\65\7!\2\2\65i\5\6\4\22\66\67\7\"\2"+
		"\2\678\5\b\5\289\7\f\2\29i\3\2\2\2:;\7 \2\2;<\7\36\2\2<=\5\6\4\2=>\7\20"+
		"\2\2>?\5\6\4\2?i\3\2\2\2@A\7 \2\2AB\7\36\2\2BC\5\6\4\2CD\7\20\2\2DE\5"+
		"\6\4\2EF\7%\2\2FG\5\6\4\2Gi\3\2\2\2HI\7-\2\2IJ\7\36\2\2JK\7=\2\2KL\7\32"+
		"\2\2LM\5\6\4\2MN\7\20\2\2NO\5\6\4\2Oi\3\2\2\2PQ\7\34\2\2QR\7\36\2\2RS"+
		"\5\6\4\2ST\7\20\2\2TU\5\6\4\2Ui\3\2\2\2Vi\7\n\2\2Wi\7$\2\2XY\7\36\2\2"+
		"YZ\5\6\4\2Z[\7\20\2\2[i\3\2\2\2\\i\7=\2\2]i\7<\2\2^i\78\2\2_i\79\2\2`"+
		"i\7:\2\2ai\7;\2\2bi\7*\2\2ci\7\26\2\2di\7\65\2\2ei\7\21\2\2fi\7\13\2\2"+
		"gi\7)\2\2h$\3\2\2\2h\'\3\2\2\2h)\3\2\2\2h+\3\2\2\2h\62\3\2\2\2h\64\3\2"+
		"\2\2h\66\3\2\2\2h:\3\2\2\2h@\3\2\2\2hH\3\2\2\2hP\3\2\2\2hV\3\2\2\2hW\3"+
		"\2\2\2hX\3\2\2\2h\\\3\2\2\2h]\3\2\2\2h^\3\2\2\2h_\3\2\2\2h`\3\2\2\2ha"+
		"\3\2\2\2hb\3\2\2\2hc\3\2\2\2hd\3\2\2\2he\3\2\2\2hf\3\2\2\2hg\3\2\2\2i"+
		"\u009d\3\2\2\2jk\f(\2\2kl\t\5\2\2l\u009c\5\6\4)mn\f\'\2\2no\t\6\2\2o\u009c"+
		"\5\6\4(pq\f%\2\2qr\7\37\2\2r\u009c\5\6\4&st\f$\2\2tu\7>\2\2u\u009c\5\6"+
		"\4%vw\f#\2\2wx\t\7\2\2x\u009c\5\6\4$yz\f\"\2\2z{\t\4\2\2{\u009c\5\6\4"+
		"#|}\f!\2\2}~\t\b\2\2~\u009c\5\6\4\"\177\u0080\f\37\2\2\u0080\u0081\t\t"+
		"\2\2\u0081\u009c\5\6\4 \u0082\u0083\f\36\2\2\u0083\u0084\t\n\2\2\u0084"+
		"\u009c\5\6\4\37\u0085\u0086\f\34\2\2\u0086\u0087\7\66\2\2\u0087\u009c"+
		"\5\6\4\35\u0088\u0089\f\33\2\2\u0089\u008a\t\13\2\2\u008a\u009c\5\6\4"+
		"\34\u008b\u008c\f*\2\2\u008c\u008d\7\r\2\2\u008d\u008e\5\16\b\2\u008e"+
		"\u008f\7\31\2\2\u008f\u0090\7\31\2\2\u0090\u009c\3\2\2\2\u0091\u0092\f"+
		")\2\2\u0092\u0093\7\4\2\2\u0093\u0094\5\16\b\2\u0094\u0095\7\31\2\2\u0095"+
		"\u009c\3\2\2\2\u0096\u0097\f\31\2\2\u0097\u0098\7\36\2\2\u0098\u0099\5"+
		"\16\b\2\u0099\u009a\7\20\2\2\u009a\u009c\3\2\2\2\u009bj\3\2\2\2\u009b"+
		"m\3\2\2\2\u009bp\3\2\2\2\u009bs\3\2\2\2\u009bv\3\2\2\2\u009by\3\2\2\2"+
		"\u009b|\3\2\2\2\u009b\177\3\2\2\2\u009b\u0082\3\2\2\2\u009b\u0085\3\2"+
		"\2\2\u009b\u0088\3\2\2\2\u009b\u008b\3\2\2\2\u009b\u0091\3\2\2\2\u009b"+
		"\u0096\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\7\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a7\5\4\3\2\u00a1\u00a3"+
		"\t\2\2\2\u00a2\u00a4\5\4\3\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2"+
		"\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00ac\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\u00ac\3\2\2\2\u00ab\u00a0\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\t\3\2\2\2"+
		"\u00ad\u00b2\5\f\7\2\u00ae\u00af\7\33\2\2\u00af\u00b1\5\f\7\2\u00b0\u00ae"+
		"\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\13\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00bb\7=\2\2\u00b6\u00b7\7=\2\2"+
		"\u00b7\u00b8\7\24\2\2\u00b8\u00bb\5\6\4\2\u00b9\u00bb\7#\2\2\u00ba\u00b5"+
		"\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\r\3\2\2\2\u00bc"+
		"\u00c1\5\20\t\2\u00bd\u00be\7\33\2\2\u00be\u00c0\5\20\t\2\u00bf\u00bd"+
		"\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\17\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00d7\5\6\4\2\u00c5\u00c6\7=\2\2"+
		"\u00c6\u00d7\7\24\2\2\u00c7\u00c8\7=\2\2\u00c8\u00c9\7\24\2\2\u00c9\u00d7"+
		"\5\6\4\2\u00ca\u00cb\7<\2\2\u00cb\u00d7\7\24\2\2\u00cc\u00cd\7<\2\2\u00cd"+
		"\u00ce\7\24\2\2\u00ce\u00d7\5\6\4\2\u00cf\u00d0\7*\2\2\u00d0\u00d7\7\24"+
		"\2\2\u00d1\u00d2\7*\2\2\u00d2\u00d3\7\24\2\2\u00d3\u00d7\5\6\4\2\u00d4"+
		"\u00d7\7#\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00c4\3\2\2\2\u00d6\u00c5\3\2"+
		"\2\2\u00d6\u00c7\3\2\2\2\u00d6\u00ca\3\2\2\2\u00d6\u00cc\3\2\2\2\u00d6"+
		"\u00cf\3\2\2\2\u00d6\u00d1\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d5\3\2"+
		"\2\2\u00d7\21\3\2\2\2\20\26\30\".h\u009b\u009d\u00a3\u00a7\u00ab\u00b2"+
		"\u00ba\u00c1\u00d6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}