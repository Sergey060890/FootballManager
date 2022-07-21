package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.GoalScore;
import project.models.Team;
/**
 * GoalScoreRepository
 */
@Repository
public interface GoalScoreRepository extends JpaRepository<GoalScore, Integer> {
}
