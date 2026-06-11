public class ASTSeq implements ASTNode {
    ASTNode lhs, rhs;

    public ASTSeq(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        lhs.eval(env); // evaluate first expression  and ignore result
        return rhs.eval(env); // evaluate second and return it
    }
    
}