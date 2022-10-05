package DTO;

public class ElementDTO {
    long ID;
    String name;
    long atomicNumber;
    double atomicMass;

    /**
     * Inititalizes a element DTO object
     *
     * @param ID
     * @param name
     * @param atomicNumber
     * @param atomicMass
     */
    public ElementDTO(long ID, String name, long atomicNumber, double atomicMass) {
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
    }
    /**
     * update the ID
     *
     * @param ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * update the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * update the atomic number
     *
     * @param atomicNumber
     */
    public void setAtomicNumber(long atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    /**
     * update the atomic mass
     *
     * @param atomicMass
     */
    public void setAtomicMass(double atomicMass) {
        this.atomicMass = atomicMass;
    }

    /**
     * return the ID
     *
     * @return ID
     */
    public long getID() {
        return ID;
    }

    /**
     * return the name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * return the atomic number
     *
     * @return atomicNumber
     */
    public long getAtomicNumber() {
        return atomicNumber;
    }

    /**
     * return the atomic mass
     *
     * @return atomicMass
     */
    public double getAtomicMass() {
        return atomicMass;
    }
}

