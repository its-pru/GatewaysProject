package DTO;

public class ChemicalDTO {
    final long ID;
    final String name;

    /**
     * Creates a chemical DTO
     * @param ID
     * @param name
     */
    public ChemicalDTO(long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * returns the ID
     * @return
     */
    public long getID() {
        return ID;
    }

    /**
     * returns the name
     * @return
     */
    public String getName() {
        return name;
    }

}
