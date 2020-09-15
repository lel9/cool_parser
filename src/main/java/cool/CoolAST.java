package cool;

import java.util.List;

public class CoolAST {
    public static class ASTNode {
    }

    public static class expression extends ASTNode {
    }

    public static class boolConst extends expression{
        public boolean value;
        public boolConst(boolean v) {
            value = v;
        }
    }

    public static class stringConst extends expression{
        public String value;
        public stringConst(String v) {
            value = v;
        }
    }

    public static class intConst extends expression{
        public int value;
        public intConst(int v) {
            value = v;
        }
    }

    public static class terminal extends expression{
        public String name;
        public terminal(String v) {
            name = v;
        }
    }
    
    public static class comp extends expression{
        public expression e1;
        public comp(expression v) {
            e1 = v;
        }
    }
    
    public static class equal extends expression{
        public expression e1;
        public expression e2;
        public equal(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class lessEqual extends expression{
        public expression e1;
        public expression e2;
        public lessEqual(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class lt extends expression{
        public expression e1;
        public expression e2;
        public lt(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class neg extends expression{
        public expression e1;
        public neg(expression v) {
            e1 = v;
        }
    }

    public static class division extends expression{
        public expression e1;
        public expression e2;
        public division(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class multiple extends expression{
        public expression e1;
        public expression e2;
        public multiple(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }

    public static class minus extends expression{
        public expression e1;
        public expression e2;
        public minus(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class add extends expression {
        public expression e1;
        public expression e2;
        public add(expression v1, expression v2) {
            e1 = v1;
            e2 = v2;
        }
    }
    
    public static class isvoid extends expression{
        public expression e1;
        public isvoid(expression v) {
            e1 = v;
        }
    }
    
    public static class new_ extends expression{
        public String typeid;
        public new_(String t) {
            typeid = t;
        }
    }
    
    public static class assign extends expression{
        public String name;
        public expression e1;
        public assign(String n, expression v1) {
            name = n;
            e1 = v1;
        }
    }
    
    public static class block extends expression{
        public List<expression> l1;
        public block(List<expression> v1) {
            l1 = v1;
        }
    }
    
    public static class loop extends expression{
        public expression predicate;
        public expression body;
        public loop(expression v1, expression v2) {
            predicate = v1;
            body = v2;
        }
    }
    
    public static class if_ extends expression{
        public expression predicate;
        public expression ifbody;
        public expression elsebody;
        public if_(expression v1, expression v2, expression v3) {
            predicate = v1;
            ifbody = v2;
            elsebody = v3;
        }
    }
    
    public static class let extends expression{
        public String name;
        public String typeid;
        public expression value;
        public expression body;
        public let(String n, String t, expression v, expression b) {
            name = n;
            typeid = t;
            value = v;
            body = b;
        }
    }
    
    public static class ownMethodCall extends expression {
        public String name;
        public List<expression> actuals;
        public ownMethodCall(String n, List<expression> a) {
            name = n;
            actuals = a;
        }
    }
    
    public static class methodCall extends expression{
        public expression caller;
        public String typeid;
        public String name;
        public List<expression> actuals;
        public methodCall(expression v1, String t, String n, List<expression> a) {
            caller = v1;
            typeid = t;
            name = n;
            actuals = a;
        }
    }
    
    public static class typcase extends expression{
        public expression predicate;
        public List<branch> branches;
        public typcase(expression p, List<branch> b) {
            predicate = p;
            branches = b;
        }
    }
    
    public static class branch extends ASTNode {
        public String name;
        public String type;
        public expression value;
        public branch(String n, String t, expression v) {
            name = n;
            type = t;
            value = v;
        }
    }
    
    public static class formal extends ASTNode {
        public String name;
        public String typeid;
        public formal(String n, String t) {
            name = n;
            typeid = t;
        }
    }
    
    public static class feature extends ASTNode {
        public feature(){
        }
    }

    public static class method extends feature {
        public String name;
        public List<formal> formals;
        public String typeid;
        public expression body;
        public method(String n, List<formal> f, String t, expression b) {
            name = n;
            formals = f;
            typeid = t;
            body = b;
        }
    }
    
    public static class attr extends feature {
        public String name;
        public String typeid;
        public expression value;
        public attr(String n, String t, expression v) {
            name = n;
            typeid = t;
            value = v;
        }
    }
    
    public static class class_ extends ASTNode {
        public String name;
        public String parent;
        public List<feature> features;
        public class_(String n, String p, List<feature> fs) {
            name = n;
            parent = p;
            features = fs;
        }
    }

    public static class program extends ASTNode {
        public List<class_> classes;
        public program(List<class_> c) {
            classes = c;
        }
    }
}