package cool;

import java.io.FileInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cool.CoolLexer;
import cool.CoolParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Paths;

public class ParserTest {

    public static void main(String[] args) throws IOException {
        ANTLRFileStream reader = new ANTLRFileStream("C:\\Users\\olg-1\\uni\\grammars-v4\\cool\\examples\\primes.cl");
        CoolLexer lexer = new CoolLexer((CharStream) reader);
        TokenStream tokens = new CommonTokenStream(lexer);
        CoolParser parser = new CoolParser(tokens);
        ParseTree tree = parser.program();

        CoolBaseVisitorImpl visitor = new CoolBaseVisitorImpl();
        Object visit = visitor.visit(tree);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(visit);
        System.out.println(json);
    }
}
