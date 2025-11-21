package nl.blitz.java21springboottemplate.service;

import nl.blitz.java21springboottemplate.dto.ArrivalDto;
import nl.blitz.java21springboottemplate.entity.Arrival;
import nl.blitz.java21springboottemplate.entity.TrainType;
import nl.blitz.java21springboottemplate.entity.Message;
import nl.blitz.java21springboottemplate.repository.ArrivalRepository;
import nl.blitz.java21springboottemplate.client.NsClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrivalService {

    private final NsClient nsClient;
    private final ArrivalRepository arrivalRepository;

    public ArrivalService(NsClient nsClient, ArrivalRepository arrivalRepository) {
        this.nsClient = nsClient;
        this.arrivalRepository = arrivalRepository;
    }

    // -----------------------------------------------------
    // FETCH + MAP + SAVE
    // -----------------------------------------------------
    public List<Arrival> refreshArrivals() {
        List<ArrivalDto> dtoList = nsClient.fetchArrivals();

        List<Arrival> arrivals = dtoList.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());

        arrivalRepository.saveAll(arrivals);
        return arrivals;
    }

    // -----------------------------------------------------
    // Mapping logic: DTO → Entity
    // -----------------------------------------------------
    private Arrival mapDtoToEntity(ArrivalDto dto) {
        Arrival arrival = new Arrival();

        arrival.setDeparture(dto.getOrigin());  // "origin" → your "departure"
        arrival.setDestination(dto.getName());  // you can change if needed

        arrival.setPlannedTime(dto.getPlannedDateTime());
        arrival.setActualTime(dto.getActualDateTime());
        arrival.setCancelled(dto.isCancelled());

        // Train Type mapping
        TrainType type = new TrainType();
        type.setTypeName(dto.getTrainCategory());
        arrival.setTrainType(type);

        // Messages mapping
        if (dto.getMessages() != null) {
            List<Message> messages = dto.getMessages().stream()
                    .map(msgDto -> {
                        Message msg = new Message();
                        msg.setMessage(msgDto.getMessage());
                        return msg;
                    })
                    .collect(Collectors.toList());

            arrival.setMessages(messages);
        }

        return arrival;
    }

    // -----------------------------------------------------
    // BUSINESS QUERIES
    // -----------------------------------------------------
    public List<Arrival> getDelayedArrivals() {
        return arrivalRepository.findAll().stream()
                .filter(a -> a.getActualTime() != null &&
                        !a.getActualTime().equals(a.getPlannedTime()))
                .collect(Collectors.toList());
    }

    public List<Arrival> getCancelledArrivals() {
        return arrivalRepository.findAll().stream()
                .filter(Arrival::isCancelled)
                .collect(Collectors.toList());
    }

    public List<Arrival> getAllArrivals() {
        return arrivalRepository.findAll();
    }
}