package model.Mapper;

import DTO.ElementDTO;
import datasource.CompoundRowDataGateway;
import datasource.ElementRowDataGateway;
import datasource.ElementTableDataGateway;
import datasource.MadeOfTableDataGateway;
import exceptions.CompoundNotFoundException;

import exceptions.ElementNotFoundException;
import model.Compound;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompoundMapper {
    Compound myCompound;

    public CompoundMapper(String name) throws CompoundNotFoundException {
        CompoundRowDataGateway compound;
        try {
            compound = new CompoundRowDataGateway(name);
            myCompound = new Compound(compound.getName());

            myCompound.setMadeOf(getMadeOf());
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }
    }

    public static void createCompound(String name) throws CompoundNotFoundException {
        try {
            CompoundRowDataGateway.createCompound(name);
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }
    }

    public static void deleteCompound(String name) throws Exception {
        try {
            CompoundRowDataGateway gateway = new CompoundRowDataGateway(name);
            gateway.delete(name);
        } catch (SQLException e) {
            throw new CompoundNotFoundException();
        }
    }

    public void addElement(String name) throws ElementNotFoundException, CompoundNotFoundException {
        ElementRowDataGateway ERDG;
        CompoundRowDataGateway CRDG;

        try {
            CRDG = new CompoundRowDataGateway(myCompound.getNameBefore());

        } catch (Exception e) {
            throw new ElementNotFoundException();
        }

        try {
            ERDG = new ElementRowDataGateway(name);
            MadeOfTableDataGateway.createNewCompound(CRDG.getID(), ERDG.getID());
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        myCompound.setMadeOf(this.getMadeOf());
    }

    public Compound getMyCompound() {
        return myCompound;
    }

    public List<String> getMadeOf() throws CompoundNotFoundException {
        try {
            CompoundRowDataGateway compound = new CompoundRowDataGateway(myCompound.getNameBefore());
            List<Long> elementIDs = MadeOfTableDataGateway.findElements(compound.getID());
            List<ElementDTO> elements = ElementTableDataGateway.getAllElements();
            List<String> elementNames = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                ElementDTO temp = elements.get(i);
                if (elementIDs.contains(temp.getID())) {
                        elementNames.add(temp.getName());
                }
            }
            return elementNames;
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }

    }

}
