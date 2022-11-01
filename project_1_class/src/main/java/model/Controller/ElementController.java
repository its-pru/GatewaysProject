package model.Controller;

import model.Element;
import model.Mapper.ElementMapper;
import model.Mapper.ElementNotFoundException;

public class ElementController {
    /**
     *
     * @param name
     * @throws
     * I think this is how deleting an element would look
     */
    public static void delete(String name) throws Exception { //IDEA: create mapper here, add a persist method in mapper.
        ElementMapper mapper = new ElementMapper(name);
        mapper.deleteElement(name);
    }

    public static Element[] getElementsBetween(int firstAtomicNumber, int lastAtomicNumber) {
        return null;
    }

    public static Element[] getAllElements() {
        return null;
    }

    public Element getMyElement() {
        return myElement;
    }

    private Element myElement;
    public ElementController(String name) throws Exception {
        ElementMapper mapper = new ElementMapper(name);
        myElement = mapper.getMyElement();

    }

    public ElementController(String name, int atomicNumber, double atomicMass) throws Exception {
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

    public void persist() {
    }
}
