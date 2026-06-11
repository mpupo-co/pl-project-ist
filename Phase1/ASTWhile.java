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
}
