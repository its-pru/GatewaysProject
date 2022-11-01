package model.Mapper;

import datasource.ChemicalRowDataGateway;
import datasource.ElementRowDataGateway;
import exceptions.EntryNotFoundException;
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

        ChemicalRowDataGateway chemical = ChemicalRowDataGateway.createChemicalRowDataGateway(name); //Creating chemical in database.

        new ElementRowDataGateway(chemical.getId(), atomicNumber, atomicMass); //using already created chemical to create element in database.


       myElement = new Element(name, atomicNumber, atomicMass);
    }

    /**
     * Constructor for objects that exist in the db
     * @param name
     */
    public ElementMapper(String name) throws ElementNotFoundException {
        ChemicalRowDataGateway chemical = null;
        try {
            chemical = new ChemicalRowDataGateway(name);
        } catch (EntryNotFoundException e) {
            throw new ElementNotFoundException();
        }

        ElementRowDataGateway element = null;
        try {
            element = new ElementRowDataGateway(chemical.getId());
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

        myElement = new Element(name, (int) element.getAtomicNumber(), element.getAtomicMass());
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

    public void persistElement(String name, int atomicNumber, double atomicMass) throws Exception {
        ChemicalRowDataGateway chemical = new ChemicalRowDataGateway(myElement.getName());
        ElementRowDataGateway element = new ElementRowDataGateway(chemical.getId());

        chemical.setName(name);
        element.setAtomicNumber(atomicNumber);
        element.setAtomicMass(atomicMass);

        chemical.persist();
        element.persist();

        myElement = new Element(name, atomicNumber, atomicMass);
    }
}
