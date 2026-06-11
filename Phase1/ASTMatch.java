import java.util.List;

public class ASTMatch implements ASTNode {

        ASTNode exp, patb, elseb;
        ASTPattern pat;
        
        public IValue eval(Environment<IValue> env) throws InterpreterError {

       IValue v = exp.eval(env);

        if (!(v instanceof VStructTuple)) {
        throw new InterpreterError("illegal types for match operator");
        }

        VStructTuple vst = (VStructTuple) v;

        if (!vst.getTag().equals(pat.getTag()))
                return elseb.eval(env);

        Environment<IValue> matchEnv = env.beginScope();

        List<String> pvs = pat.getPvs();
        List<IValue> fields = vst.getFields();

        if (pvs.size() != fields.size()) {
                throw new InterpreterError(
                "Match error: wrong number of fields for tag " + vst.getTag()
                );
        }

        for (int i = 0; i < pvs.size(); i++) {
                matchEnv.assoc(pvs.get(i), fields.get(i));
        }

        return patb.eval(matchEnv);
        }

        public ASTMatch(ASTNode e, ASTPattern p, ASTNode pb, ASTNode eb) {
                exp = e;
                pat = p;
                patb = pb;
                elseb = eb;
        }
}
