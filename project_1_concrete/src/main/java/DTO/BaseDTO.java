package DTO;

public class BaseDTO {
    final long ID;
    final String name;
    final long solute;
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
