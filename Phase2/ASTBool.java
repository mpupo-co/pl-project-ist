class ASTBool implements ASTNode {
    
    boolean bv;
    
    public ASTBool(boolean b) {
        bv = b;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError{
        return new VBool(bv);
    }


    public ASTType typecheck(Environment<ASTType> e) throws TypeCheckError, InterpreterError
	{
		return ASTTBool.tbool;
    }
	
    
}