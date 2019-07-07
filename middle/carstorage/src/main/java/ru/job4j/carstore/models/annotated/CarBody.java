package ru.job4j.carstore.models.annotated;

import javax.persistence.*;

@Entity
@Table(name = "carbodys")
public class CarBody {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type", nullable = false, unique = true)
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

    public String getName() {
        return type;
    }

    public void setName(String name) {
        this.type = name;
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
        return String.format("CarBody{type='%s'}", this.type);
    }
}
