package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.GoalConceded;
/**
 * GoalConcededRepository
 */
@Repository
public interface GoalConcededRepository extends JpaRepository<GoalConceded, Integer> {
}
