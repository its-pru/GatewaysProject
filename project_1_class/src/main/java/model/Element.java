package model;

import model.Mapper.ElementMapper;

public class Element extends Chemical
{
    int atomicNumber;
    double atomicMass;
    int period;
    ElementMapper mapper;


    public Element (String name, int atomicNumber, double atomicMass, ElementMapper mapper) throws Exception {
        super(name);
        this.atomicMass = atomicMass;
        this.atomicNumber = atomicNumber;
        this.mapper = mapper;
        if (atomicNumber == 1 || atomicNumber == 2) {
            period = 1;
        }
        else if (atomicNumber >= 3 && atomicNumber <=10) {
            period = 2;
        }
        else if (atomicNumber >=11 && atomicNumber <=18) {
            period = 3;
        }
        else if (atomicNumber >= 19 && atomicNumber <=36) {
            period = 4;
        }
        else if (atomicNumber >= 37 && atomicNumber <=54) {
            period = 5;
        }
        else if (atomicNumber >= 55 && atomicNumber <= 86) {
            period = 6;
        }
        else if (atomicNumber >= 87 && atomicNumber <=118) {
            period = 7;
        }
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

    public void persist() throws Exception {
        mapper.persistElement(this);
        this.nameBefore = name;
    }
}
