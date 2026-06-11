class Bind {
    private final String id;
    private final ASTType type;
    private final ASTNode exp;

    public Bind( String _id, ASTType typ, ASTNode _exp) {
        this.id = _id;
        this.exp = _exp;
        this.type = typ;
    }

    public String getId() {
        return id;
    }

    public ASTNode getExp() {
        return exp;
    }

    public ASTType getType() {
        return type;
}
}
