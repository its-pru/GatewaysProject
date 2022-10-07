package datasource;

import exceptions.UnableToConnectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MadeOfTableDataGateway {
    private static final String getElementIDsStatement = "SELECT * FROM madeOf WHERE elementID = ?";

    public MadeOfTableDataGateway () {}

    public List<Long> getElementIDs(int compoundID) throws Exception{
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


}
