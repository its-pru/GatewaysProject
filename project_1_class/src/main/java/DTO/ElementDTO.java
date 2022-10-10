package DTO;

public class ElementDTO {
    long ID;
    long atomicNumber;
    double atomicMass;

    /**
     * Creates an element DTO
     * @param ID
     * @param atomicNumber
     * @param atomicMass
     */
    public ElementDTO(long ID, long atomicNumber, double atomicMass){
        this.ID = ID;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
    }

    /**
     * return the ID
     * @return
     */
    public long getID() {
        return ID;
    }

    /**
     * set the ID
     * @param ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * return the atomic number
     * @return
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * set the atomic number
     * @param atomicNumber
     */
    public void setAtomicNumber(long atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    /**
     * return the atomic mass
     * @return
     */
    public double getAtomicMass() {
        return atomicMass;
    }

    /**
     * set the atomic mass
     * @param atomicMass
     */
    public void setAtomicMass(double atomicMass) {
        this.atomicMass = atomicMass;
    }
}
