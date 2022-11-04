package model.Mapper;

import DTO.MadeOfDTO;
import datasource.ChemicalRowDataGateway;
import datasource.ElementRowDataGateway;
import datasource.MadeOfTableDataGateway;
import exceptions.EntryNotFoundException;
import model.Chemical;
import model.Compound;

import java.util.ArrayList;
import java.util.List;

public class CompoundMapper {
        Compound myCompound;
        long id;

    public CompoundMapper(String name) throws Exception {
        ChemicalRowDataGateway CRDG = new ChemicalRowDataGateway(name);
        this.id = CRDG.getId();
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
    public void addElement(String name) throws Exception {
        ChemicalRowDataGateway CRDG = new ChemicalRowDataGateway(name);
        long elementID = CRDG.getId();
        MadeOfTableDataGateway gateway = new MadeOfTableDataGateway();
    }

    public Compound getMyCompound(){
        return myCompound;
    }



}
