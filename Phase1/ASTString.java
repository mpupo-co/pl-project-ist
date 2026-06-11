public class ASTString implements ASTNode{
    private String s;

    public ASTString(String s){
        this.s = s;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError{
        return new VString(s);
    }
 
}
