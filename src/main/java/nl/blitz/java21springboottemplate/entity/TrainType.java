package nl.blitz.java21springboottemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class TrainType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String typeName;

    private String description;

    @OneToMany(mappedBy = "trainType")
    @JsonIgnore   // prevent infinite recursion
    private List<Arrival> arrivals;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Arrival> getArrivals() { return arrivals; }
    public void setArrivals(List<Arrival> arrivals) { this.arrivals = arrivals; }
}
