package model;

import java.util.List;

public class Compound extends Chemical{
    String name;
    List<String> madeOf;
    public Compound(String name) {
        super(name);
    }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setMadeOf(List<String> madeOf) { this.madeOf = madeOf; } // todo Do we need this?
    public List<String> getMadeOf() { return madeOf; }
    public void addElement(String element){
        madeOf.add(element);
    }
}
