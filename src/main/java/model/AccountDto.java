package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccountDto {
    private final String name;
    private final String secondName;

    @JsonCreator
    public AccountDto(@JsonProperty("name") String name, @JsonProperty("secondName") String secondName) {
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
        return "AccountDto{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
