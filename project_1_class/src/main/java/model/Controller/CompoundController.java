package model.Controller;

import datasource.ChemicalRowDataGateway;
import model.Mapper.CompoundMapper;
import model.Mapper.ElementMapper;
import java.util.ArrayList;
import java.util.List;
import model.Compound;
import model.Element;

public class CompoundController {
    private Compound myCompound;

    public CompoundController(String name){
        myCompound = new Compound(name);
        //Create mapper and entry in database
    }

    public static void delete(String hCl) {

    }

    public static void createCompound(String water) {

    }

    public void addElement(String hydrogen) {
        myCompound.addElement(hydrogen);
        //Create relationship in database using CompoundMapper
    }

    public double getAtomicMass() throws Exception {
        List<String> madeOf = myCompound.getMadeOf();
        double atomicMass = 0;
        for(int i = 0; i < madeOf.size();i++){
            ElementMapper tempMapper = new ElementMapper(madeOf.get(i));
            atomicMass += tempMapper.getMyElement().getAtomicMass();
        }

        return atomicMass;
    }

    public List<String> getElements() {
        return myCompound.getMadeOf();
    }

    public void setName(String sulfuric_base) {
        myCompound.setName(sulfuric_base);
    }

    //TODO: ponder changing return type to Compound
    public ChemicalRowDataGateway getMyCompound() {
        return null;
    }
}
