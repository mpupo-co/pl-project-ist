public class ASTIf implements ASTNode {
    private ASTNode cond;
    private ASTNode thenB;
    private ASTNode elseB;

    public ASTIf(ASTNode cond, ASTNode thenB, ASTNode elseB){
        this.cond = cond;
        this.thenB = thenB;
        this.elseB = elseB;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError{
        boolean condition = ((VBool)cond.eval(env)).getval();
        if (condition)
            return thenB.eval(env);
        else
            return elseB.eval(env);
    }

    public ASTType typecheck(Environment<ASTType> env) throws InterpreterError, TypeCheckError {
        ASTType t_cond = cond.typecheck(env);
        ASTType t_thenB = thenB.typecheck(env);
        ASTType t_elseB = elseB.typecheck(env);
        
        if (!(t_cond instanceof ASTTBool)) 
            throw new TypeCheckError("condition must be type boolean");

        if(!t_thenB.subtypeOf(t_elseB,env))
            throw new TypeCheckError("branches must have the same type");

        return t_elseB;


    }
}
