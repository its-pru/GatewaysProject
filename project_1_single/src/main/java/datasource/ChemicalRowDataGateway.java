package datasource;

import java.sql.PreparedStatement;
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

	private static final String updateCreateString = "UPDATE chemical " + "  set name = ?, inHabits = ?, atomicNumber = ?, atomicMass = ?, madeOfIds = ?, solute = ?, dissolvedBy = ?, dissolves = ?";
	private static final String updateFinderString = "UPDATE chemical " + " set id = ?, name = ?, inHabits = ?, atomicNumber = ?, atomicMass = ?, madeOfIds = ?, solute = ?, dissolvedBy = ?, dissolves = ?";

	JDBC jdbc = JDBC.getJDBC();

	//Empty constructor used for JUnit tests
	public ChemicalRowDataGateway(){

	}
	public ChemicalRowDataGateway(String name, String inHabits, long atomicNumber, double atomicMass, List<Long> madeOfIds, long solute, long dissolvedBy, List<Long> dissolves){
		this.Name = name;
		this.inHabits = inHabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.madeOfIds = madeOfIds;
		this.Solute = Solute;
		this.dissolvedBy = dissolvedBy;
		this.dissolves = dissolves;
	}

	public ChemicalRowDataGateway(long id, String Name, String inHabits, long atomicNumber, double atomicMass, List<Long> madeOfIds, long solute, long dissolvedBy, List<Long> dissolves) {
		this.id = id;
		this.Name = Name;
		this.inHabits = inHabits;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.madeOfIds = madeOfIds;
		this.Solute = Solute;
		this.dissolvedBy = dissolvedBy;
		this.dissolves = dissolves;
	}

//	public void persist(){
//		PreparedStatement updateStatement = null;
//		try {
//			updateStatement = DB.prepare(updateFinderString);
//			updateStatement.setLong(1, id);
//			updateStatement.setString(2, Name);
//			updateStatement.setString(3, inHabits);
//			updateStatement.setLong(4, atomicNumber);
//
//			updateStatement.execute();
//		} catch (Exception e) {
//			throw new ApplicationException(e);
//		} finally {DB.cleanUp(updateStatement);
//		}
//	}

	public void update(){

	}

	public void insert(){
		// if id doesn't exist yet
	}

	public void delete(){

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
