package model;

import model.Mapper.ElementMapper;

public class Element {
    String name;
    double atomicMass;
    int atomicNumber;
    ElementMapper mapper;
    int period;
    String nameBefore;
    public Element(String name, double atomicMass, int atomicNumber, ElementMapper e){
        this.name = name;
        this.atomicMass = atomicMass;
        this.atomicNumber = atomicNumber;
        mapper = e;

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

    public void setName(String name){
        nameBefore = this.name;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getPeriod(){
        return period;
    }

    public void setAtomicNumber(int atomicNumber){
        this.atomicNumber = atomicNumber;
    }

    public int getAtomicNumber(){
        return atomicNumber;
    }

    public void setAtomicMass(double mass){
        atomicMass = mass;
    }

    public double getAtomicMass(){
        return atomicMass;
    }
    public void persist() throws Exception {
        mapper.persistElement(this);
        this.nameBefore = name;
    }

    public String getNameBefore(){return nameBefore;}
}
