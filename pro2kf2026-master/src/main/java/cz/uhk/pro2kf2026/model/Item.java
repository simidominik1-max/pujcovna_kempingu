package cz.uhk.pro2kf2026.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double pricePerDay;


    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Pomocí AltGr + Insert vygenerujte gettery, settery a konstruktor

    public Item(String name, String description, double pricePerDay) {
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    public Item() {}


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

    public String getDescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

}
