package model.Controller;

import model.Element;
import model.Mapper.ElementMapper;
import model.Mapper.ElementNotFoundException;

public class ElementController {
    public static void delete(String name) {
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
    public ElementController(String name) throws ElementNotFoundException {
        ElementMapper mapper = new ElementMapper(name);
        myElement = mapper.getMyElement();

    }

    public ElementController(String name, int atomicNumber, double atomicMass) throws Exception {
        ElementMapper mapper = new ElementMapper(name, atomicNumber, atomicMass);
        myElement = mapper.getMyElement();
    }

    public void setAtomicNumber(int newAtomicNumber) {
    }

    public void setAtomicMass(double newAtomicMass) {
    }

    public void setName(String newName) {
    }

    public void persist() {
    }
}
