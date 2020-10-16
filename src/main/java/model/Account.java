package model;

public class Account {
    private final String name;
    private final String secondName;

    public Account(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
