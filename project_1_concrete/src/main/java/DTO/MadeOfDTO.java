package DTO;

import javax.swing.text.Element;
import java.util.List;

public class MadeOfDTO {
    final long compoundID;
    final List<Long> ElementIDs;

    public MadeOfDTO(long compoundID, List<Long> ElementIDs) {
        this.compoundID = compoundID;
        this.ElementIDs = ElementIDs;
    }

    public long getCompoundID(){
        return compoundID;
    }

    public int getLength(){
        return ElementIDs.size();
    }
    public long getElementID(int index){
        return ElementIDs.get(index);
    }

    public List<Long> getElementIDs(){
        return ElementIDs;
    }
}
