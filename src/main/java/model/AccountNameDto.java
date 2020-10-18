package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNameDto that = (AccountNameDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AccountNameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
