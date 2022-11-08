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
    public CompoundMapper(String name) throws CompoundNotFoundException{
        CompoundRowDataGateway compound;
        try{
            compound = new CompoundRowDataGateway(name);
            myCompound = new Compound(compound.getName());
            List<Long> elementIDs = MadeOfTableDataGateway.findElements(compound.getID());
            List<ElementDTO> elements = ElementTableDataGateway.getElements(elementIDs);
            List<String> elementNames = new ArrayList<String>();
            for(int i = 0; i < elements.size(); i++){
                elementNames.add(elements.get(i).getName());
            }
            myCompound.setMadeOf(elementNames);
        }catch (Exception e){
            throw new CompoundNotFoundException();
        }
    }

    public static void createCompound(String name) throws CompoundNotFoundException {
        try {
            CompoundRowDataGateway.createCompound(name);
        }catch (Exception e){
            throw new CompoundNotFoundException();
        }
    }

    public static void deleteCompound(String name) throws Exception{
        try {
            CompoundRowDataGateway gateway = new CompoundRowDataGateway(name);
            gateway.delete(name);
        }catch (SQLException e){
            throw new CompoundNotFoundException();
        }
    }

    public void addElement(String name) throws Exception{
        ElementRowDataGateway ERDG;
        CompoundRowDataGateway CRDG;

        try{
            CRDG = new CompoundRowDataGateway(myCompound.getName());

        }catch (Exception e){
            throw new CompoundNotFoundException();
        }

        try{
            ERDG = new ElementRowDataGateway(name);
            MadeOfTableDataGateway.createNewCompound(CRDG.getID(), ERDG.getID());
        }catch(Exception e){
            throw new ElementNotFoundException();
        }
    }

    public Compound getMyCompound(){
        return myCompound;
    }

    public List<String> getMadeOf() throws Exception{
        List<String> elementNames;
        try{
            List<Long> elementIDs = MadeOfTableDataGateway.findElements(new CompoundRowDataGateway(myCompound.getName()).getID());
            List<ElementDTO> dtos = ElementTableDataGateway.getElements(elementIDs);
            elementNames = new ArrayList<String>();
            for(int i = 0; i < dtos.size(); i++){
                elementNames.add(dtos.get(i).getName());
            }
        }catch (Exception e){
            throw new CompoundNotFoundException();
        }
        return elementNames;
    }

}
