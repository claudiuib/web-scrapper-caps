package org.example;

import javax.persistence.*;
/**
 * This class represent the Caps model,stored in caps table in database.
 * It has getters and setters to access each of the fields and an overridden 'toString' method to display the data.
 */
@Entity
@Table(name = "caps")
public class Caps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

//

    String style_key;

    @Column(name = "name")
    String name;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStyle_key() {
        return style_key;
    }

    public void setStyle_key(String style_key) {
        this.style_key = style_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Caps{" +
                "id=" + id +
                ", style_key='" + style_key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
