package ru.job4j.carstore.models.descripted;

public class Car {

    private long id;

    private String name;

    private Engine engine;

    private Transmission transmission;

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
        return String.format("Car{name='%s', engine='%s', body='%s', transmission='%s'.",
                this.name, this.engine, this.body, this.transmission);
    }
}
