package model.Controller;

import exceptions.ElementNotFoundException;
import model.Element;
import model.Mapper.ElementMapper;

import java.util.ArrayList;
import java.util.List;

public class ElementController {

    /**
     * Deletes the element from the data source
     * @param name name of the element
     * @throws ElementNotFoundException
     */
    public static void delete(String name) throws ElementNotFoundException {
        ElementMapper mapper = new ElementMapper(name);
        mapper.deleteElement(name);
    }

    /**
     * Grabs elements from the data source within a range
     * @param firstAtomicNumber start of the range
     * @param lastAtomicNumber end of the range
     * @return list of element objects
     * @throws ElementNotFoundException if the list cannot be obtained
     */
    public static Element[] getElementsBetween(int firstAtomicNumber, int lastAtomicNumber) throws ElementNotFoundException {
        return ElementMapper.getElementsBetweenRange(firstAtomicNumber, lastAtomicNumber);
    }

    /**
     * Grabs all elements
     * @return list of element objects
     * @throws ElementNotFoundException if the list cannot be obtained
     */
    public static Element[] getAllElements() throws ElementNotFoundException {
        return ElementMapper.getAllElements();
    }

    /**
     * Gets current element object
     * @return element
     */
    public Element getMyElement() {
        return myElement;
    }

    private Element myElement;

    /**
     * Constructor for already created elements
     * @param name name of element
     * @throws ElementNotFoundException if the element cannot be found
     */
    public ElementController(String name) throws ElementNotFoundException {
        ElementMapper mapper = new ElementMapper(name);
        myElement = mapper.getMyElement();
    }

    /**
     * Create constructor for Elements
     * @param name name of the element
     * @param atomicNumber atomic number of the element
     * @param atomicMass atomic mass of the element
     * @throws ElementNotFoundException if it cannot be created
     */
    public ElementController(String name, int atomicNumber, double atomicMass) throws ElementNotFoundException {
        ElementMapper mapper = new ElementMapper(name, atomicNumber, atomicMass);
        myElement = mapper.getMyElement();
    }

    public void setAtomicNumber(int newAtomicNumber) {
        myElement.setAtomicNumber(newAtomicNumber);
    }

    public void setAtomicMass(double newAtomicMass) {
        myElement.setAtomicMass(newAtomicMass);
    }

    public void setName(String newName) {
        myElement.setName(newName);
    }

    /**
     * Save changes into the 
     * @throws ElementNotFoundException
     */
    public void persist() throws ElementNotFoundException {
        myElement.persist();
    }

    public List<String> getCompoundsContaining() throws Exception {
        return ElementMapper.getCompounds(myElement.getName());
    }
}