//package model.Controller;
//
//import Exceptions.CompoundNotFoundException;
//import datasource.ChemicalRowDataGateway;
//import datasource.MadeOfTableDataGateway;
//import Exceptions.CompoundNotFoundException;
//import Exceptions.EntryNotFoundException;
//import model.Mapper.CompoundMapper;
//import model.Mapper.ElementMapper;
//import java.util.ArrayList;
//import java.util.List;
//import model.Compound;
//import model.Element;
//import model.Mapper.ElementNotFoundException;
//
//public class CompoundController {
//    private Compound myCompound;
//
//    public CompoundController(String name) throws CompoundNotFoundException {
//        CompoundMapper mapper = new CompoundMapper(name);
//
//        myCompound = mapper.getMyCompound();
//        //Find Mapper in DB
//    }
//
//    public static void delete(String hCl) throws CompoundNotFoundException {
//        try {
//            CompoundMapper.deleteCompound(hCl);
//        } catch (Exception e) {
//            throw new CompoundNotFoundException();
//        }
//    }
//
//    public static void createCompound(String water) throws CompoundNotFoundException {
//        CompoundMapper.createCompound(water);
//    }
//
//    public void addElement(String name) throws CompoundNotFoundException, ElementNotFoundException {
//        CompoundMapper mapper = new CompoundMapper(myCompound.getName());
//        mapper.addElement(name);
//        myCompound.addElement(name);
//    }
//
//    public double getAtomicMass() throws ElementNotFoundException {
//        List<String> madeOf = myCompound.getMadeOf();
//        double atomicMass = 0;
//        for(int i = 0; i < madeOf.size();i++){
//            ElementMapper tempMapper = new ElementMapper(madeOf.get(i));
//            atomicMass += tempMapper.getMyElement().getAtomicMass();
//        }
//
//        return atomicMass;
//    }
//
//    public List<String> getElements() throws CompoundNotFoundException {
//        CompoundMapper mapper = new CompoundMapper(myCompound.getName());
//        return mapper.getMadeOf();
//    }
//
//    public void setName(String sulfuric_base) {
//        myCompound.setName(sulfuric_base);
//    }
//
//    //TODO: ponder changing return type to Compound
//    public Compound getMyCompound() {
//        return myCompound;
//    }
//}
