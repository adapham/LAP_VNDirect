package edu.hanoi.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "HN_GROUP", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Group {
    private String name;
    private int id;

    @Column(name = "name", nullable = false,length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
