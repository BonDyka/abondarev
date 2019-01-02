package ru.job4j.carstore.models.annotated;

import javax.persistence.*;

@Entity
@Table(name = "engines")
public class Engine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "power", nullable = false)
    private int power;

    @Column(name = "volume", nullable = false)
    private double volume;

    public Engine() { }

    public Engine(String type, int power, Double volume) {
        this.type = type;
        this.power = power;
        this.volume = volume;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Engine engine = (Engine) o;

        if (power != engine.power) {
            return false;
        }
        if (Double.compare(engine.volume, volume) != 0) {
            return false;
        }
        return type.equals(engine.type);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type.hashCode();
        result = 31 * result + power;
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.format(
                "Engine{type=%s, power=%s, volume=%s}",
                type, power, volume
        );
    }
}
