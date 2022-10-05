package DTO;

import java.util.List;

public class CompoundDTO {
    long ID;
    String name;
    List<Long> madeOf;
    /**
     * Inititalizes a compound DTO object
     *
     * @param ID
     * @param name
     * @param madeOf
     */
    public CompoundDTO(long ID, String name, List<Long> madeOf) {
        this.ID = ID;
        this.name = name;
        this.madeOf = madeOf;
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
     * update the made of
     *
     * @param madeOf
     */
    public void setMadeOf(List<Long> madeOf) {
        this.madeOf = madeOf;
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
     * return the list of made of
     *
     * @return made of
     */
    public List<Long> getMadeOf() {
        return madeOf;
    }
}
