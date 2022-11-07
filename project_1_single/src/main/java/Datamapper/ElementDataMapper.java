//package Datamapper;
//
//import datasource.ChemicalRowDataGateway;
//import datasource.Type;
//import model.Element;
//import model.Mapper.ElementNotFoundException;
//
//public class ElementDataMapper {
// private Element myElement;
//
//    public ElementDataMapper(String name, long atomicNumber, double atomicMass) throws ElementNotFoundException {
//        ChemicalRowDataGateway chemical = null;
//        try {
//            chemical = new ChemicalRowDataGateway(name, atomicNumber, atomicMass, 0, 0, Type.ELEMENT);
//        } catch (Exception e) {
//            throw new ElementNotFoundException();
//        }
//    }
//
//    public void setID(long ID) {
//        this.ID = ID;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAtomicNumber(long atomicNumber) {
//        this.atomicNumber = atomicNumber;
//    }
//
//    public void setAtomicMass(double atomicMass) {
//        this.atomicMass = atomicMass;
//    }
//}
