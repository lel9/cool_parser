package cool.parser;

import cool.CoolLexer;
import cool.CoolParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class CoolLexerBaseTest {
    public List<String> getTokens(String program) {
        CoolLexer lexer = new CoolLexer(CharStreams.fromString(program));
        TokenStream tokens = new CommonTokenStream(lexer);

        CoolParser parser = new CoolParser(tokens);
        ParseTree tree = parser.program();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < tokens.size(); i++) {
            list.add(tokens.get(i).getText());
        }
        return list;
    }
}