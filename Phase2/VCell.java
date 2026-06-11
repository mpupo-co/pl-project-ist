public class VCell implements IValue {
    IValue value;

    public VCell (IValue v){
        this.value = v;
    }

    public IValue get(){
        return value;
    }

    public void set(IValue v){
        this.value = v;
    }
    
    public String toStr() {
        return  "ref@"+this;
    }
}
