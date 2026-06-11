import java.util.List;

public class VStructTuple implements IValue {
    private String tag;
    private List<IValue> fields;

    public VStructTuple(String tag, List<IValue> fields){
        this.tag = tag;
        this.fields = fields;
    }

    public String  getTag(){
        return tag;
    }

    public List<IValue> getFields(){
        return fields;
    }

    public IValue getField(int i){
        return fields.get(i);
    }

    public String toStr(){
        StringBuilder sb = new StringBuilder();
        sb.append(tag).append("(");
        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i).toStr());
            if (i < fields.size() - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();

    }
    
}
