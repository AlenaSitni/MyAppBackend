package nl.blitz.java21springboottemplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Arrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;
    private String destination;

    private LocalDateTime plannedTime;
    private LocalDateTime actualTime;

    private boolean cancelled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_type_id")
    private TrainType trainType;

    @OneToMany(mappedBy = "arrival", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    // Getters & Setters
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
