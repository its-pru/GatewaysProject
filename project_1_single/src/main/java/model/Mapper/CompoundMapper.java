package model.Mapper;

import datasource.ChemicalRowDataGateway;
import datasource.MadeOfTableDataGateway;
import exceptions.CompoundNotFoundException;
import exceptions.ElementNotFoundException;
import exceptions.EntryNotFoundException;

import model.Compound;

import java.util.List;

public class CompoundMapper {
    Compound myCompound;

    /**
     * CompoundMapper constructor
     * creates compound and ChemicalRowDataGateweay objects and sets the compounds made of field using the gateway.
     * @param name
     * @throws CompoundNotFoundException
     */
    public CompoundMapper(String name) throws CompoundNotFoundException {
        ChemicalRowDataGateway compound = null;
        try {
            compound = new ChemicalRowDataGateway(name);
            myCompound = new Compound(compound.getName());
            myCompound.setMadeOf(MadeOfTableDataGateway.findElements(compound.getId()));
        } catch (EntryNotFoundException e) {
            throw new CompoundNotFoundException();
        }
    }

    /**
     * Creates compound using the ChemicalRowDataGateway object
     * and the static static method, createChemicalRowDataGateway
     * @param name
     * @throws CompoundNotFoundException
     */
    public static void createCompound(String name) throws CompoundNotFoundException {
        try {
            ChemicalRowDataGateway.createChemicalRowDataGateway(name);
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }
    }

    /**
     * deletes Compound by creating gateway object and then using its delete function.
     * @param name
     * @throws EntryNotFoundException
     */
    public static void deleteCompound(String name) throws EntryNotFoundException {
        ChemicalRowDataGateway gateway = new ChemicalRowDataGateway(name);
        gateway.delete();
    }

    /**
     * adds element to compound by using the static CreateNewCompound method from MadeOfTableDataGateway,
     * where it is adding one element to the compound using one element ID.
     * @param name
     * @throws ElementNotFoundException
     * @throws CompoundNotFoundException
     */
    public void addElement(String name) throws ElementNotFoundException, CompoundNotFoundException {
        ChemicalRowDataGateway element = null;
        try {
            element = new ChemicalRowDataGateway(name);
        } catch (Exception e) {
            throw new ElementNotFoundException();
        }
        ChemicalRowDataGateway compound = null;
        try {
            compound = new ChemicalRowDataGateway(myCompound.getName());
            MadeOfTableDataGateway.CreateNewCompound(compound.getId(), element.getId());

        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }

    }

    public Compound getMyCompound(){
        return myCompound;
    }

    public List<String> getMadeOf() throws CompoundNotFoundException {
        try {
            return MadeOfTableDataGateway.findElements(new ChemicalRowDataGateway(myCompound.getName()).getId());
        } catch (EntryNotFoundException e) {
            throw new CompoundNotFoundException();
        }
    }



}
