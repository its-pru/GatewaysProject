package model;
import java.util.ArrayList;
import java.util.List;
public class Compound extends Chemical{
    List<String> madeOf;
    public Compound(String name) {
        super(name);
        madeOf = new ArrayList<String>();
    }
    public void setName(String name) { super.setName(name); }
    public String getName() { return super.getName(); }
    public void setMadeOf(List<String> madeOf) { this.madeOf = madeOf; }
    public List<String> getMadeOf() { return madeOf; }
    public void addElement(String element){
        madeOf.add(element);
    }
}
