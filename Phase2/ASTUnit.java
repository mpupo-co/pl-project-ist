public class ASTUnit implements ASTNode{
    public IValue eval(Environment<IValue> env) throws InstantiationError {
        return new VUnit();
    }

    public ASTType typecheck(Environment<ASTType> env) throws InterpreterError, TypeCheckError {
        return new ASTTUnit();
    }
    
}
