package nl.blitz.java21springboottemplate.repository;

import nl.blitz.java21springboottemplate.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Find all messages associated with a specific Arrival by its ID.
     *
     * @param arrivalId the ID of the Arrival
     * @return List of Message objects linked to that Arrival
     */
    List<Message> findByArrivalId(Long arrivalId);

}