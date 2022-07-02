package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Team;
import project.models.YellowCard;

@Repository
public interface YellowCardRepository extends JpaRepository<YellowCard, Integer> {
}
