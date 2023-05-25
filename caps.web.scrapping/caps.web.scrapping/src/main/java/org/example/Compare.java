package org.example;


import javax.persistence.*;
/**
 * This class represent the caps_instance model,stored in caps_instance table in database.
 * It has getters and setters to access each of the fields and an overridden 'toString' method to display the data.
 */
@Entity
@Table(name = "compare")
public class Compare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Override
    public String toString() {
        return "Compare{" +
                "id=" + id +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", capsInstance=" + capsInstance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "price")
    double price;

    @Column(name = "url")
    String url;
    @ManyToOne
    @JoinColumn(name = "instance_id", nullable = false)

    CapsInstance capsInstance;

    public CapsInstance getCapsInstance() {
        return capsInstance;
    }

    public void setCapsInstance(CapsInstance capsInstance) {
        this.capsInstance = capsInstance;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


}
