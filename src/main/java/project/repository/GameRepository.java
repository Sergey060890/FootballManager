package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Game;
import project.models.Team;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
