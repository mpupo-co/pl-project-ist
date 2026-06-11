public class ASTDiv implements ASTNode {

    ASTNode lhs, rhs;

    public ASTDiv(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {

        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (!(v1 instanceof VInt) || !(v2 instanceof VInt)) {
            throw new InterpreterError(
                "illegal type for / operator"
            );
        }

        int i1 = ((VInt) v1).getval();
        int i2 = ((VInt) v2).getval();

        if (i2 == 0) {
            throw new InterpreterError("runtime error: division by zero");
        }

        return new VInt(i1 / i2);
    }

    public ASTType typecheck(Environment<ASTType> e) throws InterpreterError, TypeCheckError {
        ASTType tlhs = lhs.typecheck(e);
        ASTType trhs = rhs. typecheck(e);
        if (!(tlhs instanceof ASTTInt) || !(trhs instanceof ASTTInt))
            throw new TypeCheckError("Operands must be type int");
        return new ASTTInt();
    }
}