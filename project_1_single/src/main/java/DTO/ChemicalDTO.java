package DTO;

/**
 * an object that stores data for a chemical
 */
public class ChemicalDTO {
    long ID;
    String name;
    long atomicNumber;
    double atomicMass;
    long dissolvedBy;
    long solute;

    /**
     * create and initialize a chemical DTO
     *
     * @param ID
     * @param name
     * @param atomicNumber
     * @param atomicMass
     * @param dissolvedBy
     * @param solute
     */
    public ChemicalDTO(long ID, String name, long atomicNumber, double atomicMass, long dissolvedBy, long solute) {
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.dissolvedBy = dissolvedBy;
        this.solute = solute;
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
     * update the dissolved by
     *
     * @param dissolvedBy
     */
    public void setDissolvedBy(long dissolvedBy) {
        this.dissolvedBy = dissolvedBy;
    }

    /**
     * update the solute
     *
     * @param solute
     */
    public void setSolute(long solute) {
        this.solute = solute;
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

    /**
     * return the solute
     *
     * @return solute
     */
    public long getSolute() {
        return solute;
    }
}
