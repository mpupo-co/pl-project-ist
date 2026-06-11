public class VClosure implements IValue {
    private String param;
    private ASTNode body;
    private Environment<IValue> env;

    public VClosure(String p, ASTNode b, Environment<IValue> e) {
        param = p;
        body = b;
        env = e;
    }

    public IValue apply(IValue arg) throws InterpreterError {
        Environment<IValue> funEnv = env.beginScope();
        funEnv.assoc(param, arg);
        return body.eval(funEnv);
    }
    

    @Override
    public String toStr(){
        return "VClosure{parameters=" + param + ", body=" + body
                + ", env=" + env + "}"; 
    }
    
}
