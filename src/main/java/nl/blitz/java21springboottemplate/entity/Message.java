package nl.blitz.java21springboottemplate.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique ID for the message

    private String message; // the actual message text, e.g., "Train delayed 5 minutes"

    private LocalDateTime timestamp; // when the message was created

    @ManyToOne(cascade = CascadeType.ALL)
    private Arrival arrival; // which Arrival this message belongs to

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Arrival getArrival() { return arrival; }
    public void setArrival(Arrival arrival) { this.arrival = arrival; }
}
