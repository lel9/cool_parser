package cool.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cool.CoolBaseVisitorImpl;
import cool.CoolLexer;
import cool.CoolParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CoolParserBaseTest {
    protected ObjectMapper mapper = new ObjectMapper();

    String getAST(String program) throws JsonProcessingException {
        CoolLexer lexer = new CoolLexer(CharStreams.fromString(program));
        TokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        ParseTree tree = parser.program();

        CoolBaseVisitorImpl visitor = new CoolBaseVisitorImpl();
        Object visit = visitor.visit(tree);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(visit);
    }
}
