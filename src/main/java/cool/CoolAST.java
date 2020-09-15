package cool;

import java.util.List;

public class CoolAST {
    public static class ASTNode {
        public String nodetype = this.getClass().getSimpleName();
    }

    public static class Expression extends ASTNode {
    }

    public static class BoolConst extends Expression {
        public boolean value;
        public BoolConst(boolean v) {
            value = v;
        }
    }

    public static class StringConst extends Expression {
        public String value;
        public StringConst(String v) {
            value = v;
        }
    }

    public static class IntConst extends Expression {
        public int value;
        public IntConst(int v) {
            value = v;
        }
    }

    public static class Terminal extends Expression {
        public String name;
        public Terminal(String v) {
            name = v;
        }
    }
    
    public static class BoolNot extends Expression {
        public Expression e1;
        public BoolNot(Expression v) {
            e1 = v;
        }
    }
    
    public static class Equal extends Expression {
        public Expression e1;
        public Expression e2;
        public Equal(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class LessEqual extends Expression {
        public Expression e1;
        public Expression e2;
        public LessEqual(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class LessThan extends Expression {
        public Expression e1;
        public Expression e2;
        public LessThan(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class Negative extends Expression {
        public Expression e1;
        public Negative(Expression v) {
            e1 = v;
        }
    }

    public static class Division extends Expression {
        public Expression e1;
        public Expression e2;
        public Division(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class Multiple extends Expression {
        public Expression e1;
        public Expression e2;
        public Multiple(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class Minus extends Expression {
        public Expression e1;
        public Expression e2;
        public Minus(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class Add extends Expression {
        public Expression e1;
        public Expression e2;
        public Add(Expression v1, Expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class IsVoid extends Expression {
        public Expression e1;
        public IsVoid(Expression v) {
            e1 = v;
        }
    }
    
    public static class New extends Expression {
        public String typeid;
        public New(String t) {
            typeid = t;
        }
    }
    
    public static class Assign extends Expression {
        public String name;
        public Expression e1;
        public Assign(String n, Expression v1) {
            name = n;
            e1 = v1;
        }
    }
    
    public static class Block extends Expression {
        public List<Expression> expressions;
        public Block(List<Expression> v1) {
            expressions = v1;
        }
    }
    
    public static class Loop extends Expression {
        public Expression predicate;
        public Expression body;
        public Loop(Expression v1, Expression v2) {
            predicate = v1;
            body = v2;
        }
    }
    
    public static class If extends Expression {
        public Expression predicate;
        public Expression ifbody;
        public Expression elsebody;
        public If(Expression v1, Expression v2, Expression v3) {
            predicate = v1;
            ifbody = v2;
            elsebody = v3;
        }
    }
    
    public static class Let extends Expression {
        public List<LetElem> letList;
        public Expression body;
        public Let(List<LetElem> list, Expression b) {
            letList = list;
            body = b;
        }
    }

    public static class LetElem extends ASTNode {
        public String name;
        public String typeid;
        public Expression value;
        public LetElem(String n, String t, Expression v) {
            name = n;
            typeid = t;
            value = v;
        }
    }

    public static class OwnMethodCall extends Expression {
        public String name;
        public List<Expression> actuals;
        public OwnMethodCall(String n, List<Expression> a) {
            name = n;
            actuals = a;
        }
    }
    
    public static class MethodCall extends Expression {
        public Expression caller;
        public String typeid;
        public String name;
        public List<Expression> actuals;
        public MethodCall(Expression v1, String t, String n, List<Expression> a) {
            caller = v1;
            typeid = t;
            name = n;
            actuals = a;
        }
    }
    
    public static class Case extends Expression {
        public Expression predicate;
        public List<Branch> branches;
        public Case(Expression p, List<Branch> b) {
            predicate = p;
            branches = b;
        }
    }
    
    public static class Branch extends ASTNode {
        public String name;
        public String type;
        public Expression value;
        public Branch(String n, String t, Expression v) {
            name = n;
            type = t;
            value = v;
        }
    }
    
    public static class Argument extends ASTNode {
        public String name;
        public String typeid;
        public Argument(String n, String t) {
            name = n;
            typeid = t;
        }
    }
    
    public static class Feature extends ASTNode {
        public Feature() {
        }
    }

    public static class Method extends Feature {
        public String name;
        public List<Argument> arguments;
        public String typeid;
        public Expression body;
        public Method(String n, List<Argument> f, String t, Expression b) {
            name = n;
            arguments = f;
            typeid = t;
            body = b;
        }
    }
    
    public static class Property extends Feature {
        public String name;
        public String typeid;
        public Expression value;
        public Property(String n, String t, Expression v) {
            name = n;
            typeid = t;
            value = v;
        }
    }
    
    public static class Class extends ASTNode {
        public String name;
        public String parent;
        public List<Feature> features;
        public Class(String n, String p, List<Feature> fs) {
            name = n;
            parent = p;
            features = fs;
        }
    }

    public static class Program extends ASTNode {
        public List<Class> classes;
        public Program(List<Class> c) {
            classes = c;
        }
    }
}