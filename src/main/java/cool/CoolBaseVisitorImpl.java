package cool;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class CoolBaseVisitorImpl<T> extends CoolBaseVisitor<CoolAST.ASTNode> {
    private List<CoolAST.Class> classes = new ArrayList<>();

    @Override
    public CoolAST.ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        visit(ctx.programBlocks());
        CoolAST.Program program = new CoolAST.Program(classes);
        return program;
    }

    @Override
    public CoolAST.ASTNode visitClasses(CoolParser.ClassesContext ctx) {
        CoolAST.ASTNode firstClass = visit(ctx.classDefine());
        classes.add((CoolAST.Class) firstClass);
        if (ctx.programBlocks() != null) {
            CoolAST.ASTNode visited = visit(ctx.programBlocks());
            if (visited != null)
                classes.add((CoolAST.Class) visited);
        }

        return null;
    }

    @Override
    public CoolAST.ASTNode visitEof(CoolParser.EofContext ctx) {
        return null;
    }

    @Override
    public CoolAST.ASTNode visitClassDefine(CoolParser.ClassDefineContext ctx) {
        List<CoolAST.Feature> features = new ArrayList<>();
        for (CoolParser.FeatureContext context: ctx.feature()) {
            CoolAST.ASTNode visit = visit(context);
            features.add((CoolAST.Feature) visit);
        };
        if (ctx.TYPEID().size() > 1) {
            return new CoolAST.Class(getText(ctx.TYPEID(0)), getText(ctx.TYPEID(1)), features);
        }
        return new CoolAST.Class(getText(ctx.TYPEID(0)), null, features);
    }

    @Override
    public CoolAST.ASTNode visitMethod(CoolParser.MethodContext ctx) {
        List<CoolAST.Argument> arguments = new ArrayList<>();
        for (CoolParser.FormalContext context: ctx.formal()) {
            CoolAST.ASTNode visit = visit(context);
            arguments.add((CoolAST.Argument) visit);
        };
        CoolAST.ASTNode body = visit(ctx.expression());
        return new CoolAST.Method(ctx.OBJECTID().getText(), arguments, ctx.TYPEID().getText(), (CoolAST.Expression) body);
    }

    @Override
    public CoolAST.ASTNode visitProperty(CoolParser.PropertyContext ctx) {
        CoolAST.ASTNode expr = ctx.expression() != null ? visit(ctx.expression()) : null;
        return new CoolAST.Property(getText(ctx.OBJECTID()), getText(ctx.TYPEID()),
                    (CoolAST.Expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new CoolAST.Argument(getText(ctx.OBJECTID()), getText(ctx.TYPEID()));
    }

    @Override
    public CoolAST.ASTNode visitLetIn(CoolParser.LetInContext ctx) {
        List<CoolAST.LetElem> letList = new ArrayList<>();
        int i = 1;
        while (i < ctx.getChildCount() - 2) {
            String name = getText((TerminalNode) ctx.getChild(i));
            String typeId = getText((TerminalNode) ctx.getChild(i+2));
            CoolAST.Expression expr = null;
            if ("<-".equals(getText((TerminalNode)ctx.getChild(i+3)))) {
                expr = (CoolAST.Expression) visit(ctx.getChild(i+4));
                i += 5;
            } else {
                i += 3;
            }
            letList.add(new CoolAST.LetElem(name, typeId, expr));
        }
        CoolAST.Expression body = (CoolAST.Expression) visit(ctx.getChild(ctx.getChildCount()-1));
        return new CoolAST.Let(letList, body);
    }

    @Override
    public CoolAST.ASTNode visitMinus(CoolParser.MinusContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.Minus((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitString(CoolParser.StringContext ctx) {
        return new CoolAST.StringConst(ctx.STRING().getText());
    }

    @Override
    public CoolAST.ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new CoolAST.IsVoid((CoolAST.Expression) visit(ctx.getChild(0)));
    }

    @Override
    public CoolAST.ASTNode visitWhile(CoolParser.WhileContext ctx) {
        CoolAST.ASTNode predicate = visit(ctx.getChild(1));
        CoolAST.ASTNode body = visit(ctx.getChild(3));
        return new CoolAST.Loop((CoolAST.Expression) predicate, (CoolAST.Expression) body);
    }

    @Override
    public CoolAST.ASTNode visitDivision(CoolParser.DivisionContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.Division((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitNegative(CoolParser.NegativeContext ctx) {
        CoolAST.ASTNode expr = visit(ctx.expression());
        return new CoolAST.Negative((CoolAST.Expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitBoolNot(CoolParser.BoolNotContext ctx) {
        return new CoolAST.BoolNot((CoolAST.Expression) visit(ctx.expression()));
    }

    @Override
    public CoolAST.ASTNode visitLessThan(CoolParser.LessThanContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.LessThan((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<CoolAST.Expression> expressions = new ArrayList<>();
        for (CoolParser.ExpressionContext context: ctx.expression()) {
            CoolAST.ASTNode visit = visit(context);
            expressions.add((CoolAST.Expression) visit);
        };
        return new CoolAST.Block(expressions);
    }

    @Override
    public CoolAST.ASTNode visitId(CoolParser.IdContext ctx) {
        return visit(ctx.OBJECTID());
    }

    @Override
    public CoolAST.ASTNode visitMultiply(CoolParser.MultiplyContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.Multiple((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitIf(CoolParser.IfContext ctx) {
        CoolAST.ASTNode predicate = visit(ctx.getChild(1));
        CoolAST.ASTNode ifbody = visit(ctx.getChild(3));
        CoolAST.ASTNode elsebody = visit(ctx.getChild(5));
        return new CoolAST.If((CoolAST.Expression) predicate,
                (CoolAST.Expression) ifbody, (CoolAST.Expression) elsebody);
    }

    @Override
    public CoolAST.ASTNode visitCase(CoolParser.CaseContext ctx) {
        CoolAST.Expression predicate = (CoolAST.Expression) visit(ctx.getChild(1));
        List<CoolAST.Branch> branches = new ArrayList<>();
        for (int i = 3; i < ctx.getChildCount()-1; i+=6) {
            String name = getText((TerminalNode) ctx.getChild(i));
            String type = getText((TerminalNode) ctx.getChild(i+2));
            CoolAST.Expression expression = (CoolAST.Expression) visit(ctx.getChild(i+4));
            branches.add(new CoolAST.Branch(name, type, expression));
        }
        return new CoolAST.Case(predicate, branches);
    }

    @Override
    public CoolAST.ASTNode visitOwnMethodCall(CoolParser.OwnMethodCallContext ctx) {
        List<CoolAST.Expression> expressions = new ArrayList<>();
        for (CoolParser.ExpressionContext context: ctx.expression()) {
            CoolAST.ASTNode visit = visit(context);
            expressions.add((CoolAST.Expression) visit);
        };
        return new CoolAST.OwnMethodCall(ctx.OBJECTID().getText(), expressions);
    }

    @Override
    public CoolAST.ASTNode visitAdd(CoolParser.AddContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.Add((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitNew(CoolParser.NewContext ctx) {
        return new CoolAST.New(getText(ctx.TYPEID()));
    }

    @Override
    public CoolAST.ASTNode visitParentheses(CoolParser.ParenthesesContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public CoolAST.ASTNode visitAssignment(CoolParser.AssignmentContext ctx) {
        String terminal = getText(ctx.OBJECTID());
        CoolAST.ASTNode expr = visit(ctx.getChild(2));
        return new CoolAST.Assign(terminal, (CoolAST.Expression) expr);
    }

    @Override
    public CoolAST.ASTNode visitFalse(CoolParser.FalseContext ctx) {
        return new CoolAST.BoolConst(false);
    }

    @Override
    public CoolAST.ASTNode visitInt(CoolParser.IntContext ctx) {
        return new CoolAST.IntConst(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public CoolAST.ASTNode visitEqual(CoolParser.EqualContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.Equal((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitTrue(CoolParser.TrueContext ctx) {
        return new CoolAST.BoolConst(true);
    }

    @Override
    public CoolAST.ASTNode visitLessEqual(CoolParser.LessEqualContext ctx) {
        CoolAST.ASTNode left = visit(ctx.getChild(0));
        CoolAST.ASTNode right = visit(ctx.getChild(2));
        return new CoolAST.LessEqual((CoolAST.Expression) left, (CoolAST.Expression) right);
    }

    @Override
    public CoolAST.ASTNode visitMethodCall(CoolParser.MethodCallContext ctx) {
        CoolParser.ExpressionContext caller = ctx.expression().get(0);
        List<CoolAST.Expression> expressions = new ArrayList<>();
        if (ctx.expression().size() > 1) {
            for (CoolParser.ExpressionContext context : ctx.expression().subList(1, ctx.expression().size())) {
                CoolAST.ASTNode visit = visit(context);
                expressions.add((CoolAST.Expression) visit);
            }
        }
        return new CoolAST.MethodCall((CoolAST.Expression) visit(caller),
                getText(ctx.TYPEID()), getText(ctx.OBJECTID()), expressions);
    }

    @Override
    public CoolAST.ASTNode visitTerminal(TerminalNode node) {
        return new CoolAST.Terminal(node.getText());
    }

    private String getText(TerminalNode node) {
        if (node == null)
            return null;
        return node.getText();
    }
}
