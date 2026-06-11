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

    public ASTType typecheck(Environment<ASTType> env)
    throws InterpreterError, TypeCheckError {

    ASTType funType = ASTTypeDef.unfold(lhs.typecheck(env), env);
    ASTType argType = ASTTypeDef.unfold(rhs.typecheck(env), env);

    if (!(funType instanceof ASTTArrow)) 
        throw new TypeCheckError("attempting to apply a non-function type");

    ASTTArrow arrow = (ASTTArrow) funType;
    ASTType dom = ASTTypeDef.unfold(arrow.getDom(), env);

    if (!argType.subtypeOf(dom, env))
        throw new TypeCheckError("function argument type mismatch");

    return arrow.getCodom();
}
}