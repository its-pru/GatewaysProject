package DTO;

public class ElementDTO {
    final long ID;
    final String name;
    final long atomicNumber;
    final double atomicMass;

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

