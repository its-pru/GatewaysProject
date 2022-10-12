package DTO;

import java.util.List;

public class MadeOfDTO {

    final List<Long> listOfElementIDs;
    final long chemicalID;

    /**
     * Object used for transfering data from gateways to business logic layer
     * @param chemicalID - ID of the chemical being stored
     * @param listOfElementIDs - IDs of all elements being stored
     */
    public MadeOfDTO(long chemicalID, List<Long> listOfElementIDs){
        this.chemicalID = chemicalID;
        this.listOfElementIDs = listOfElementIDs;
    }

    public List<Long> getListOfElementIDs(){
        return listOfElementIDs;
    }

    public long getChemicalID(){
        return chemicalID;
    }

}
