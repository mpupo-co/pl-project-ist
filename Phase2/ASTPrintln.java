public class ASTPrintln implements ASTNode {
    ASTNode exp;

    public ASTPrintln(ASTNode exp){
        this.exp = exp;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = exp.eval(env);

        System.out.println(v.toStr());

        return new VUnit();
    }

    public ASTType typecheck(Environment<ASTType> env) throws InterpreterError, TypeCheckError {
        exp.typecheck(env);
        return new ASTTUnit();
    }
}