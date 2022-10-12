package DTO;

import datasource.Type;

/**
 * an object that stores data for a chemical
 */
public class ChemicalDTO {
    final long ID;
    final String name;
    final long atomicNumber;
    final double atomicMass;
    final long dissolvedBy;
    final long solute;
    final Type type;

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
    public ChemicalDTO(long ID, String name, long atomicNumber, double atomicMass, long dissolvedBy, long solute, Type type) {
        this.ID = ID;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicMass = atomicMass;
        this.dissolvedBy = dissolvedBy;
        this.solute = solute;
        this.type = type;
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
