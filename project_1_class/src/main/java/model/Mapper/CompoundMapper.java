package model.Mapper;

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
        ChemicalRowDataGateway compound = new ChemicalRowDataGateway(name);
        myCompound = new Compound(compound.getName());
        myCompound.setMadeOf(MadeOfTableDataGateway.findElements(compound.getId()));
    }

    public static void createCompound(String name) throws Exception {
        ChemicalRowDataGateway.createChemicalRowDataGateway(name);
    }

    public static void deleteCompound(String name) throws EntryNotFoundException {
        ChemicalRowDataGateway gateway = new ChemicalRowDataGateway(name);
        gateway.delete();
    }
    public void addElement(String name) throws Exception { //todo: this don't work yet either
        ChemicalRowDataGateway CRDG = new ChemicalRowDataGateway(name);
        long elementID = CRDG.getId();
        MadeOfTableDataGateway gateway = new MadeOfTableDataGateway();
    }

    public Compound getMyCompound(){
        return myCompound;
    }



}
