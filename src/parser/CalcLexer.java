// Generated from /Users/helderbetiol/Downloads/CalcANTLR-ofi/src/parser/Calc.g4 by ANTLR 4.9.1
package parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, OP=7, IDENTIFIER=8, INTEGER=9, 
		WS=10, LINE_COMMENT=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "OP", "IDENTIFIER", "INTEGER", 
			"WS", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "'defun'", "')'", "'='", "'-'", "'if'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "OP", "IDENTIFIER", "INTEGER", 
			"WS", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public CalcLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\rU\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b/\n\b\3\t\3\t\7\t\63\n\t\f\t\16\t\66\13"+
		"\t\3\n\3\n\3\n\7\n;\n\n\f\n\16\n>\13\n\5\n@\n\n\3\13\6\13C\n\13\r\13\16"+
		"\13D\3\13\3\13\3\f\3\f\3\f\3\f\7\fM\n\f\f\f\16\fP\13\f\3\f\3\f\3\f\3\f"+
		"\2\2\r\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\3\2\6\5\2,-"+
		"//\61\61\4\2\62;c|\5\2\13\f\17\17\"\"\3\2\f\f\2[\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\5\33\3\2"+
		"\2\2\7!\3\2\2\2\t#\3\2\2\2\13%\3\2\2\2\r\'\3\2\2\2\17.\3\2\2\2\21\60\3"+
		"\2\2\2\23?\3\2\2\2\25B\3\2\2\2\27H\3\2\2\2\31\32\7*\2\2\32\4\3\2\2\2\33"+
		"\34\7f\2\2\34\35\7g\2\2\35\36\7h\2\2\36\37\7w\2\2\37 \7p\2\2 \6\3\2\2"+
		"\2!\"\7+\2\2\"\b\3\2\2\2#$\7?\2\2$\n\3\2\2\2%&\7/\2\2&\f\3\2\2\2\'(\7"+
		"k\2\2()\7h\2\2)\16\3\2\2\2*/\t\2\2\2+,\7?\2\2,/\7?\2\2-/\7>\2\2.*\3\2"+
		"\2\2.+\3\2\2\2.-\3\2\2\2/\20\3\2\2\2\60\64\4c|\2\61\63\t\3\2\2\62\61\3"+
		"\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\22\3\2\2\2\66\64\3"+
		"\2\2\2\67@\7\62\2\28<\4\63;\29;\4\62;\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2"+
		"<=\3\2\2\2=@\3\2\2\2><\3\2\2\2?\67\3\2\2\2?8\3\2\2\2@\24\3\2\2\2AC\t\4"+
		"\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2FG\b\13\2\2G\26"+
		"\3\2\2\2HI\7\61\2\2IJ\7\61\2\2JN\3\2\2\2KM\n\5\2\2LK\3\2\2\2MP\3\2\2\2"+
		"NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\f\2\2RS\3\2\2\2ST\b\f\2\2"+
		"T\30\3\2\2\2\t\2.\64<?DN\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}