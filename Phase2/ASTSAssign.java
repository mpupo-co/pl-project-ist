public class ASTSAssign implements ASTNode {

    ASTNode lhs, rhs;

    public ASTSAssign(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {

        IValue lhsVal = lhs.eval(env);

        if (!(lhsVal instanceof VCell))
            throw new InterpreterError("illegal type for ::= operator");
        
        VCell cell = (VCell) lhsVal;
        IValue oldVal = cell.get();
        IValue newVal = rhs.eval(env);

        cell.set(newVal);

        return oldVal;
        }

    public ASTType typecheck(Environment<ASTType> env) throws TypeCheckError, InterpreterError {

        ASTType tlhs = ASTTypeDef.unfold(lhs.typecheck(env), env);
        ASTType trhs = ASTTypeDef.unfold(rhs.typecheck(env), env);

        if (!(tlhs instanceof ASTTRef))
            throw new TypeCheckError("left operand of ::= must be a type ref");

        ASTType oldValType = ((ASTTRef) tlhs).getType();

        if (!trhs.subtypeOf(oldValType, env))
            throw new TypeCheckError("type mismatch in ::=");
        
        return oldValType;
    }
}
