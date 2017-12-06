package entities;

import entities.*;

public enum ObjectType {
    LOCATION("Location"),
    OFFICE("Office"),
    DEPARTMENT("Department"),
    EMPLOYEE("Employee");

    private final String name;

    private ObjectType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
