public class VString implements IValue{
    private String s;

    public VString(String s){
        this.s = s;
    }

    public String getval(){
        return s;
    }

    public String toStr(){
        return s;
    }
    
}
