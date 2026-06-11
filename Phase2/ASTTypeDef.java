import java.util.*;

public class ASTTypeDef implements ASTNode {
HashMap<String,ASTType> ltd;
ASTNode body;

    static public ASTType unfold(ASTType t, Environment<ASTType> e) throws InterpreterError
    {
        if (t instanceof ASTTId) {
            ASTTId tid = (ASTTId)t;
            return e.find(tid.toStr());
        } else return t;
    }

    public ASTTypeDef(HashMap<String,ASTType>  ltdp, ASTNode b) {
	ltd = ltdp;
    body = b;
    }
    
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return body.eval(env);
    }

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError
	{
        e = e.beginScope();

        for (String id : ltd.keySet()) {
            ASTType exp = ltd.get(id);
            e.assoc(id, exp);

            // register enum constructors
            if (exp instanceof ASTTEnum) {
                ASTTEnum et = (ASTTEnum) exp;
                for (String tag : et.getCases().keySet())
                    e.assoc(tag, et);
            }

        }
        ASTType returnType = body.typecheck(e);
        e.endScope();
        return returnType;
    }

}
