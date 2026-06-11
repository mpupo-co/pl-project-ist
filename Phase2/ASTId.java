public	class ASTId implements ASTNode	{	

    String id;	
    
    public ASTId(String id)	{
        this.id = id;
    }

    public IValue eval(Environment<IValue> env)	throws
    InterpreterError {
        return env.find(id);
        	
    }

    public ASTType typecheck(Environment<ASTType> env) throws
    TypeCheckError, InterpreterError {
        return env.find(id);
    }
}	
