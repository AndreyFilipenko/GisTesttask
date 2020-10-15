package model;

public class AccountModel {
    private final String name;
    private final String secondName;

    public AccountModel(String name, String secondName) {
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
