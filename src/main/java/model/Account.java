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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) &&
                Objects.equals(secondName, account.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
