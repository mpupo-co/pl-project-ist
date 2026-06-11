public class ASTPrint implements ASTNode {
    ASTNode exp;

    public ASTPrint(ASTNode exp){
        this.exp = exp;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = exp.eval(env);

        System.out.print(v.toStr());

        return new VUnit();
    }
}