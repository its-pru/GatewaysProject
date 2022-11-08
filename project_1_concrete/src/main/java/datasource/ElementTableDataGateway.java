package datasource;

import DTO.ElementDTO;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.UnableToConnectException;

public class ElementTableDataGateway {
    /**
     * Selects a group of chemicals based on ids
     *
     * @param start - start of range
     * @param end - end of range
     */
    public static List<ElementDTO> getElements(int start, int end) throws Exception {
        JDBC jdbc = JDBC.getJDBC();
        List<ElementDTO> ElementList = new ArrayList<>();
        try {
            PreparedStatement stmt = jdbc.getConnect().prepareStatement("SELECT * FROM element WHERE atomicNumber BETWEEN ? AND ? ");
            stmt.setInt(1, start);
            stmt.setInt(2, end);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("name");
                long atomicNumber = rs.getLong("atomicNumber");
                double atomicMass = rs.getDouble("atomicMass");
                ElementList.add(new ElementDTO(id, name, atomicNumber, atomicMass));
            }
        }catch(SQLException e){
            throw new UnableToConnectException("Unable to connect. Check connection and try again");
        }
        return ElementList;
    }

    public static List<ElementDTO> getAllElements() throws Exception{
        JDBC jdbc = JDBC.getJDBC();
        List<ElementDTO> ElementList = new ArrayList<ElementDTO>();
        PreparedStatement stmt = jdbc.getConnect().prepareStatement("SELECT * FROM element");
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            long id = rs.getLong("ID");
            String name = rs.getString("name");
            long atomicNumber = rs.getLong("atomicNumber");
            double atomicMass = rs.getDouble("atomicMass");
            ElementList.add(new ElementDTO(id, name, atomicNumber, atomicMass));
        }

        return ElementList;
    }

    public static void rollback() throws UnableToConnectException{
        try{
            PreparedStatement delete = JDBC.getJDBC().getConnect().prepareStatement("DELETE FROM element");
            delete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnableToConnectException("Unable to rollback database");
        }
    }
    /**
     * builds a sql statement based on number of ids
     *
     * @param numIDs    - number of ids
     * @param beginning - beginning of string before formatting
     * @return
     */
    public static String queryBuilder(int numIDs, String beginning) {

        StringBuilder sb = new StringBuilder(beginning);

        for (int i = 0; i < numIDs; i++) {
            sb.append(" ?");
            if (i != (numIDs - 1)) {
                sb.append(",");
            }
        }
        sb.append(")");

        return sb.toString();
    }

}
