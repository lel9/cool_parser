package cool;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class CoolBaseVisitorImpl<T> extends CoolBaseVisitor<CoolAST.ASTNode> {
    private List<CoolAST.class_> classes = new ArrayList<>();

    @Override
    public CoolAST.ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        visit(ctx.programBlocks());
        CoolAST.program program = new CoolAST.program(classes);
        return program;
    }

    @Override
    public CoolAST.ASTNode visitClasses(CoolParser.ClassesContext ctx) {
        CoolAST.ASTNode firstClass = visit(ctx.classDefine());
        classes.add((CoolAST.class_) firstClass);
        if (ctx.programBlocks() != null) {
            CoolAST.ASTNode visited = visit(ctx.programBlocks());
            if (visited != null)
                classes.add((CoolAST.class_) visited);
        }

        return null;
    }

    @Override
    public CoolAST.ASTNode visitEof(CoolParser.EofContext ctx) {
        return null;
    }

    @Override
    public CoolAST.ASTNode visitClassDefine(CoolParser.ClassDefineContext ctx) {
        List<CoolAST.feature> features = new ArrayList<>();
        for (CoolParser.FeatureContext context: ctx.feature()) {
            CoolAST.ASTNode visit = visit(context);
            features.add((CoolAST.feature) visit);
        };
        if (ctx.TYPEID().size() > 1) {
            return new CoolAST.class_(getText(ctx.TYPEID(0)), getText(ctx.TYPEID(1)), features);
        }
        return new CoolAST.class_(getText(ctx.TYPEID(0)), null, features);
    }

    @Override
    public CoolAST.ASTNode visitMethod(CoolParser.MethodContext ctx) {
        List<CoolAST.formal> formals = new ArrayList<>();
        for (CoolParser.FormalContext context: ctx.formal()) {
            CoolAST.ASTNode visit = visit(context);
            formals.add((CoolAST.formal) visit);
        };
        CoolAST.ASTNode body = visit(ctx.expression());
        return new CoolAST.method(ctx.OBJECTID().getText(), formals, ctx.TYPEID().getText(), (CoolAST.expression) body);
    }

    @Override
    public CoolAST.ASTNode visitProperty(CoolParser.PropertyContext ctx) {
        CoolAST.ASTNode expr = ctx.expression() != null ? visit(ctx.expression()) : null;
        return new CoolAST.attr(getText(ctx.OBJECTID()), getText(ctx.TYPEID()),
                    (CoolAST.expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new CoolAST.formal(getText(ctx.OBJECTID()), getText(ctx.TYPEID()));
    }

    @Override
    public CoolAST.ASTNode visitLetIn(CoolParser.LetInContext ctx) {
        return super.visitLetIn(ctx);
    }

    @Override
    public CoolAST.ASTNode visitMinus(CoolParser.MinusContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.minus((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitString(CoolParser.StringContext ctx) {
        return new CoolAST.stringConst(ctx.STRING().getText());
    }

    @Override
    public CoolAST.ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new CoolAST.isvoid((CoolAST.expression) visit(ctx.getChild(0)));
    }

    @Override
    public CoolAST.ASTNode visitWhile(CoolParser.WhileContext ctx) {
        CoolAST.ASTNode predicate = visit(ctx.getChild(1));
        CoolAST.ASTNode body = visit(ctx.getChild(3));
        return new CoolAST.loop((CoolAST.expression) predicate, (CoolAST.expression) body);
    }

    @Override
    public CoolAST.ASTNode visitDivision(CoolParser.DivisionContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.division((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitNegative(CoolParser.NegativeContext ctx) {
        CoolAST.ASTNode expr = visit(ctx.getChild(1));
        return new CoolAST.neg((CoolAST.expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitBoolNot(CoolParser.BoolNotContext ctx) {
        return super.visitBoolNot(ctx);
    }

    @Override
    public CoolAST.ASTNode visitLessThan(CoolParser.LessThanContext ctx) {
        return super.visitLessThan(ctx);
    }

    @Override
    public CoolAST.ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<CoolAST.expression> expressions = new ArrayList<>();
        for (CoolParser.ExpressionContext context: ctx.expression()) {
            CoolAST.ASTNode visit = visit(context);
            expressions.add((CoolAST.expression) visit);
        };
        return new CoolAST.block(expressions);
    }

    @Override
    public CoolAST.ASTNode visitId(CoolParser.IdContext ctx) {
        return visit(ctx.OBJECTID());
    }

    @Override
    public CoolAST.ASTNode visitMultiply(CoolParser.MultiplyContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.multiple((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitIf(CoolParser.IfContext ctx) {
        CoolAST.ASTNode predicate = visit(ctx.getChild(1));
        CoolAST.ASTNode ifbody = visit(ctx.getChild(3));
        CoolAST.ASTNode elsebody = visit(ctx.getChild(5));
        return new CoolAST.if_((CoolAST.expression) predicate,
                (CoolAST.expression) ifbody, (CoolAST.expression) elsebody);
    }

    @Override
    public CoolAST.ASTNode visitCase(CoolParser.CaseContext ctx) {
        return super.visitCase(ctx);
    }

    @Override
    public CoolAST.ASTNode visitOwnMethodCall(CoolParser.OwnMethodCallContext ctx) {
        List<CoolAST.expression> expressions = new ArrayList<>();
        for (CoolParser.ExpressionContext context: ctx.expression()) {
            CoolAST.ASTNode visit = visit(context);
            expressions.add((CoolAST.expression) visit);
        };
        return new CoolAST.ownMethodCall(ctx.OBJECTID().getText(), expressions);
    }

    @Override
    public CoolAST.ASTNode visitAdd(CoolParser.AddContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.add((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitNew(CoolParser.NewContext ctx) {
        return new CoolAST.new_(getText(ctx.TYPEID()));
    }

    @Override
    public CoolAST.ASTNode visitParentheses(CoolParser.ParenthesesContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public CoolAST.ASTNode visitAssignment(CoolParser.AssignmentContext ctx) {
        String terminal = getText(ctx.OBJECTID());
        CoolAST.ASTNode expr = visit(ctx.getChild(2));
        return new CoolAST.assign(terminal, (CoolAST.expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitFalse(CoolParser.FalseContext ctx) {
        return new CoolAST.boolConst(false);
    }

    @Override
    public CoolAST.ASTNode visitInt(CoolParser.IntContext ctx) {
        return new CoolAST.intConst(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public CoolAST.ASTNode visitEqual(CoolParser.EqualContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.equal((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitTrue(CoolParser.TrueContext ctx) {
        return new CoolAST.boolConst(true);
    }

    @Override
    public CoolAST.ASTNode visitLessEqual(CoolParser.LessEqualContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.lessEqual((CoolAST.expression) left, (CoolAST.expression) right);
    }

    @Override
    public CoolAST.ASTNode visitMethodCall(CoolParser.MethodCallContext ctx) {
        CoolParser.ExpressionContext caller = ctx.expression().get(0);
        List<CoolAST.expression> expressions = new ArrayList<>();
        if (ctx.expression().size() > 1) {
            for (CoolParser.ExpressionContext context : ctx.expression().subList(1, ctx.expression().size())) {
                CoolAST.ASTNode visit = visit(context);
                expressions.add((CoolAST.expression) visit);
            }
        }
        return new CoolAST.methodCall((CoolAST.expression) visit(caller),
                getText(ctx.TYPEID()), getText(ctx.OBJECTID()), expressions);
    }

    @Override
    public CoolAST.ASTNode visitTerminal(TerminalNode node) {
        return new CoolAST.terminal(node.getText());
    }

    private String getText(TerminalNode node) {
        if (node == null)
            return null;
        return node.getText();
    }
}
