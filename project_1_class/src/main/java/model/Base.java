package model;

public class Base extends Chemical{
    String name;
    Chemical solute;

    public Base(String name, Chemical solute) {
        super(name);
        this.solute = solute;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Chemical getSolute() {return solute;}

    //todo Do we need a setter for solute?
}
