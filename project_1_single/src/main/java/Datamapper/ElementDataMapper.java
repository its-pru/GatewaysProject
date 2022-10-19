package Datamapper;

public class ElementDataMapper {
    long ID;
    String name;
    long atomicNumber;
    double atomicMass;

    public ElementDataMapper(long ID, String name, long atomicNumber, double atomicMass){
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAtomicNumber(long atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public void setAtomicMass(double atomicMass) {
        this.atomicMass = atomicMass;
    }
}
