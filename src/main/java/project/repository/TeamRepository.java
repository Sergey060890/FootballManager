package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Team;
/**
 * TeamRepository
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
