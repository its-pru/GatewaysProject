package DTO;

public class ElementDTO {
    final long ID;
    final long atomicNumber;
    final double atomicMass;

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
     * return the atomic number
     * @return
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }


    /**
     * return the atomic mass
     * @return
     */
    public double getAtomicMass() {
        return atomicMass;
    }

}
