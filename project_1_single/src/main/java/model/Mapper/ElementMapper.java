package model.Mapper;

import DTO.ChemicalDTO;
import exceptions.AlreadyExistsException;
import exceptions.EntryNotFoundException;
import exceptions.UnableToConnectException;
import datasource.*;
//import exceptions.EntryNotFoundException;
//import exceptions.UnableToConnectException;
import model.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementMapper {
    public static Element[] getAllElements() throws ElementNotFoundException {
        List<ChemicalDTO> chemicals = null;
        try {
            chemicals = ChemicalTableDataGateway.getAllChemicals();
        } catch (UnableToConnectException e) {
            throw new ElementNotFoundException();
        }
        List<Element> temp = new ArrayList<Element>();

        for (int i = 0; i < chemicals.size(); i++) {
            ChemicalDTO element = chemicals.get(i);
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
     * Create a new element in the database, and store the resulting model object
     * into my instance variable
     */
    public ElementMapper(String name, int atomicNumber, double atomicMass) throws ElementNotFoundException {
        try {
            ChemicalRowDataGateway chemical = new ChemicalRowDataGateway(name, atomicNumber, atomicMass, 0, 0, Type.ELEMENT);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

        myElement = new Element(name, atomicNumber, atomicMass, this);
    }

    /**
     * Constructor for objects that exist in the db
     *
     * @param name
     */
    public ElementMapper(String name) throws ElementNotFoundException {
        ChemicalRowDataGateway chemical = null;
        try {
            chemical = new ChemicalRowDataGateway(name);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

        myElement = new Element(name, (int) chemical.getAtomicNumber(), chemical.getAtomicMass(), this);
    }

    public Element getMyElement() {
        return myElement;
    }

    /**
     * @param name
     * @throws Exception I think this is how deleting an Element would look.
     */
    public void deleteElement(String name) throws ElementNotFoundException {
        ChemicalRowDataGateway chemical = null;
        try {
            chemical = new ChemicalRowDataGateway(name);
            chemical.delete();
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
    }

    public void persistElement(Element newElement) throws ElementNotFoundException {

        ChemicalRowDataGateway chemical = null;
        try {
            chemical = new ChemicalRowDataGateway(myElement.getNameBefore());
            chemical.setName(newElement.getName());
            chemical.setAtomicNumber(newElement.getAtomicNumber());
            chemical.setAtomicMass(newElement.getAtomicMass());

            chemical.persist();
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
            List<ChemicalDTO> elementDTOs = ChemicalTableDataGateway.getElementsBetweenRange(start, end);

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
