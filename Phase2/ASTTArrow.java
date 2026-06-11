public class ASTTArrow implements ASTType {
    ASTType dom;
    ASTType codom;

    public ASTTArrow(ASTType d, ASTType co) {
        dom = d;
        codom = co;
    }

    public ASTType getCodom() {
        return codom;
    }

    public ASTType getDom() {
        return dom;
    }

    public String toStr() {
        return "("+dom.toStr()+"->"+codom.toStr()+")";
    }   

    public boolean subtypeOf(ASTType t, Environment<ASTType> env) {
    try {
        t = ASTTypeDef.unfold(t, env);
        if (!(t instanceof ASTTArrow)) return false;

        ASTTArrow other = (ASTTArrow) t;

        ASTType myDom    = ASTTypeDef.unfold(this.dom, env);
        ASTType otherDom = ASTTypeDef.unfold(other.dom, env);
        ASTType myCod    = ASTTypeDef.unfold(this.codom, env);
        ASTType otherCod = ASTTypeDef.unfold(other.codom, env);


        if (!otherDom.subtypeOf(myDom, env)) return false;
        if (!myCod.subtypeOf(otherCod, env)) return false;

    } catch (InterpreterError e) { return false; }

    return true;
}
}