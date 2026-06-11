public class ASTTRef implements ASTType {

    private ASTType type;

    public ASTTRef(ASTType _type) {
        this.type = _type;
    }
    
    public ASTType getType() {
        return type;
    }
    public String toStr() {
        return "ref<"+type.toStr()+">";
    }

    public boolean subtypeOf(ASTType t, Environment<ASTType> env)
    {
        if (!(t instanceof ASTTRef))
            return false;

        ASTTRef other = (ASTTRef) t;
        
        return this.type.subtypeOf(other.getType(), env);
    }

}