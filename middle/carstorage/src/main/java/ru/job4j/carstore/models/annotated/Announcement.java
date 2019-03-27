package ru.job4j.carstore.models.annotated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "announcements")
public class Announcement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public Announcement() { }

    public Announcement(User author, Car car, int price, boolean status, List<Photo> photos) {
        this.author = author;
        this.car = car;
        this.price = price;
        this.status = status;
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Announcement that = (Announcement) o;
        return price == that.price
                && status == that.status
                && author.equals(that.author)
                && car.equals(that.car)
                && photos.containsAll(that.photos)
                && that.getPhotos().containsAll(photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, car, price, status, photos);
    }

    @Override
    public String toString() {
        return "Announcement{"
                + "author=" + author
                + ", car=" + car
                + ", price=" + price
                + ", status=" + status
                + ", photos=" + photos
                + '}';
    }
}
