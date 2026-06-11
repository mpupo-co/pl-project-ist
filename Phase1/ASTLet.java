import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
    Environment<IValue> en = e.beginScope();

    for (Bind b : decls) {
        if (en.bindings.containsKey(b.getId())){
            throw new InterpreterError("Variable already defined within current scope: "+b.getId());
        }

        IValue value = b.getExp().eval(en);
        en.assoc(b.getId(), value);
    }
    
    IValue v = body.eval(en);
    en.endScope();
    return v;
}


    public ASTLet(List<Bind> decls, ASTNode body) {
	    this.decls = decls;
	    this.body = body;
    }

}
