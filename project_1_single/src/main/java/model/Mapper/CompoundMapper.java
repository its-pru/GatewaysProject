//package model.Mapper;
//
//import datasource.ChemicalRowDataGateway;
//import datasource.ElementRowDataGateway;
//import datasource.MadeOfTableDataGateway;
//import exceptions.CompoundNotFoundException;
//import exceptions.EntryNotFoundException;
//import model.Chemical;
//import model.Compound;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CompoundMapper {
//        Compound myCompound;
//
//    public CompoundMapper(String name) throws CompoundNotFoundException {
//        ChemicalRowDataGateway compound = null;
//        try {
//            compound = new ChemicalRowDataGateway(name);
//            myCompound = new Compound(compound.getName());
//            myCompound.setMadeOf(MadeOfTableDataGateway.findElements(compound.getId()));
//        } catch (EntryNotFoundException e) {
//            throw new CompoundNotFoundException();
//        }
//    }
//
//    public static void createCompound(String name) throws CompoundNotFoundException {
//        try {
//            ChemicalRowDataGateway.createChemicalRowDataGateway(name);
//        } catch (Exception e) {
//            throw new CompoundNotFoundException();
//        }
//    }
//
//    public static void deleteCompound(String name) throws EntryNotFoundException, SQLException {
//        ChemicalRowDataGateway gateway = new ChemicalRowDataGateway(name);
//        gateway.delete();
//    }
//    public void addElement(String name) throws ElementNotFoundException, CompoundNotFoundException {
//        ChemicalRowDataGateway element = null;
//        try {
//            element = new ChemicalRowDataGateway(name);
//        } catch (EntryNotFoundException e) {
//            throw new ElementNotFoundException();
//        }
//        ChemicalRowDataGateway compound = null;
//        try {
//            compound = new ChemicalRowDataGateway(myCompound.getName());
//            MadeOfTableDataGateway.CreateNewCompound(compound.getId(), element.getId());
//
//        } catch (Exception e) {
//            throw new CompoundNotFoundException();
//        }
//
//    }
//
//    public Compound getMyCompound(){
//        return myCompound;
//    }
//
//    public List<String> getMadeOf() throws CompoundNotFoundException {
//        try {
//            return MadeOfTableDataGateway.findElements(new ChemicalRowDataGateway(myCompound.getName()).getId());
//        } catch (EntryNotFoundException e) {
//            throw new CompoundNotFoundException();
//        }
//    }
//
//
//
//}
