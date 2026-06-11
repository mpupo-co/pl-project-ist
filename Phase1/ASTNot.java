public class ASTNot implements ASTNode {
    private ASTNode t;

    public ASTNot(ASTNode t){
        this.t = t;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {

        IValue v = t.eval(env);

        if (!(v instanceof VBool)) {
            throw new InterpreterError(
                "Type error: '!' requires boolean, got " + v.toStr()
            );
        }

        boolean res = ((VBool) v).getval();

        return new VBool(!res);
    }
}