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

    /**
     * Constructor for CompoundMapper to find myCompound by name
     * @param name
     * @throws CompoundNotFoundException
     */
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

    /**
     * Create compound bu name using RowDataGateway
     * @param name
     * @throws CompoundNotFoundException
     */
    public static void createCompound(String name) throws CompoundNotFoundException {
        try {
            CompoundRowDataGateway.createCompound(name);
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }
    }

    /**
     * Delete compound by name using RowDataGateway
     * @param name
     * @throws Exception
     */
    public static void deleteCompound(String name) throws Exception {
        try {
            CompoundRowDataGateway gateway = new CompoundRowDataGateway(name);
            gateway.delete(name);
        } catch (SQLException e) {
            throw new CompoundNotFoundException();
        }
    }

    /**
     * Add element by name using RowDataGateways
     * @param name
     * @throws ElementNotFoundException
     * @throws CompoundNotFoundException
     */
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
            for (int i = 0; i < elementIDs.size(); i++) {
                long temp = elementIDs.get(i);
                for (int j = 0; j < elements.size(); j++) {
                    if (elements.get(j).getID() == temp) {
                        elementNames.add(elements.get(j).getName());
                    }
                }
            }
            return elementNames;
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }

    }

}
