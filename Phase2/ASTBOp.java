public class ASTBOp implements ASTNode {
    private ASTNode t1;
    private String op;
    private ASTNode t2;
    
    public ASTBOp (ASTNode t1, String op, ASTNode t2){
        this.t1 = t1;
        this.op = op;
        this.t2 = t2;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError{
        IValue val1 = t1.eval(env);
        IValue val2 = t2.eval(env);

        if (!(val1 instanceof VBool) || !(val2 instanceof VBool))
            throw new InterpreterError("illegal types for "+ op + " operator");

        boolean v1 = ((VBool)val1).getval();
        boolean v2 = ((VBool)val2).getval();

        if (op == "&&")
            return new VBool(v1 && v2);

        else if (op == "||")
            return new VBool(v1 || v2);

        else
            throw new InterpreterError("unknown operator");
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeCheckError, InterpreterError {
        ASTType left = ASTTypeDef.unfold(t1.typecheck(env), env);
        ASTType right = ASTTypeDef.unfold(t2.typecheck(env), env);

        if (!(left instanceof ASTTBool) || !(right instanceof ASTTBool))
            throw new TypeCheckError("operator " + op + " requires boolean operands");

        return new ASTTBool();
    }
}
