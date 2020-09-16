package cool.parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CoolLexerBaseTestImpl extends CoolLexerBaseTest {

    @Test
    public void test1() {
        String programm = "class A {};";
        List<String> expected = Arrays.asList("class", "A", "{", "}", ";", "<EOF>");
        List<String> tokens = getTokens(programm);
        assertEquals(expected, tokens);
    }

    @Test
    public void test2() {
        String programm =
                "class Math {\n" +
                        "    add(n: Int, m: Int) : Int {\n" +
                        "        n + m\n" +
                        "    };" +
                        "};\n";
        List<String> expected =
                Arrays.asList("class", "Math", "{", "add", "(", "n",
                        ":", "Int", ",", "m", ":", "Int", ")", ":",
                        "Int", "{", "n", "+", "m", "}", ";", "}", ";", "<EOF>");
        List<String> tokens = getTokens(programm);
        assertEquals(expected, tokens);
    }

    @Test
    public void test3() {
        String programm =
                "class App {\n" +
                        "  arg : Expr;\n" +
                        "\n" +
                        "  init(a : Expr) : App {\n" +
                        "    {\n" +
                        "      arg <- a;\n" +
                        "      self;\n" +
                        "    }\n" +
                        "  };" +
                        "};\n";
        List<String> expected =
                Arrays.asList("class", "App", "{", "arg", ":", "Expr",
                        ";", "init", "(", "a", ":", "Expr", ")", ":",
                        "App", "{", "{", "arg", "<-", "a", ";", "self",
                        ";", "}", "}", ";", "}", ";", "<EOF>");
        List<String> tokens = getTokens(programm);
        assertEquals(expected, tokens);
    }

    @Test
    public void test4() {
        String programm =
                "class Math {\n" +
                        "    factorial(n: Int) : Int {\n" +
                        "        if n = 1\n" +
                        "        then \n" +
                        "            1\n" +
                        "        else\n" +
                        "            n * factorial(n - 1)\n" +
                        "        fi\n" +
                        "    };" +
                        "};\n";
        List<String> expected =
                Arrays.asList("class", "Math", "{", "factorial", "(", "n"
                        , ":", "Int", ")", ":", "Int", "{", "if", "n", "=",
                        "1", "then", "1", "else", "n", "*", "factorial", "(",
                        "n", "-", "1", ")", "fi", "}", ";", "}", ";", "<EOF>");
        List<String> tokens = getTokens(programm);
        assertEquals(expected, tokens);
    }

    @Test
    public void test5() {
        String programm =
                "class D {\n" +
                        "   inf_loop() : Object {\n" +
                        "   while (true) loop\n" +
                        "   \t    {\n" +
                        "   \t       io.out_string(\"hello\");\n" +
                        "   \t    }\n" +
                        "   \t pool\n" +
                        "    };\n" +
                        "};";
        List<String> expected =
                Arrays.asList("class", "D", "{", "inf_loop", "(", ")", ":",
                        "Object", "{", "while", "(", "true", ")", "loop", "{",
                        "io", ".", "out_string", "(", "\"hello\"", ")", ";", "}", "pool", "}",
                        ";", "}", ";", "<EOF>");
        List<String> tokens = getTokens(programm);
        assertEquals(expected, tokens);
    }
}
