package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccountNameDto {
    private final String name;

    @JsonCreator
    public AccountNameDto(@JsonProperty("name") String name) {
        this.name = Objects.requireNonNull(name, "Name can't be null");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AccountNameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
