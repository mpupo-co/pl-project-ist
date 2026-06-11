class ASTTUnit implements ASTType {
        
    static public ASTTUnit tunit = new ASTTUnit();

    public ASTTUnit() {
    }

    public String toStr() {
        return "()";
    }
    
    public boolean subtypeOf(ASTType other, Environment<ASTType> env) {
        return this.getClass() == other.getClass();
    }
    
}