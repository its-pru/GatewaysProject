package model.Controller;

import exceptions.ElementNotFoundException;
import model.Element;
import model.Mapper.ElementMapper;

import java.util.ArrayList;
import java.util.List;

public class ElementController {
    /**
     * @param name
     * @throws Exception
     */
    public static void delete(String name) throws ElementNotFoundException { //IDEA: create mapper here, add a persist method in mapper.
        ElementMapper mapper = new ElementMapper(name);
        mapper.deleteElement(name);
    }

    public static Element[] getElementsBetween(int firstAtomicNumber, int lastAtomicNumber) throws ElementNotFoundException {
        return ElementMapper.getElementsBetweenRange(firstAtomicNumber, lastAtomicNumber);
    }

    public static Element[] getAllElements() throws ElementNotFoundException {
        return ElementMapper.getAllElements();
    }

    public Element getMyElement() {
        return myElement;
    }

    private Element myElement;

    public ElementController(String name) throws ElementNotFoundException {
        ElementMapper mapper = new ElementMapper(name);
        myElement = mapper.getMyElement();
    }

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

    public void persist() throws ElementNotFoundException {
        myElement.persist();
    }

    public List<String> getCompoundsContaining() throws Exception {
        return ElementMapper.getCompounds(myElement.getName());
    }
}