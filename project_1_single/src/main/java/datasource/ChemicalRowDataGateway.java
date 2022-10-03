package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//use count for ID's?
public class ChemicalRowDataGateway {
	long id;
	String Name;
	String inHabits;
	long atomicNumber;
	double atomicMass;
	List<Long> madeOfIds = new ArrayList<>();
	long Solute;
	long dissolvedBy;
	List<Long> dissolves = new ArrayList<>();

	private static final String updateCreateString = "INSERT INTO Chemical" + " set name = ?, atomicNumber = ?, atomicMass = ?, solute = ?, dissolvedBy = ?";
	private static final String updateFinderString = "SELECT FROM Chemical WHERE id = ?";
	private static final String deleteEntry = "DELETE FROM Chemical WHERE id = ?";
	JDBC jdbc = JDBC.getJDBC();

	//Empty constructor used for JUnit tests
	public ChemicalRowDataGateway(){

	}
	//Create constructor
	public ChemicalRowDataGateway(String name, long atomicNumber, double atomicMass, long Solute, long dissolvedBy) throws SQLException {
		this.Name = name;
		this.inHabits = inHabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.madeOfIds = madeOfIds;
		this.Solute = Solute;
		this.dissolvedBy = dissolvedBy;
		this.dissolves = dissolves;
	}

	//Finder Constructor
	public ChemicalRowDataGateway(long id) throws SQLException{
		this.id = id;
		try{
			PreparedStatement findStatement = null;
			findStatement = jdbc.getConnect().prepareStatement(updateFinderString);
			findStatement.setLong(1, id);
			findStatement.execute();
			ResultSet results = findStatement.getResultSet();

		}catch (SQLException e){
			//exception for informing user there isn't an element with this id
		}
	}



	public void persist(){
		PreparedStatement updateStatement = null;
		try {
			updateStatement = jdbc.getConnect().prepareStatement(updateFinderString);
			updateStatement.setLong(1, id);
			updateStatement.setString(2, Name);
			updateStatement.setString(3, inHabits);
			updateStatement.setLong(4, atomicNumber);

			updateStatement.execute();
		} catch (SQLException e) {
			//throw new ApplicationException(e);
		}
	}


	public boolean delete(int id){
		//DELETE FROM `table_name` [WHERE condition]
		PreparedStatement deleteStatement = null;
		try{
			deleteStatement = jdbc.getConnect().prepareStatement(deleteEntry);
			deleteStatement.setLong(1,id);
			deleteStatement.execute();
			return true;
		}catch (SQLException entryNotFound){
			return false;
			//throw general exception for business logic
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

	public String getInHabits() {
		return inHabits;
	}

	public void setInHabits(String inHabits) {
		this.inHabits = inHabits;
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
