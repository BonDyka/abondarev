package ru.job4j.carstore.models.annotated;

import javax.persistence.*;
@Entity
@Table(name = "cars")
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "trns_id")
    private Transmission transmission;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "body_id")
    private CarBody body;

    public Car() { }

    public Car(String name, Engine engine, Transmission transmission, CarBody body) {
        this.name = name;
        this.engine = engine;
        this.transmission = transmission;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public CarBody getBody() {
        return body;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car car = (Car) o;

        if (id != car.id) {
            return false;
        }
        if (!name.equals(car.name)) {
            return false;
        }
        if (!engine.equals(car.engine)) {
            return false;
        }
        if (!transmission.equals(car.transmission)) {
            return false;
        }
        return body.equals(car.body);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + engine.hashCode();
        result = 31 * result + transmission.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{name='" + name + "', engine=" + engine
                + ", transmission=" + transmission + ", body=" + body + '}';
    }
}
