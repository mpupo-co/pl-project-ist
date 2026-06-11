public class ASTTInt implements ASTType {
    
    static public ASTTInt tint = new ASTTInt();

    public String toStr() {
        return "int";
    }

    public boolean subtypeOf(ASTType other, Environment<ASTType> env) {
        return this.getClass() == other.getClass();
    }

}


