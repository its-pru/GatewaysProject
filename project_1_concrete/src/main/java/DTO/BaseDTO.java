package DTO;

public class BaseDTO {
    long ID;
    String name;
    long solute;
    /**
     * Inititalizes a base DTO object
     *
     * @param ID
     * @param name
     * @param solute
     */
    public BaseDTO(long ID, String name, long solute) {
        this.ID = ID;
        this.name = name;
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
     * return the solute
     *
     * @return solute
     */
    public long getSolute() {
        return solute;
    }
}
