package ru.job4j.carstore.models.descripted;

public class CarBody {

    private long id;

    private String type;

    public CarBody() { }

    public CarBody(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarBody carBody = (CarBody) o;

        return type.equals(carBody.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return "CarBody{name='" + type + "\'}";
    }
}
