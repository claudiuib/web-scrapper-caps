package org.example;


import javax.persistence.*;

/**
 * This class represent the caps_instance model,stored in caps_instance table in database.
 * It has getters and setters to access each of the fields and an overridden 'toString' method to display the data.
 */
@Entity
@Table(name = "caps_instance")

public class CapsInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;


    @Column(name = "colour")
    String colour;

    @Column(name = "image_url")
    String image_url;


    @ManyToOne
    @JoinColumn(name ="caps_id", nullable = false)


    Caps caps;

    @Override
    public String toString() {
        return "CapsInstance{" +
                "id=" + id +
                ", colour='" + colour + '\'' +
                ", image_url='" + image_url + '\'' +
                ", caps=" + caps +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Caps getCaps() {
        return caps;
    }

    public void setCaps(Caps caps) {
        this.caps = caps;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
