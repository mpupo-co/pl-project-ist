public class ASTUnit implements ASTNode{
    public IValue eval(Environment<IValue> env) throws InstantiationError {
        return new VUnit();
    }
    
}
