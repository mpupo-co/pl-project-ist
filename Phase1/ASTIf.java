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
}
