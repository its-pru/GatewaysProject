package datasource;

import java.util.ArrayList;
import java.util.List;

public class ChemicalRowDataGateway {
	long id;
	String Name;
	String inHabits;
	long atomicNumber;
	double atomicMass;
	List<Long> ids = new ArrayList<>();
	long Solute;
	long dissolvedBy;
	List<Long> dissolves = new ArrayList<>();

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
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
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
