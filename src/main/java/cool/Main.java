package cool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ANTLRFileStream reader = new ANTLRFileStream("C:\\Users\\olg-1\\uni\\grammars-v4\\cool\\examples\\arith.cl");
        CoolLexer lexer = new CoolLexer((CharStream) reader);
        TokenStream tokens = new CommonTokenStream(lexer);

        CoolParser parser = new CoolParser(tokens);
        ParseTree tree = parser.program();

        CoolBaseVisitorImpl visitor = new CoolBaseVisitorImpl();
        Object visit = visitor.visit(tree);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(visit);
        System.out.println(json);

        List<String> list = new ArrayList<>();
        for(int i = 0; i < tokens.size(); i++) {
            System.out.println(" text=" + tokens.get(i).getText() + " type=" + tokens.get(i).getType() + " line=" +
                    tokens.get(i).getLine() + " pos_in_line=" + tokens.get(i).getCharPositionInLine() + " start_index=" +
                    tokens.get(i).getStartIndex() + " stop_index=" + tokens.get(i).getStopIndex() +
                    " index=" + tokens.get(i).getTokenIndex());
        }
    }
}
