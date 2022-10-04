package datasource;

import Exceptions.EntryNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class MadeOfTableDataGateway {
    //Take ID of compound from Chemical, get all ids of elements that make up this compound; return as list
    //Multiple entries for each compound, each containing a different element ID. (Many-to-many)
    JDBC jdbc = JDBC.getJDBC();
    List<Long> elementIDs = new ArrayList<Long>();
    long compoundID = 0;

    private static final String getElementIDs = "SELECT * FROM madeOf WHERE compoundID = ?";
    private static final String getCompoundIDs = "SELECT * FROM madeOf WHERE elementID = ?";
    private static final String createNewCompound = "INSERT INTO madeOf VALUES ( ?, ?)";

    /**
     * constructor for gateway
     */
    public MadeOfTableDataGateway() {}

    /**
     * finds all of the elements that make up a compound
     * @param CompoundID - id being searched for
     * @throws Exception - throws when compound isnt found
     */
    public void findElements(long CompoundID) throws Exception{
        //select all of the elementIDs that contain compoundID ID
        try {
            PreparedStatement stmt = jdbc.getConnect().prepareStatement(getElementIDs);
            stmt.setLong(1, CompoundID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                elementIDs.add(rs.getLong("ID"));
            }

        }catch (SQLException e){
            throw new EntryNotFoundException("This Compound could not be found");
        }
    }

    /**
     * Finds all of the compounds that contain an element
     * @param elementID - id of element
     * @return - boolean if this is successful or not
     * @throws Exception - throws when element isnt found
     */
    public boolean findCompoundsWithOneElement(long elementID) throws Exception{
        //select all of the elementIDs that contain compoundID ID
        try {
            PreparedStatement stmt = jdbc.getConnect().prepareStatement(getCompoundIDs);
            stmt.setLong(1, elementID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            compoundID = rs.getLong("compoundID");

        }catch (SQLException e){
            e.printStackTrace();
            throw new EntryNotFoundException("This Element could not be found");
        }

        return true;
    }

    /**
     * Creates a new compound
     * @param CompoundID - id of compound in Chemical table
     * @param ElementIDs - list of elements making up this compound
     * @throws Exception - throws when breaks foreign key rules
     */
    public void CreateNewCompound(long CompoundID, List<Long> ElementIDs) throws Exception{
        //creates a new compound entry with its elements
        try {
            for (int i = 0; i < ElementIDs.size(); i++) {
                PreparedStatement stmt = jdbc.getConnect().prepareStatement(createNewCompound);
                stmt.setLong(1, CompoundID);
                stmt.setLong(2, ElementIDs.get(i));
                stmt.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes an element based on a certain compound
     * @param id - id of compound
     * @return - boolean whether successful or not
     * @throws Exception - Throws when entry could not be found
     */
    public boolean deleteElements(long id) throws Exception{
        //delete all elements in a certain compound
        //DELETE
        Statement deleteStatement = null;
        try{
            deleteStatement = jdbc.getConnect().createStatement();
            String deleteString = "DELETE FROM madeOf WHERE elementID = " + Long.toString(id);
            deleteStatement.execute(deleteString);

        }catch (SQLException entryNotFound) {
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    /**
     * Deletes an element from a compound
     * @param id - id of element being removed
     * @return - boolean whether successful or not
     * @throws Exception - throws when entry for element isn't found
     */
    public boolean deleteCompounds(long id) throws Exception {
        Statement deleteStatement = null;
        try{
            deleteStatement = jdbc.getConnect().createStatement();
            String deleteString = "DELETE FROM madeOf WHERE compoundID = " + Long.toString(id);
            deleteStatement.execute(deleteString);

        }catch (SQLException entryNotFound){
            throw new EntryNotFoundException("Could not find entry with this id");
        }
        return true;
    }

    /**
     * gets the compound id this gateway is storing
     * @return - compound id
     */
    public long getCompoundID(){
        return compoundID;
    }


}
