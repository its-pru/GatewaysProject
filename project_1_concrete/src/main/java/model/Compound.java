package model;
import java.util.ArrayList;
import java.util.List;

public class Compound {
    List<String> madeOf;
    String name;

    public Compound(String name){
        this.name = name;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setMadeOf(List<String> madeOf){this.madeOf = madeOf;}
    public List<String> getMadeOf(){return madeOf;}
    public void addElement(String element){
        madeOf.add(element);
    }
}
