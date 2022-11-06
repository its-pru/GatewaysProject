package model.Controller;

import datasource.ChemicalRowDataGateway;
import exceptions.EntryNotFoundException;
import model.Mapper.CompoundMapper;
import model.Mapper.ElementMapper;
import java.util.ArrayList;
import java.util.List;
import model.Compound;
import model.Element;

public class CompoundController {
    private Compound myCompound;

    public CompoundController(String name) throws Exception {
        CompoundMapper mapper = new CompoundMapper(name);
        myCompound = mapper.getMyCompound();
        //Find Mapper in DB
    }

    public static void delete(String hCl) throws EntryNotFoundException {
        CompoundMapper.deleteCompound(hCl);
    }

    public static void createCompound(String water) throws Exception {
        CompoundMapper.createCompound(water);
    }

    public void addElement(String hydrogen) throws Exception { //todo: this don't work yet
        CompoundMapper mapper = new CompoundMapper(myCompound.getName());
        myCompound.addElement(hydrogen);
        mapper.addElement(hydrogen);

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
    public Compound getMyCompound() {
        return myCompound;
    }
}
