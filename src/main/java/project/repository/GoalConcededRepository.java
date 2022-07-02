package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.models.GoalConceded;

public interface GoalConcededRepository extends JpaRepository<GoalConceded, Integer> {
}
