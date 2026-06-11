class ASTTBool implements ASTType {

    static public ASTType tbool = new ASTTBool();
        
    public ASTTBool() {
    }
    public String toStr() {
        return "bool";
    }

    public boolean subtypeOf(ASTType other, Environment<ASTType> env) {
        return this.getClass() == other.getClass();
    }
}