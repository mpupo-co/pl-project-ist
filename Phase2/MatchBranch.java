public class MatchBranch {
    private ASTPattern pattern;
    private ASTNode body;

    public MatchBranch(ASTPattern p, ASTNode b) {
        this.pattern = p;
        this.body = b;
    }

    public ASTPattern getPattern() {
        return pattern;
    }

    public ASTNode getBody() {
        return body;
    }
}