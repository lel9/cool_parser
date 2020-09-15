package cool;

import java.io.FileInputStream;

import cool.CoolLexer;
import cool.CoolParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Paths;

public class ParserTest {

    public static void main(String[] args) throws IOException {
        ANTLRFileStream reader = new ANTLRFileStream("C:\\Users\\olg-1\\uni\\grammars-v4\\cool\\examples\\arith.cl");
        CoolLexer lexer  = new CoolLexer((CharStream)reader);
        TokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        ParseTree tree = parser.program();

        //ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
        //ExtractMicroBaseListener extractor = new ExtractMicroBaseListener(parser);
        //walker.walk(extractor, tree); // initiate walk of tree with listener
        CoolBaseVisitorImpl visitor = new CoolBaseVisitorImpl();
        visitor.visit(tree);
    }
}

//objectid, typeid, int_const, string_const, bool_const
//	static String[] TOKENS = {"ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", "'('", "')'", "':'", "'@'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", "'}'", "'.'", "DARROW", "LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", "NOT"
//	};
