package model.Controller;

import exceptions.CompoundNotFoundException;

import exceptions.ElementNotFoundException;
import model.Mapper.CompoundMapper;
import model.Mapper.ElementMapper;

import java.util.List;
import model.Compound;

public class CompoundController {
    private Compound myCompound;

    /**
     *Constructor for Compound Controller. Creates mapper object sets compound model object variables.
     * @param name
     * @throws CompoundNotFoundException
     */
    public CompoundController(String name) throws CompoundNotFoundException {
        CompoundMapper mapper = new CompoundMapper(name);

        myCompound = mapper.getMyCompound();
    }

    /**
     * deletes Compound from database by calling the mappers deleteCompound.
     * @param hCl
     * @throws CompoundNotFoundException
     */
    public static void delete(String hCl) throws CompoundNotFoundException {
        try {
            CompoundMapper.deleteCompound(hCl);
        } catch (Exception e) {
            throw new CompoundNotFoundException();
        }
    }

    /**
     * creates Compound in database by calling createCompound with the mapper object.
     * @param water
     * @throws CompoundNotFoundException
     */
    public static void createCompound(String water) throws CompoundNotFoundException {
        CompoundMapper.createCompound(water);
    }

    /**
     * adds element to compound by creating mapper and calling its addElement() method.
     * @param name
     * @throws CompoundNotFoundException
     * @throws ElementNotFoundException
     */
    public void addElement(String name) throws CompoundNotFoundException, ElementNotFoundException {
        CompoundMapper mapper = new CompoundMapper(myCompound.getName());
        mapper.addElement(name);
        myCompound.addElement(name);
    }

    /**
     * gets atomic mass of whole compound
     * @return atomic mass of whole compound
     * @throws ElementNotFoundException
     */
    public double getAtomicMass() throws ElementNotFoundException {
        List<String> madeOf = myCompound.getMadeOf();
        double atomicMass = 0;
        for(int i = 0; i < madeOf.size();i++){
            ElementMapper tempMapper = new ElementMapper(madeOf.get(i));
            atomicMass += tempMapper.getMyElement().getAtomicMass();
        }

        return atomicMass;
    }

    /**
     * getting the names of the elements that make up the compound.
     * @return list of strings of elements that make up a compound.
     * @throws CompoundNotFoundException
     */
    public List<String> getElements() throws CompoundNotFoundException {
        CompoundMapper mapper = new CompoundMapper(myCompound.getName());
        return mapper.getMadeOf();
    }

    public void setName(String sulfuric_base) {
        myCompound.setName(sulfuric_base);
    }

    public Compound getMyCompound() {
        return myCompound;
    }
}
