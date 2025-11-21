package nl.blitz.java21springboottemplate.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime; // <-- THIS IS REQUIRED
import java.util.List;

@Entity //Marks this class as a JPA entity, meaning it maps to a database table

public class Arrival { //Class declaration
    @Id //Marks this field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells JPA (the Java Persistence API) that the value of this field should be automatically generated when a new record is saved to the database
    private Long id; //Creating a variable for id

    private String departure; //Declaring a variable for departure - station name from which the train departs
    private String destination; //Declaring a variable for arrival - station name to which the train arrives

    private LocalDateTime plannedTime; //LocalDateTime - datatype, declaring variables
    private LocalDateTime actualTime;

    private boolean cancelled; //boolean is true/false value: true - train was cancelled; false - train ran normally

    @ManyToOne(cascade = CascadeType.ALL) //Many Arrival records can point to one TrainType
    private TrainType trainType;

    @OneToMany(cascade = CascadeType.ALL) //One Arrival can have many Message objects associated with it. Message objects are, for instance: “Train is delayed 5 minutes”
    private List<Message> messages; //List<Message> is a list, and it will only contain objects of type Message

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getPlannedTime() { return plannedTime; }
    public void setPlannedTime(LocalDateTime plannedTime) { this.plannedTime = plannedTime; }

    public LocalDateTime getActualTime() { return actualTime; }
    public void setActualTime(LocalDateTime actualTime) { this.actualTime = actualTime; }

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    public TrainType getTrainType() { return trainType; }
    public void setTrainType(TrainType trainType) { this.trainType = trainType; }

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }
}