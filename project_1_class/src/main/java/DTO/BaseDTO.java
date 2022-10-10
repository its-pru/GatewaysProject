package DTO;

public class BaseDTO {
    long ID;
    long solute;

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
     * sets the ID
     * @param ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * return the solute
     * @return
     */
    public long getSolute() {
        return solute;
    }

    /**
     * set the solute
     * @param solute
     */
    public void setSolute(long solute) {
        this.solute = solute;
    }
}
