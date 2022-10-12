package DTO;

public class MetalDTO {
    long ID;
    long dissolvedBy;

    /**
     * Creates a metal DTO
     * @param ID
     * @param dissolvedBy
     */
    public MetalDTO(long ID, long dissolvedBy) {
        this.ID = ID;
        this.dissolvedBy = dissolvedBy;
    }

    /**
     * returns the ID
     * @return
     */
    public long getID() {
        return ID;
    }


    /**
     * returns the dissolved by ID
     * @return ID of the object that dissolves
     */
    public long getDissolvedBy() {
        return dissolvedBy;
    }

}
