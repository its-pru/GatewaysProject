package model;

public abstract class Chemical {
    String name;
    String nameBefore;

    public Chemical(String name) {

        this.name = name;
        this.nameBefore = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNameBefore() {
        return nameBefore;
    }

}

