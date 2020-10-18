package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(secondName, that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName);
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
