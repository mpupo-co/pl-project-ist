import java.util.ArrayList;

public class ASTTStruct {

    private String tag;
    private ArrayList<ASTType> fields;

    public ASTTStruct(String tag, ArrayList<ASTType> fields) {
        this.tag = tag;
        this.fields = fields;
    }

    public String getTag() {
        return tag;
    }

    public ArrayList<ASTType> getFields() {
        return fields;
    }

    public String toStr() {
        StringBuilder sb = new StringBuilder();

        sb.append(tag);
        sb.append(":(");

        for (int i = 0; i < fields.size(); i++) {
            if (i > 0)
                sb.append(",");

            sb.append(fields.get(i).toStr());
        }

        sb.append(")");

        return sb.toString();
    }
}