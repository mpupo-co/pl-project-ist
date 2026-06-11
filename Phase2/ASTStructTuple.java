import java.util.ArrayList;
import java.util.List;

public class ASTStructTuple implements ASTNode {
    private String tag;
    private List<ASTNode> fields;

    public ASTStructTuple(String tag, List<ASTNode> fields) {
        this.tag = tag;
        this.fields = fields;
    }

    public String getTag() {
        return tag;
    }

    public List<ASTNode> getFields() {
        return fields;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        List<IValue> vals = new ArrayList<>();
        for (ASTNode field : fields)
            vals.add(field.eval(env));
        return new VStructTuple(tag, vals);
    }

    public ASTType typecheck(Environment<ASTType> env)
        throws TypeCheckError, InterpreterError {

    ASTType t = env.find(tag);

    if (!(t instanceof ASTTEnum)) {
        throw new TypeCheckError("Unknown constructor: " + tag);
    }

    ASTTEnum et = (ASTTEnum) t;

    ASTTStruct ctor = et.getCases().get(tag);
    if (ctor == null)
        throw new TypeCheckError("Invalid constructor: " + tag);

    List<ASTType> expected = ctor.getFields();

    if (fields.size() != expected.size())
        throw new TypeCheckError("Arity mismatch");

    for (int i = 0; i < fields.size(); i++) {
        ASTType ti = fields.get(i).typecheck(env);
        if (!ti.subtypeOf(expected.get(i), env))
            throw new TypeCheckError("Type mismatch in constructor");
    }

    return et;
}
}
