import java.util.List;

public class ASTMatch implements ASTNode {

    private ASTNode expr;
    private List<MatchBranch> branches;

    public ASTMatch(ASTNode expr, List<MatchBranch> branches) {
        this.expr = expr;
        this.branches = branches;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {

        IValue v = expr.eval(env);

        if (!(v instanceof VStructTuple))
            throw new InterpreterError("match expects enum value");

        VStructTuple ev = (VStructTuple) v;

        for (MatchBranch br : branches) {

            ASTPattern p = br.getPattern();

            if (!p.getTag().equals(ev.getTag()))
                continue;

            List<String> vars = p.getPvs();
            List<IValue> vals = ev.getFields();

            // depth subtyping: value may have MORE fields than pattern variables
            if (vars.size() > vals.size())
                throw new InterpreterError("arity mismatch");

            Environment<IValue> local = env.beginScope();

            for (int i = 0; i < vars.size(); i++)
                if (!vars.get(i).equals("_"))
                    local.assoc(vars.get(i), vals.get(i));

            return br.getBody().eval(local);
        }

        throw new InterpreterError("non-exhaustive match");
    }

    public ASTType typecheck(Environment<ASTType> env)
            throws TypeCheckError, InterpreterError {

        ASTType t = ASTTypeDef.unfold(expr.typecheck(env), env);

        if (!(t instanceof ASTTEnum))
            throw new TypeCheckError("match expects enum type");

        ASTTEnum enumT = (ASTTEnum) t;

        ASTType result = null;

        for (MatchBranch br : branches) {

            ASTPattern pat = br.getPattern();
            String tag = pat.getTag();

            ASTTStruct ctor = enumT.getCase(tag);

            if (ctor == null)
                throw new TypeCheckError("unknown tag: " + tag);

            List<ASTType> expected = ctor.getFields();
            List<String> vars = pat.getPvs();

            // depth subtyping: pattern may bind fewer fields than the constructor has
            if (vars.size() > expected.size())
                throw new TypeCheckError("arity mismatch in " + tag);

            Environment<ASTType> local = env.beginScope();

            for (int i = 0; i < vars.size(); i++) {
                ASTType fieldType = ASTTypeDef.unfold(expected.get(i), env);
                if (!vars.get(i).equals("_"))
                    local.assoc(vars.get(i), fieldType);
            }

            ASTType bodyT = br.getBody().typecheck(local);

            if (result == null)
                result = bodyT;
            else if (!result.subtypeOf(bodyT, env))
                throw new TypeCheckError("branches type mismatch");
        }

        return result;
    }
}