package dto;

public class AccountNameDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AccountNameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
