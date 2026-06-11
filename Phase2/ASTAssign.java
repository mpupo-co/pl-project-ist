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

    public ASTType typecheck(Environment<ASTType> env)
        throws InterpreterError, TypeCheckError {

    ASTType tlhs = ASTTypeDef.unfold(lhs.typecheck(env), env);
    ASTType trhs = ASTTypeDef.unfold(rhs.typecheck(env), env);

    if (!(tlhs instanceof ASTTRef))
        throw new TypeCheckError("left operand must be ref type");

    ASTType target = ASTTypeDef.unfold(((ASTTRef) tlhs).getType(), env);

   
    if (!trhs.subtypeOf(target, env))
        throw new TypeCheckError("type mismatch in assignment");

    return new ASTTUnit();
}
}
