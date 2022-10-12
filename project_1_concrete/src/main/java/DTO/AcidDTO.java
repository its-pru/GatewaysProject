package DTO;

import java.util.List;

public class AcidDTO {
    final long ID;
    final String name;
    final long solute;
    final List<Long> dissolves;

    /**
     * Inititalizes a acid DTO object
     *
     * @param ID
     * @param name
     * @param solute
     * @param dissolves
     */
    public AcidDTO(long ID, String name, long solute, List<Long> dissolves) {
        this.ID = ID;
        this.name = name;
        this.solute = solute;
        this.dissolves = dissolves;
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
     * returns the solute
     *
     * @return solute
     */
    public long getSolute() {
        return solute;
    }
    /**
     * return the list of dissolves
     *
     * @return dissolves
     */
    public List<Long> getDissolves() {
        return dissolves;
    }
}
