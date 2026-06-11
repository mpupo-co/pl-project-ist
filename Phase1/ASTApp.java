public class ASTApp implements ASTNode {
    private ASTNode lhs, rhs;

    public ASTApp(ASTNode function, ASTNode argument) {
        this.lhs = function;
        this.rhs = argument;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {

        IValue f = lhs.eval(env);
         
        if (!(f instanceof VClosure)) {
            throw new InterpreterError("illegal types: attempting to call a non-function value");
        }

        VClosure fun = (VClosure) f;

        IValue argVal = rhs.eval(env);

        return fun.apply(argVal);
    }

    
}