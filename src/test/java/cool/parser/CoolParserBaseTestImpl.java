package cool.parser;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CoolParserBaseTestImpl extends CoolParserBaseTest {
    @Test
    public void test1() throws IOException {
        String programm = "class A {};";
        String expected = "{\n" +
                "  \"nodetype\" : \"Program\",\n" +
                "  \"classes\" : [ {\n" +
                "    \"nodetype\" : \"Class\",\n" +
                "    \"name\" : \"A\",\n" +
                "    \"parent\" : null,\n" +
                "    \"features\" : [ ]\n" +
                "  } ]\n" +
                "}";
        String actual = getAST(programm);
        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }

    @Test
    public void test2() throws IOException {
        String programm =
                "class Math {\n" +
                        "    add(n: Int, m: Int) : Int {\n" +
                        "        a <- 5 + 3*2 - 10\n" +
                        "    };" +
                        "};\n";
        String expected =
                "{\n" +
                        "  \"nodetype\" : \"Program\",\n" +
                        "  \"classes\" : [ {\n" +
                        "    \"nodetype\" : \"Class\",\n" +
                        "    \"name\" : \"Math\",\n" +
                        "    \"parent\" : null,\n" +
                        "    \"features\" : [ {\n" +
                        "      \"nodetype\" : \"Method\",\n" +
                        "      \"name\" : \"add\",\n" +
                        "      \"arguments\" : [ {\n" +
                        "        \"nodetype\" : \"Argument\",\n" +
                        "        \"name\" : \"n\",\n" +
                        "        \"typeid\" : \"Int\"\n" +
                        "      }, {\n" +
                        "        \"nodetype\" : \"Argument\",\n" +
                        "        \"name\" : \"m\",\n" +
                        "        \"typeid\" : \"Int\"\n" +
                        "      } ],\n" +
                        "      \"typeid\" : \"Int\",\n" +
                        "      \"body\" : {\n" +
                        "        \"nodetype\" : \"Add\",\n" +
                        "        \"e1\" : {\n" +
                        "          \"nodetype\" : \"Terminal\",\n" +
                        "          \"name\" : \"n\"\n" +
                        "        },\n" +
                        "        \"e2\" : {\n" +
                        "          \"nodetype\" : \"Terminal\",\n" +
                        "          \"name\" : \"m\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  } ]\n" +
                        "}";
        String actual = getAST(programm);
        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }

    @Test
    public void test3() throws IOException {
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
        String expected =
                "{\n" +
                        "  \"nodetype\" : \"Program\",\n" +
                        "  \"classes\" : [ {\n" +
                        "    \"nodetype\" : \"Class\",\n" +
                        "    \"name\" : \"App\",\n" +
                        "    \"parent\" : null,\n" +
                        "    \"features\" : [ {\n" +
                        "      \"nodetype\" : \"Property\",\n" +
                        "      \"name\" : \"arg\",\n" +
                        "      \"typeid\" : \"Expr\",\n" +
                        "      \"value\" : null\n" +
                        "    }, {\n" +
                        "      \"nodetype\" : \"Method\",\n" +
                        "      \"name\" : \"init\",\n" +
                        "      \"arguments\" : [ {\n" +
                        "        \"nodetype\" : \"Argument\",\n" +
                        "        \"name\" : \"a\",\n" +
                        "        \"typeid\" : \"Expr\"\n" +
                        "      } ],\n" +
                        "      \"typeid\" : \"App\",\n" +
                        "      \"body\" : {\n" +
                        "        \"nodetype\" : \"Block\",\n" +
                        "        \"expressions\" : [ {\n" +
                        "          \"nodetype\" : \"Assign\",\n" +
                        "          \"name\" : \"arg\",\n" +
                        "          \"e1\" : {\n" +
                        "            \"nodetype\" : \"Terminal\",\n" +
                        "            \"name\" : \"a\"\n" +
                        "          }\n" +
                        "        }, {\n" +
                        "          \"nodetype\" : \"Terminal\",\n" +
                        "          \"name\" : \"self\"\n" +
                        "        } ]\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  } ]\n" +
                        "}";
        String actual = getAST(programm);
        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }

    @Test
    public void test4() throws IOException {
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
        String expected =
                "{\n" +
                        "  \"nodetype\" : \"Program\",\n" +
                        "  \"classes\" : [ {\n" +
                        "    \"nodetype\" : \"Class\",\n" +
                        "    \"name\" : \"Math\",\n" +
                        "    \"parent\" : null,\n" +
                        "    \"features\" : [ {\n" +
                        "      \"nodetype\" : \"Method\",\n" +
                        "      \"name\" : \"factorial\",\n" +
                        "      \"arguments\" : [ {\n" +
                        "        \"nodetype\" : \"Argument\",\n" +
                        "        \"name\" : \"n\",\n" +
                        "        \"typeid\" : \"Int\"\n" +
                        "      } ],\n" +
                        "      \"typeid\" : \"Int\",\n" +
                        "      \"body\" : {\n" +
                        "        \"nodetype\" : \"If\",\n" +
                        "        \"predicate\" : {\n" +
                        "          \"nodetype\" : \"Equal\",\n" +
                        "          \"e1\" : {\n" +
                        "            \"nodetype\" : \"Terminal\",\n" +
                        "            \"name\" : \"n\"\n" +
                        "          },\n" +
                        "          \"e2\" : {\n" +
                        "            \"nodetype\" : \"IntConst\",\n" +
                        "            \"value\" : 1\n" +
                        "          }\n" +
                        "        },\n" +
                        "        \"ifbody\" : {\n" +
                        "          \"nodetype\" : \"IntConst\",\n" +
                        "          \"value\" : 1\n" +
                        "        },\n" +
                        "        \"elsebody\" : {\n" +
                        "          \"nodetype\" : \"Multiple\",\n" +
                        "          \"e1\" : {\n" +
                        "            \"nodetype\" : \"Terminal\",\n" +
                        "            \"name\" : \"n\"\n" +
                        "          },\n" +
                        "          \"e2\" : {\n" +
                        "            \"nodetype\" : \"OwnMethodCall\",\n" +
                        "            \"name\" : \"factorial\",\n" +
                        "            \"actuals\" : [ {\n" +
                        "              \"nodetype\" : \"Minus\",\n" +
                        "              \"e1\" : {\n" +
                        "                \"nodetype\" : \"Terminal\",\n" +
                        "                \"name\" : \"n\"\n" +
                        "              },\n" +
                        "              \"e2\" : {\n" +
                        "                \"nodetype\" : \"IntConst\",\n" +
                        "                \"value\" : 1\n" +
                        "              }\n" +
                        "            } ]\n" +
                        "          }\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  } ]\n" +
                        "}\n";
        String actual = getAST(programm);
        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }



    @Test
    public void test5() throws IOException {
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
        String expected =
                "{\n" +
                        "  \"nodetype\" : \"Program\",\n" +
                        "  \"classes\" : [ {\n" +
                        "    \"nodetype\" : \"Class\",\n" +
                        "    \"name\" : \"D\",\n" +
                        "    \"parent\" : null,\n" +
                        "    \"features\" : [ {\n" +
                        "      \"nodetype\" : \"Method\",\n" +
                        "      \"name\" : \"inf_loop\",\n" +
                        "      \"arguments\" : [ ],\n" +
                        "      \"typeid\" : \"Object\",\n" +
                        "      \"body\" : {\n" +
                        "        \"nodetype\" : \"Loop\",\n" +
                        "        \"predicate\" : {\n" +
                        "          \"nodetype\" : \"BoolConst\",\n" +
                        "          \"value\" : true\n" +
                        "        },\n" +
                        "        \"body\" : {\n" +
                        "          \"nodetype\" : \"Block\",\n" +
                        "          \"expressions\" : [ {\n" +
                        "            \"nodetype\" : \"MethodCall\",\n" +
                        "            \"caller\" : {\n" +
                        "              \"nodetype\" : \"Terminal\",\n" +
                        "              \"name\" : \"io\"\n" +
                        "            },\n" +
                        "            \"typeid\" : null,\n" +
                        "            \"name\" : \"out_string\",\n" +
                        "            \"actuals\" : [ {\n" +
                        "              \"nodetype\" : \"StringConst\",\n" +
                        "              \"value\" : \"\\\"hello\\\"\"\n" +
                        "            } ]\n" +
                        "          } ]\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  } ]\n" +
                        "}";
        String actual = getAST(programm);
        assertEquals(mapper.readTree(expected), mapper.readTree(actual));
    }

}
