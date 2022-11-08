package model.Mapper;

import DTO.ChemicalDTO;
import DTO.ElementDTO;
import datasource.*;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import model.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementMapper {
    /**
     * Grabs all elements in the data source
     *
     * @return list of elements
     * @throws ElementNotFoundException
     */
    public static Element[] getAllElements() throws ElementNotFoundException {
        List<ChemicalDTO> chemicals = null;
        List<ElementDTO> elements = null;
        try {
            chemicals = ChemicalTableDataGateway.getAllChemicals();
            elements = ElementTableDataGateway.getAllElements();
        } catch (UnableToConnectException e) {
            throw new ElementNotFoundException();
        }
        List<Element> temp = new ArrayList<Element>();

        for (int i = 0; i < elements.size(); i++) {
            ElementDTO element = elements.get(i);
            String name = null;

            for (int j = 0; j < chemicals.size(); j++) {
                if (element.getID() == chemicals.get(j).getID()) {
                    name = chemicals.get(j).getName();
                    break;
                }
            }
            int atomicNumber = (int) element.getAtomicNumber();
            double atomicMass = element.getAtomicMass();
            temp.add(new Element(name, atomicNumber, atomicMass, new ElementMapper(name)));
        }
        return temp.toArray(new Element[temp.size()]);
    }


    private Element myElement;


    /**
     * Constructor for elements that do not exist in the data source
     *
     * @param name
     * @param atomicNumber
     * @param atomicMass
     * @throws ElementNotFoundException
     */
    public ElementMapper(String name, int atomicNumber, double atomicMass) throws ElementNotFoundException {
        ChemicalRowDataGateway chemical = null; //Creating chemical in database.
        try {
            chemical = ChemicalRowDataGateway.createChemicalRowDataGateway(name);
            new ElementRowDataGateway(chemical.getId(), atomicNumber, atomicMass); //using already created chemical to create element in database.
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

        myElement = new Element(name, atomicNumber, atomicMass, this);
    }

    /**
     * Constructor for objects that exist in the data source
     *
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

        myElement = new Element(name, (int) element.getAtomicNumber(), element.getAtomicMass(), this);
    }

    public Element getMyElement() {
        return myElement;
    }

    /**
     * Delete element by name using RowDataGateways
     * @param name
     * @throws Exception I think this is how deleting an Element would look.
     */
    public void deleteElement(String name) throws ElementNotFoundException {
        ChemicalRowDataGateway chemical = null;
        ElementRowDataGateway element = null;
        try {
            chemical = new ChemicalRowDataGateway(name);
            element = new ElementRowDataGateway(chemical.getId());
            chemical.delete();
            element.delete();
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Saves the element in the data source
     *
     * @param newElement
     * @throws ElementNotFoundException
     */
    public void persistElement(Element newElement) throws ElementNotFoundException {

        ChemicalRowDataGateway chemical = null;
        ElementRowDataGateway element = null;
        try {
            chemical = new ChemicalRowDataGateway(myElement.getNameBefore());
            element = new ElementRowDataGateway(chemical.getId());
            chemical.setName(newElement.getName());
            element.setAtomicNumber(newElement.getAtomicNumber());
            element.setAtomicMass(newElement.getAtomicMass());

            chemical.persist();
            element.persist();
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

    }

    /**
     * Gets all of the Elements in a given range
     *
     * @param start First atomic number in the range
     * @param end   Last atomic number in the range
     * @return
     */
    public static Element[] getElementsBetweenRange(int start, int end) throws ElementNotFoundException {
        List<Element> elements = new ArrayList<Element>();
        try {
            List<ElementDTO> elementDTOs = ElementTableDataGateway.getElementsBetweenRange(start, end);

            List<Long> ElementIDs = new ArrayList<Long>();

            for (int i = 0; i < elementDTOs.size(); i++) {
                ElementIDs.add(elementDTOs.get(i).getID());
            }

            List<ChemicalDTO> ChemicalDTOS = ChemicalTableDataGateway.getChemicals(ElementIDs);

            for (int i = 0; i < elementDTOs.size(); i++) {
                elements.add(new Element(ChemicalDTOS.get(i).getName(), (int) elementDTOs.get(i).getAtomicNumber(), elementDTOs.get(i).getAtomicMass(), new ElementMapper(ChemicalDTOS.get(i).getName())));
            }
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        Element[] elementArray = new Element[elements.size()];
        elementArray = elements.toArray(elementArray);
        return elementArray;
    }

    public static List<String> getCompounds(String name) throws Exception {
        ChemicalRowDataGateway chem = new ChemicalRowDataGateway(name);
        return MadeOfTableDataGateway.findCompounds(chem.getId());
    }
}
