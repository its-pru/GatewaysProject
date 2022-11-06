package model.Mapper;

import DTO.ElementDTO;
import datasource.ElementRowDataGateway;
import datasource.ElementTableDataGateway;
import exceptions.ElementNotFoundException;
import model.Element;

import java.util.List;

public class ElementMapper {
    Element myElement;
    public ElementMapper(String name) throws ElementNotFoundException {
        ElementRowDataGateway ERDG = null;
        try {
            ERDG = new ElementRowDataGateway(name);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        myElement = new Element(ERDG.getName(), ERDG.getAtomicMass(), (int) ERDG.getAtomicNumber(), this);
    }

    public ElementMapper(String name, int atomicNumber, double atomicMass) throws Exception {
        myElement =  new Element(name, atomicMass, atomicNumber, this);
        ElementRowDataGateway ERDG = new ElementRowDataGateway(name, atomicNumber, atomicMass);
    }

    public static Element[] getElementsBetweenRange(int start, int end){

        return new Element[0];
    }

    public static Element[] getAllElements() throws Exception {
        List<ElementDTO> DTOList = ElementTableDataGateway.getAllElements();
        Element[] elements = new Element[DTOList.size()];
        for(int i = 0; i < DTOList.size(); i++){
            String name = DTOList.get(i).getName();
            double atomicMass = DTOList.get(i).getAtomicMass();
            int atomicNumber = (int)DTOList.get(i).getAtomicNumber();
            elements[i] = new Element(name, atomicMass, atomicNumber, new ElementMapper(name));
        }
        return elements;
    }

    public void deleteElement(String name) throws Exception {
        ElementRowDataGateway.delete(name);
    }

    public Element getMyElement(){return myElement;}

    public void persistElement(Element element) throws Exception {
        ElementRowDataGateway e = new ElementRowDataGateway(myElement.getNameBefore());
        e.setName(element.getName());
        e.setAtomicNumber(element.getAtomicNumber());
        e.setAtomicMass(element.getAtomicMass());
        e.persist();
    }
}
