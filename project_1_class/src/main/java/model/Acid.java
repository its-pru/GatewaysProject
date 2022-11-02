package model;

public class Acid extends Chemical{
    Chemical solute;
    Chemical[] dissolves;
    String name;
    public Acid(String name, Chemical solute, Chemical[] dissolves){
        super (name);
        this.solute = solute;
        this.dissolves = dissolves;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Chemical getSolute() {return solute;}
    public Chemical[] getDissolves() {return dissolves;}

    //todo Do we need setters for solute and dissolves?
}
