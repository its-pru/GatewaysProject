package model;

public class Element
{
    String name;
    long atomicNumber;
    double atomicMass;

    public Element (String name, int atomicNumber, double atomicMass) {
        this.name = name;
        this.atomicMass = atomicMass;
        this.atomicNumber = atomicNumber;
    }

    public String getName()
    {
        return name;
    }

    public long getAtomicNumber()
    {
        return atomicNumber;
    }

    public double getAtomicMass()
    {
        return atomicMass;
    }

    public int getPeriod()
    {
        return 0;
    }

    public void setName(String name) {this.name = name;}

    public void setAtomicNumber(long atomicNumber) {this.atomicNumber = atomicNumber;}

    public void setAtomicMass(double atomicMass) {this.atomicMass = atomicMass;}
}
