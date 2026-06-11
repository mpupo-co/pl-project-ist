import java.util.ArrayList;
import java.util.List;

public class ASTStructTuple implements ASTNode {
    private String tag;
    private List<ASTNode> fields;

    public ASTStructTuple(String tag, List<ASTNode> fields) {
        this.tag = tag;
        this.fields = fields;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        List<IValue> vals = new ArrayList<>();
        for (ASTNode field : fields)
            vals.add(field.eval(env));
        return new VStructTuple(tag, vals);
    }
}