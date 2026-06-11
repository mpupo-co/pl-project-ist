public class ASTComp implements ASTNode {
    private ASTNode t1;
    private ASTNode t2;
    private String op;
    
    public ASTComp(ASTNode t1, String op, ASTNode t2){
        this.t1=t1;
        this.op=op;
        this.t2=t2;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError{
        IValue v1=t1.eval(env);
        IValue v2=t2.eval(env);
    
        if(op == "==")
            return new VBool(v1.toStr().equals(v2.toStr()));
        if (op == "~=")
            return new VBool(!v1.toStr().equals(v2.toStr()));

        if (!(v1 instanceof VInt) || !(v2 instanceof VInt)) 
            throw new InterpreterError("illegal types for comparision operator");

        int val1 = ((VInt)v1).getval();
        int val2 = ((VInt)v2).getval();
        boolean res;
        
        if (op == ">=")
            res = (val1 >= val2);
        else if (op == "<=")
            res = (val1 <= val2);
        else if (op == "<")
            res = (val1 < val2);
        else if (op == ">")
            res = (val1 > val2);
        else
            throw new InterpreterError("unknown operator");
        return new VBool(res);
        }
}