package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.RedCard;
import project.models.Team;
/**
 * RedCardRepository
 */
@Repository
public interface RedCardRepository extends JpaRepository<RedCard, Integer> {
}
