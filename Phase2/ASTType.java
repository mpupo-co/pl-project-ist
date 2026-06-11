public interface ASTType  {
    String toStr();
    public boolean subtypeOf(ASTType other, Environment<ASTType> env);
}


