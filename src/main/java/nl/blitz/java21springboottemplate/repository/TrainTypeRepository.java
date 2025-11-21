package nl.blitz.java21springboottemplate.repository;

import nl.blitz.java21springboottemplate.entity.TrainType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TrainTypeRepository extends JpaRepository<TrainType, Long> {

    /**
     * Find a train type by its name.
     * Example names: "IC", "Sprinter", "Intercity direct"
     *
     * @param typeName the name of the train type
     * @return Optional containing the TrainType if found
     */
    Optional<TrainType> findByTypeName(String typeName);

    /**
     * Check if a train type with this name already exists.
     *
     * @param typeName the name of the train type
     * @return true if exists, false otherwise
     */
    boolean existsByTypeName(String typeName);

    /**
     * Find all train types that contain a keyword in their description.
     *
     * Example: findByDescriptionContainingIgnoreCase("fast")
     */
    List<TrainType> findByDescriptionContainingIgnoreCase(String keyword);
}