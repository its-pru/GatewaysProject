package datasource;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChemicalTableDataGateway {
	//Find every element that makes up this compound
	//Compound: CO2: Key for CO2 -> madeOfTable(CompoundID); madeOfTable(ElementID) -> elements in Data table

	//Method for getting a list of ID's of elements that make up a compound
	//get all rows from table

	//Take in a list of IDs of elements (gotten from madeof); select all from table where the id matches; return a list of elements
}
