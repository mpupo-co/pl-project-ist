public class ASTFun implements ASTNode {
    private String param;
    private ASTType paramType;
    private ASTNode body;
    
    public ASTFun(String param, ASTType paramType, ASTNode body) {
        this.param = param;
        this.paramType = paramType;
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

    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError {
        Environment<ASTType> env = e.beginScope();
        env.assoc(param, paramType); // extend environment with param type

        ASTType bodyType = body.typecheck(env);
        env.endScope();

        return new ASTTArrow(paramType, bodyType);
    }
}
