package DTO;

import java.util.List;

public class AcidDTO {
    long ID;
    String name;
    long solute;
    List<Long> dissolves;

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
     * update the solute
     *
     * @param solute
     */
    public void setSolute(long solute) {
        this.solute = solute;
    }
    /**
     * update the dissolves
     *
     * @param dissolves
     */
    public void setMadeOf(List<Long> dissolves) {
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
    public List<Long> getMadeOf() {
        return dissolves;
    }
}
