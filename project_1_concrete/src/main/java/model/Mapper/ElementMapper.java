package model.Mapper;

import DTO.ElementDTO;
import datasource.CompoundRowDataGateway;
import datasource.ElementRowDataGateway;
import datasource.ElementTableDataGateway;
import datasource.MadeOfTableDataGateway;
import exceptions.ElementNotFoundException;
import model.Compound;
import model.Element;

import java.util.ArrayList;
import java.util.List;

public class ElementMapper {
    Element myElement;

    public ElementMapper(String name) throws ElementNotFoundException {
        ElementRowDataGateway ERDG;
        try {
            ERDG = new ElementRowDataGateway(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ElementNotFoundException();
        }
        myElement = new Element(ERDG.getName(), (int) ERDG.getAtomicNumber(), ERDG.getAtomicMass(), this);
    }

    public ElementMapper(String name, int atomicNumber, double atomicMass) throws ElementNotFoundException {
        myElement = new Element(name, (int) atomicNumber, atomicMass, this);
        try {
            ElementRowDataGateway ERDG = new ElementRowDataGateway(name, atomicNumber, atomicMass);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
    }

    public static Element[] getElementsBetweenRange(int start, int end) throws ElementNotFoundException {
        List<Long> range = new ArrayList<>();
        for (long j = start; j <= end; j++) {
            range.add(j);
        }
        List<ElementDTO> DTOList = null;
        try {
            DTOList = ElementTableDataGateway.getElements(start, end);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        Element[] elements = new Element[DTOList.size()];
        for (int i = 0; i < DTOList.size(); i++) {
            String name = DTOList.get(i).getName();
            double atomicMass = DTOList.get(i).getAtomicMass();
            int atomicNumber = (int) DTOList.get(i).getAtomicNumber();
            elements[i] = new Element(name, atomicNumber, atomicMass, new ElementMapper(name));
        }
        return elements;
    }

    public static Element[] getAllElements() throws ElementNotFoundException {
        List<ElementDTO> DTOList = null;
        try {
            DTOList = ElementTableDataGateway.getAllElements();
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        Element[] elements = new Element[DTOList.size()];
        for (int i = 0; i < DTOList.size(); i++) {
            String name = DTOList.get(i).getName();
            double atomicMass = DTOList.get(i).getAtomicMass();
            int atomicNumber = (int) DTOList.get(i).getAtomicNumber();
            elements[i] = new Element(name, (int) atomicNumber, atomicMass, new ElementMapper(name));
        }
        return elements;
    }

    public static List<String> getCompounds(String name) throws Exception {
        List<String> names = new ArrayList<>();
        ElementRowDataGateway element = new ElementRowDataGateway(name);
        List<Long> compoundIDs = MadeOfTableDataGateway.findCompounds(element.getID());

        for (int i = 0; i < compoundIDs.size(); i++) {
            CompoundRowDataGateway temp = new CompoundRowDataGateway(compoundIDs.get(i));
            if (!names.contains(temp.getName())){
                names.add(temp.getName());
            }

        }

        return names;
    }


    public void deleteElement(String name) throws ElementNotFoundException {
        try {
            ElementRowDataGateway.delete(name);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
    }

    public Element getMyElement() {
        return myElement;
    }

    public void persistElement(Element element) throws ElementNotFoundException {
        try {
            ElementRowDataGateway e = new ElementRowDataGateway(element.getNameBefore());
            e.setName(element.getName());
            e.setAtomicNumber(element.getAtomicNumber());
            e.setAtomicMass(element.getAtomicMass());
            e.persist();

        } catch (Exception ex) {
            throw new ElementNotFoundException();
        }
    }
}
