public class ASTDeref implements ASTNode {
    private ASTNode exp;

    public ASTDeref (ASTNode exp){
        this.exp = exp;
    }
        
     public IValue eval(Environment<IValue> e) throws InterpreterError
    {
        IValue val = exp.eval(e);
        if (!(val instanceof VCell)) {
            throw new InterpreterError(
                "illegal type for dereference. '!' expects a cell");
        }
        VCell cell = (VCell)val;
        return cell.get();                
    }
}


