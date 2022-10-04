package datasource;

import Exceptions.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//use count for ID's?
public class ChemicalRowDataGateway {
	long id;
	String Name;
	long atomicNumber;
	double atomicMass;
	List<Long> madeOfIds = new ArrayList<>();
	long Solute;
	long dissolvedBy;
	List<Long> dissolves = new ArrayList<>();

	private static final String updateCreateString = "INSERT INTO Chemical" + " set name = ?, atomicNumber = ?, atomicMass = ?, solute = ?, dissolvedBy = ?";
	private static final String updateFinderString = "SELECT * FROM Chemical WHERE id = ?";
	private static final String entryUpdateString = "UPDATE Chemical SET name = ?, atomicNumber = ?, atomicMass = ?, solute = ?, dissolvedBy = ? WHERE ID = ?";
	private static final String existsString = "SELECT * FROM Chemical WHERE name = ? OR atomicNumber = ? OR atomicMass = ?";

	JDBC jdbc = JDBC.getJDBC();


	//Create constructor
	public ChemicalRowDataGateway(String name, long atomicNumber, double atomicMass, long Solute, long dissolvedBy) throws Exception {
		this.Name = name;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.Solute = Solute;
		this.dissolvedBy = dissolvedBy;

		if(exists(name, atomicNumber, atomicMass, Solute, dissolvedBy)){
			throw new AlreadyExistsException("Sorry. This already exists. Sounds like a skill issue to me");
		}else {
			try {
				PreparedStatement insertStatement = null;
				insertStatement = jdbc.getConnect().prepareStatement(updateCreateString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, name);
				insertStatement.setLong(2, atomicNumber);
				insertStatement.setDouble(3, atomicMass);
				insertStatement.setLong(4, dissolvedBy);
				insertStatement.setLong(5, Solute);
				insertStatement.execute();

				ResultSet rs = insertStatement.getGeneratedKeys();
				rs.first();
				this.id = rs.getLong(1);

			} catch (SQLException UnableToCreateException) {
				UnableToCreateException.printStackTrace();
				throw new UnableToCreateException("This entry could not be added.");
			}
		}
	}

	//Finder Constructor


	public ChemicalRowDataGateway(long id) throws Exception{
		try{
			PreparedStatement findStatement = null;
			findStatement = jdbc.getConnect().prepareStatement(updateFinderString, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			findStatement.setLong(1, id);
			findStatement.execute();
			ResultSet results = findStatement.getResultSet();

			results.first();

			this.id = results.getLong("ID");
			this.Name = results.getString("name");
			this.atomicNumber = results.getLong("atomicNumber");
			this.atomicMass = results.getDouble("atomicMass");
			this.Solute = results.getLong("solute");
			this.dissolvedBy = results.getLong("dissolvedBy");
		}catch (SQLException e){
				e.printStackTrace();
			//throw new EntryNotFoundException("Could not find an entry for this ID");
		}
	}


	public void persist() throws Exception{
		PreparedStatement updateStatement = null;
		try {
			updateStatement = jdbc.getConnect().prepareStatement(entryUpdateString);
			updateStatement.setString(1, Name);
			updateStatement.setLong(2, atomicNumber);
			updateStatement.setDouble(3, atomicMass);
			updateStatement.setLong(4, Solute);
			updateStatement.setLong(5, dissolvedBy);
			updateStatement.setLong(6, id);

			updateStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UnableToUpdateException("Unable to update entry!");
		}
	}


	public boolean delete(long id) throws Exception{
		//DELETE FROM Chemical where id =?
		Statement deleteStatement = null;
		try{
			deleteStatement = jdbc.getConnect().createStatement();
			String deleteString = "DELETE FROM Chemical WHERE id = " + Long.toString(id);
			deleteStatement.execute(deleteString);

		}catch (SQLException entryNotFound){
			throw new EntryNotFoundException("Could not find entry with this id");
		}
		return true;
	}

	//ponder idea of return ID instead of Boolean
	public boolean exists (String name, long atomicNumber, double atomicMass, long Solute, long dissolvedBy) throws Exception{
		//select * from table where condition=value
		// SELECT EXISTS(SELECT * FROM table1 WHERE ...)
		PreparedStatement exists = null;
		try{
			exists = jdbc.getConnect().prepareStatement(existsString);
			exists.setString(1, name);
			exists.setLong(2, atomicNumber);
			exists.setDouble(3, atomicMass);
			exists.execute();
			ResultSet rs = exists.getResultSet();
			return rs.isBeforeFirst(); // returns false if the result set contains zero rows

		} catch (SQLException throwables) {
			throw new UnableToConnectException("Unable to check if value exists. Check network connection and try again");
		}

	}

	public boolean find(String name){
		return false;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(long atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	public List<Long> getIds() {
		return madeOfIds;
	}

	public void setMadeOfIds(List<Long> ids) {
		this.madeOfIds = ids;
	}

	public long getSolute() {
		return Solute;
	}

	public void setSolute(long solute) {
		Solute = solute;
	}

	public long getDissolvedBy() {
		return dissolvedBy;
	}

	public void setDissolvedBy(long dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	public List<Long> getDissolves() {
		return dissolves;
	}

	public void setDissolves(List<Long> dissolves) {
		this.dissolves = dissolves;
	}


}
