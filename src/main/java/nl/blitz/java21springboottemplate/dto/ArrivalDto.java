package nl.blitz.java21springboottemplate.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalDto {

    private String origin;                     // Where the train comes from
    private String name;                       // Train name / number
    private String trainCategory;              // "IC", "SPR", etc.
    private LocalDateTime plannedDateTime;     // Planned arrival
    private LocalDateTime actualDateTime;      // Actual arrival
    private String plannedTrack;               // Platform
    private String actualTrack;                // Actual platform

    private TrainTypeDto product;              // Nested train type info

    private boolean cancelled;                 // If the train is cancelled
    private String arrivalStatus;              // "INCOMING", "ON_STATION", etc.
    private List<MessageDto> messages;         // Any messages

    // Getters & Setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTrainCategory() { return trainCategory; }
    public void setTrainCategory(String trainCategory) { this.trainCategory = trainCategory; }

    public LocalDateTime getPlannedDateTime() { return plannedDateTime; }
    public void setPlannedDateTime(LocalDateTime plannedDateTime) { this.plannedDateTime = plannedDateTime; }

    public LocalDateTime getActualDateTime() { return actualDateTime; }
    public void setActualDateTime(LocalDateTime actualDateTime) { this.actualDateTime = actualDateTime; }

    public String getPlannedTrack() { return plannedTrack; }
    public void setPlannedTrack(String plannedTrack) { this.plannedTrack = plannedTrack; }

    public String getActualTrack() { return actualTrack; }
    public void setActualTrack(String actualTrack) { this.actualTrack = actualTrack; }

    public TrainTypeDto getProduct() { return product; }
    public void setProduct(TrainTypeDto product) { this.product = product; }

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    public String getArrivalStatus() { return arrivalStatus; }
    public void setArrivalStatus(String arrivalStatus) { this.arrivalStatus = arrivalStatus; }

    public List<MessageDto> getMessages() { return messages; }
    public void setMessages(List<MessageDto> messages) { this.messages = messages; }
}
