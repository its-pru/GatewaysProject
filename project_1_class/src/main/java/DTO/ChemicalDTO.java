package DTO;

public class ChemicalDTO {
    long ID;
    String name;

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
     * sets the ID
     * @param ID
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * returns the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name
     */
    public void getName(String name) {
        this.name = name;
    }
}
