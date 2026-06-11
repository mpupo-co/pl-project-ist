public class ASTTString implements ASTType {

    public ASTTString() {}

    public String toStr() {
        return "string";
    }

    public boolean subtypeOf(ASTType other, Environment<ASTType> env) {
        return this.getClass() == other.getClass();
    }
}
