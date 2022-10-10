package DTO;

public class AcidDTO {
    long ID;
    long solute;

    /**
     * Initializes an Acid DTO
     * @param ID
     * @param solute
     */
    public AcidDTO(long ID, long solute) {
        this.ID = ID;
        this.solute = solute;
    }

    /**
     * return the ID
     * @return ID
     */
    public long getID() {
        return ID;
    }

    /**
     * sets the ID of the acid
     * @param ID unique ID of the acid
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * returns the solute
     * @return solute
     */
    public long getSolute() {
        return solute;
    }

    /**
     * update the solute
     * @param solute solute of the acid
     */
    public void setSolute(long solute) {
        this.solute = solute;
    }
}
