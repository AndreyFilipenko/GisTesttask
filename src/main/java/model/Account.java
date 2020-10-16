package model;

import java.util.Objects;

public class Account {
    private final String name;
    private final String secondName;

    public Account(String name, String secondName) {
        this.name = Objects.requireNonNull(name, "Name can't be null");
        this.secondName = Objects.requireNonNull(secondName, "Second name can't be null");
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
