package model.Mapper;

import DTO.MadeOfDTO;
import datasource.ChemicalRowDataGateway;
import datasource.MadeOfTableDataGateway;
import exceptions.EntryNotFoundException;
import model.Compound;

public class CompoundMapper {
        Compound myCompound;
    public CompoundMapper(String name) throws Exception {
        ChemicalRowDataGateway CRDG = new ChemicalRowDataGateway(name);
        myCompound = new Compound(CRDG.getName());
        MadeOfDTO DTO = MadeOfTableDataGateway.findElements(CRDG.getId());
        DTO.getListOfElementIDs();
    }

    public static void createCompound(String water) throws Exception {
        ChemicalRowDataGateway.createChemicalRowDataGateway(water);
    }

    public static void deleteCompound(String name) throws EntryNotFoundException {
        ChemicalRowDataGateway gateway = new ChemicalRowDataGateway(name);
        gateway.delete();
    }
    public void addElement(String name){

    }

    public Compound getMyCompound(){
        return myCompound;
    }



}
