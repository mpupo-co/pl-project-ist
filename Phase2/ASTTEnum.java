import java.util.HashMap;
import java.util.List;

public class ASTTEnum implements ASTType {

    private HashMap<String, ASTTStruct> cases;

    public ASTTEnum(HashMap<String, ASTTStruct> cases) {
        this.cases = cases;
    }

    public HashMap<String, ASTTStruct> getCases() {
        return cases;
    }

    public boolean hasTag(String tag) {
        return cases.containsKey(tag);
    }

    public ASTTStruct getCase(String tag) {
        return cases.get(tag);
    }
    public String toStr() {

        StringBuilder sb = new StringBuilder();

        sb.append("enum { ");

        boolean first = true;

        for (ASTTStruct c : cases.values()) {

            if (!first)
                sb.append(", ");

            first = false;

            sb.append(c.toStr());
        }

        sb.append(" }");

        return sb.toString();
    }

    public boolean subtypeOf(ASTType t, Environment<ASTType> env) {
    try { t = ASTTypeDef.unfold(t, env); } 
    catch (InterpreterError e) { return false; }

    if (!(t instanceof ASTTEnum)) return false;

    ASTTEnum superType = (ASTTEnum) t;

    // A <: B: every tag in THIS (A) must exist in superType (B)
    for (String tag : this.cases.keySet()) {
        if (!superType.getCases().containsKey(tag))
            return false;

        ASTTStruct subCtor   = this.cases.get(tag);
        ASTTStruct superCtor = superType.getCases().get(tag);

        List<ASTType> subFields   = subCtor.getFields();
        List<ASTType> superFields = superCtor.getFields();

        // depth: subtype may have MORE fields
        if (subFields.size() < superFields.size())
            return false;

        for (int i = 0; i < superFields.size(); i++) {
            ASTType subT, superT;
            try {
                subT   = ASTTypeDef.unfold(subFields.get(i), env);
                superT = ASTTypeDef.unfold(superFields.get(i), env);
            } catch (InterpreterError e) { return false; }

            if (!subT.subtypeOf(superT, env))
                return false;
        }
    }

    return true;
} 
}