public class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }
    public String toStr() {
        return id;
    }

    public boolean subtypeOf(ASTType other, Environment<ASTType> env) {
    return this.equals(other);
}
}