public class ASTAssign implements ASTNode {
    ASTNode lhs, rhs;

    ASTAssign (ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {

        IValue lhsVal = lhs.eval(env);

        if (!(lhsVal instanceof VCell)) {
            throw new InterpreterError("illegal type for := operator");
        }

        VCell cell = (VCell) lhsVal;

        IValue value = rhs.eval(env);

        cell.set(value);

        return value;
    }
}
