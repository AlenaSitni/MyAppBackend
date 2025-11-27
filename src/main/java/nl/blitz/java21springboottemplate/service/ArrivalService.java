package nl.blitz.java21springboottemplate.service;

import nl.blitz.java21springboottemplate.dto.ArrivalDto;
import nl.blitz.java21springboottemplate.entity.Arrival;
import nl.blitz.java21springboottemplate.entity.TrainType;
import nl.blitz.java21springboottemplate.entity.Message;
import nl.blitz.java21springboottemplate.repository.ArrivalRepository;
import nl.blitz.java21springboottemplate.repository.TrainTypeRepository;
import nl.blitz.java21springboottemplate.client.NsClient;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrivalService {

    private final NsClient nsClient;
    private final ArrivalRepository arrivalRepository;
    private final TrainTypeRepository trainTypeRepository;

    public ArrivalService(NsClient nsClient,
                          ArrivalRepository arrivalRepository,
                          TrainTypeRepository trainTypeRepository) {
        this.nsClient = nsClient;
        this.arrivalRepository = arrivalRepository;
        this.trainTypeRepository = trainTypeRepository;
    }

    // -----------------------------------------------------
    // AUTO REFRESH (runs every 30s)
    // -----------------------------------------------------
    @Scheduled(fixedRate = 30000)
    public void autoRefreshArrivals() {
        try {
            refreshArrivals();
            System.out.println("Auto refresh OK ✔");
        } catch (Exception e) {
            System.out.println("Auto refresh ERROR ❌ " + e.getMessage());
        }
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
    // DTO → ENTITY
    // -----------------------------------------------------
    private Arrival mapDtoToEntity(ArrivalDto dto) {

        Arrival arrival = new Arrival();

        arrival.setDeparture(dto.getOrigin());
        arrival.setDestination(dto.getName());

        arrival.setPlannedTime(dto.getPlannedDateTime() != null
                ? dto.getPlannedDateTime().toLocalDateTime()
                : null);

        arrival.setActualTime(dto.getActualDateTime() != null
                ? dto.getActualDateTime().toLocalDateTime()
                : null);

        arrival.setCancelled(dto.isCancelled());

        // -----------------------------------------
        // TRAIN TYPE: FIXED (no detached entities)
        // -----------------------------------------
        TrainType type = trainTypeRepository
                .findByTypeName(dto.getTrainCategory())
                .orElseGet(() -> {
                    TrainType t = new TrainType();
                    t.setTypeName(dto.getTrainCategory());
                    return trainTypeRepository.save(t);
                });

        arrival.setTrainType(type);

        // -----------------------------------------
        // MESSAGES: FIX (must call msg.setArrival)
        // -----------------------------------------
        if (dto.getMessages() != null) {
            List<Message> messages = dto.getMessages().stream()
                    .map(msgDto -> {
                        Message msg = new Message();
                        msg.setMessage(msgDto.getMessage());
                        msg.setArrival(arrival);
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
                .filter(this::isDelayed)
                .collect(Collectors.toList());
    }

    private boolean isDelayed(Arrival a) {
        return a.getActualTime() != null &&
                a.getPlannedTime() != null &&
                a.getActualTime().isAfter(a.getPlannedTime());
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
