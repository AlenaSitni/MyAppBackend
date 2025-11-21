package nl.blitz.java21springboottemplate.repository;

import nl.blitz.java21springboottemplate.entity.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArrivalRepository extends JpaRepository<Arrival, Long> {

    /**
     * Find all arrivals departing from a specific station.
     *
     * @param departureStation the station name (e.g., "Tilburg")
     * @return list of arrivals that depart from the station
     */
    List<Arrival> findByDeparture(String departureStation);

    /**
     * Find all arrivals going to a specific destination.
     *
     * @param destinationStation the destination station (e.g., "Eindhoven")
     * @return list of arrivals arriving at the station
     */
    List<Arrival> findByDestination(String destinationStation);

    /**
     * Find all cancelled arrivals.
     *
     * @return list of cancelled trains
     */
    List<Arrival> findByCancelledTrue();

    /**
     * Find all delayed arrivals: plannedTime < actualTime.
     *
     * NOTE: You will use this later in your Service logic.
     */
    List<Arrival> findByActualTimeAfter(LocalDateTime plannedTime);

    /**
     * Find arrivals by train type ID.
     *
     * @param trainTypeId ID of the TrainType
     * @return list of arrivals using that train type
     */
    List<Arrival> findByTrainTypeId(Long trainTypeId);
}