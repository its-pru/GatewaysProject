package DTO;

public class MetalDTO {
    final long ID;
    final String name;
    final long atomicNumber;
    final double atomicMass;
    final long dissolvedBy;

    /**
     * Inititalizes a metal DTO object
     *
     * @param ID
     * @param name
     * @param atomicNumber
     * @param atomicMass
     * @param dissolvedBy
     */
    public MetalDTO(long ID, String name, long atomicNumber, double atomicMass, long dissolvedBy) {
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.dissolvedBy = dissolvedBy;
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

    /**
     * return the dissolved by
     *
     * @return dissolvedBy
     */
    public long getDissolvedBy() {
        return dissolvedBy;
    }
}
