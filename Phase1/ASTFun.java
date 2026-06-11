public class ASTFun implements ASTNode {
    private String param;
    private ASTNode body;
    
    public ASTFun(String param, ASTNode body) {
        this.param = param;
        this.body = body;
    }

    public void setBody(ASTNode b) 
    {
	body = b;
    }
  
    public IValue eval(Environment<IValue> e) throws InterpreterError
    {
	return new VClosure(param, body, e);
    }

}
