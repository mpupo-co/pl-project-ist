public class ASTCell implements ASTNode {
    private ASTNode exp;

    public ASTCell (ASTNode exp){
        this.exp = exp;
    }
    
     public IValue eval(Environment<IValue> e) throws InterpreterError
    {
        IValue val = exp.eval(e);
	    return new VCell(val);                
    }
}
