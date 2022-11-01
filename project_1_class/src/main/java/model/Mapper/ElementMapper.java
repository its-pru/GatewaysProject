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
        ChemicalRowDataGateway createChemical = null;
        long chemicalID = 0;
        ElementRowDataGateway createElement = null;

        try{
            createChemical = createChemical.createChemicalRowDataGateway(name); //Creating chemical in database.
            chemicalID = createChemical.getId();
            createChemical.persist();
        }catch(Exception e){

        }

        try {
            createElement = new ElementRowDataGateway(chemicalID, atomicNumber, atomicMass); //using already created chemical to create element in database.
            createElement.persist();
        }catch(Exception elementException){

        }

       myElement = new Element(name, atomicNumber, atomicMass);
    }

    /**
     * Constructor for objects that exist in the db
     * @param name
     */
    public ElementMapper(String name) throws ElementNotFoundException
    {
        ChemicalRowDataGateway createChemical = null;
        long chemicalID = 0;
        ElementRowDataGateway createElement = null;

        try{
            createChemical = new ChemicalRowDataGateway(name);
            chemicalID = createChemical.getId();
        }catch(Exception e){

        }

        try {
            createElement = new ElementRowDataGateway(chemicalID);
        }catch(Exception elementException){

        }

        myElement = new Element(name, (int) createElement.getAtomicNumber(), createElement.getAtomicMass());
    }

    public Element getMyElement()
    {
        return myElement;
    }

    /**
     *
     * @param name
     * @throws Exception
     * I think this is how deleting an Element would look.
     */
    public void deleteElement(String name) throws Exception {
        ChemicalRowDataGateway chemical = new ChemicalRowDataGateway(name);
        ElementRowDataGateway element = new ElementRowDataGateway(chemical.getId());

        chemical.delete();
        element.delete();
    }
}
