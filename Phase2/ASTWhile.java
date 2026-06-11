public class ASTWhile implements ASTNode{
    private ASTNode cond;
    private ASTNode body;

    public ASTWhile (ASTNode cond, ASTNode body){
        this.cond = cond;
        this.body = body;
    }
    public IValue eval(Environment<IValue> env) throws InterpreterError{
        while (((VBool)cond.eval(env)).getval())
            body.eval(env);
        return new VUnit();
    }

    public ASTType typecheck(Environment<ASTType> env) throws InterpreterError, TypeCheckError {
        ASTType t_cond = cond.typecheck(env);

        if(!(t_cond instanceof ASTTBool))
            throw new InterpreterError("Condition must be type boolean");
        body.typecheck(env);
        return new ASTTUnit();
    }
}
