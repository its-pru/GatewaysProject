package DTO;

public class BaseDTO {
    final long ID;
    final long solute;

    /**
     * create a base DTO
     * @param ID
     * @param solute
     */
    public BaseDTO(long ID, long solute) {
        this.ID = ID;
        this.solute = solute;
    }

    /**
     * return the ID
     * @return
     */
    public long getID() {
        return ID;
    }


    /**
     * return the solute
     * @return
     */
    public long getSolute() {
        return solute;
    }

}
