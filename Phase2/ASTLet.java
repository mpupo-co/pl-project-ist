import java.util.List;

public class ASTLet implements ASTNode {

    List<Bind> decls;
    ASTNode body;

    public ASTLet(List<Bind> decls, ASTNode body) {
        this.decls = decls;
        this.body = body;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {

        Environment<IValue> en = e.beginScope();

        for (Bind b : decls) {

            if (en.bindings.containsKey(b.getId())) {
                throw new InterpreterError(
                    "Variable already defined within current scope: "
                    + b.getId()
                );
            }

            IValue value = b.getExp().eval(en);
            en.assoc(b.getId(), value);
        }

        IValue v = body.eval(en);
        en.endScope();

        return v;
    }

    public ASTType typecheck(Environment<ASTType> env)
            throws TypeCheckError, InterpreterError {

        Environment<ASTType> local = env.beginScope();

        for (Bind b : decls) {

            ASTType inferred = ASTTypeDef.unfold(b.getExp().typecheck(local), local);
            ASTType declared = b.getType();

            if (declared != null) {
                declared = ASTTypeDef.unfold(declared, local);

                if (!inferred.subtypeOf(declared, local)) {
                    throw new TypeCheckError("Type mismatch in let binding " + b.getId());
                }

                local.assoc(b.getId(), declared);
            } else {
                local.assoc(b.getId(), inferred);
            }
        }

        ASTType result = body.typecheck(local);

        local.endScope();

        return result;
    }
}