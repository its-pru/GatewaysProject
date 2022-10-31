package model.Mapper;

import datasource.ChemicalRowDataGateway;
import datasource.ElementRowDataGateway;
import model.Element;

public class ElementMapper
{
    private Element myElement;
    /**
     * Create a new element in the database, and store the resulting model object
     * into my instance variable
     */
    public ElementMapper(String name, int atomicNumber, double atomicMass) throws Exception
    {
        ChemicalRowDataGateway createChemical;
        long chemicalID = 0;
        ElementRowDataGateway createElement;
        
        try{
            createChemical = new ChemicalRowDataGateway(name);
            chemicalID = createChemical.getId();
        }catch(Exception e){

        }

        try {
            createElement = new ElementRowDataGateway(chemicalID, atomicNumber, atomicMass);
        }catch(Exception elementException){

        }
    }

    /**
     * Constructor for objects that exist in the db
     * @param name
     */
    public ElementMapper(String name) throws ElementNotFoundException
    {
    }

    public Element getMyElement()
    {
        return myElement;
    }
}
