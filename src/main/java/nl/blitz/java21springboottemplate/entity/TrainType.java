package nl.blitz.java21springboottemplate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;  // imports Entity, Id, GeneratedValue, GenerationType, OneToMany, ManyToOne, CascadeType, etc.
import java.time.LocalDateTime; // for LocalDateTime
import java.util.List;

@Entity
public class TrainType { //Declares a new Java class called TrainType
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique ID for this train type

    private String typeName; // e.g., "IC", "Sprinter"

    private String description; // optional, e.g., "Intercity train with limited stops"

    @OneToMany(mappedBy = "trainType", cascade = CascadeType.ALL)
    private List<Arrival> arrivals; // all arrivals of this train type

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Arrival> getArrivals() { return arrivals; }
    public void setArrivals(List<Arrival> arrivals) { this.arrivals = arrivals; }
}
