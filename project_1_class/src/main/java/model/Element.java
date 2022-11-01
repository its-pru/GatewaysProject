package model;

public class Element
{
    String name;
    int atomicNumber;
    double atomicMass;
    int period;

    public Element (String name, int atomicNumber, double atomicMass) {
        this.name = name;
        this.atomicMass = atomicMass;
        this.atomicNumber = atomicNumber;

    }

    public String getName()
    {
        return name;
    }

    public int getAtomicNumber()
    {
        return atomicNumber;
    }

    public double getAtomicMass()
    {
        return atomicMass;
    }

    public int getPeriod()
    {
        return period;
    }

    public void setName(String name) {this.name = name;}

    public void setAtomicNumber(int atomicNumber) {this.atomicNumber = atomicNumber;}

    public void setAtomicMass(double atomicMass) {this.atomicMass = atomicMass;}
}
