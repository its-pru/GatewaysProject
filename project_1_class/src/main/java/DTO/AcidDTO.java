package DTO;

public class AcidDTO {
    final long ID;
    final long solute;

    /**
     * Initializes an Acid DTO
     *
     * @param ID
     * @param solute
     */
    public AcidDTO(long ID, long solute) {
        this.ID = ID;
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
     * returns the solute
     *
     * @return solute
     */
    public long getSolute() {
        return solute;
    }

}
