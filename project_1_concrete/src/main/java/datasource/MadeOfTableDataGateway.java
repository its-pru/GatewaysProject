package datasource;

import exceptions.UnableToConnectException;
import exceptions.UnableToCreateException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MadeOfTableDataGateway {
    private static final String getElementIDsStatement = "SELECT * FROM madeOf WHERE compoundID = ?";

    private static final String getCompoundIDsStatement = "SELECT * FROM madeOf WHERE elementID = ?";

    private static final String createNewCompoundStatement = "INSERT INTO madeOf VALUES (?,?)";

    private static final String deleteCompoundStatement = "DELETE FROM madeOf WHERE compoundID = ?";

    private static final String deleteElementStatement = "DELETE FROM madeOf WHERE elementID = ?";

    public MadeOfTableDataGateway () {}


    /**
     * Creates a new compound in the database
     * @param CompoundID - ID for the new compound
     * @param ElementIDs - ID of elements that make up new compound
     */
    public static void createNewCompound(long CompoundID, List<Long> ElementIDs){
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createNewCompoundStatement);
            int i = 0;
            while(i < ElementIDs.size()){
                stmt.setLong(1, CompoundID);
                stmt.setLong(2, ElementIDs.get(i));
                stmt.execute();
                i++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void rollback() throws UnableToConnectException{
        try{
            PreparedStatement delete = JDBC.getJDBC().getConnect().prepareStatement("DELETE FROM madeOf");
            delete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnableToConnectException("Unable to rollback database");
        }
    }

    public static void createNewCompound(long compoundID, long elementID) throws UnableToCreateException {
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(createNewCompoundStatement);
            stmt.setLong(1, compoundID);
            stmt.setLong(2, elementID);
            stmt.execute();
        } catch (SQLException e) {
            throw new UnableToCreateException("Unable to add new element to compound");
        }
    }

    /**
     * Deletes a compound from the table
     * @param compoundID - compoundID for compound being deleted
     * @throws Exception -  throws when JDBC cannot connect to the database
     */
    public static void deleteCompounds(long compoundID) throws Exception{
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteCompoundStatement);
            stmt.setLong(1, compoundID);
            stmt.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes compounds when an element makes up that compound
     * @param elementID - ElementID that is being searched for
     * @throws Exception - throws when JDBC cannot connect to Database
     */
    public static void deleteElements(long elementID) throws Exception {
        try{
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(deleteElementStatement);
            stmt.setLong(1, elementID);
            stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
            throw new UnableToConnectException("Unable to delete. Check connection and try again");
        }
    }

    /**
     * gets all the elements that make up a compound
     * @param compoundID - compoundID that is being searched for
     * @return - returns a list of ElementIDs
     * @throws Exception - throws when JDBC cannot connect to Database
     */
    public static List<Long> findElements(long compoundID) throws Exception{
        List<Long> ElementIDs = new ArrayList<Long>();

        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(getElementIDsStatement);
            stmt.setLong(1, compoundID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ElementIDs.add(rs.getLong("elementID"));
            }
        }catch (SQLException e){
            throw new UnableToConnectException("Unable to fetch results. Check connection and try again!");
        }

        return ElementIDs;
    }

    /**
     * gets all the compounds that contain a certain element
     * @param elementID - Element that is being searched for
     * @return - list of compounds
     * @throws Exception - throws when JDBC cannot connect to Databse
     */
    public static List<Long> findCompounds(long elementID) throws Exception{
        List<Long> CompoundIDs = new ArrayList<Long>();

        try {
            PreparedStatement stmt = JDBC.getJDBC().getConnect().prepareStatement(getCompoundIDsStatement);
            stmt.setLong(1, elementID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CompoundIDs.add(rs.getLong("compoundID"));
            }
        }catch (SQLException e){
            throw new UnableToConnectException("Unable to fetch results. Check connection and try again!");
        }

        return CompoundIDs;
    }

}
