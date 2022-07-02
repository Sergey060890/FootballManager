package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.models.Player;
import project.models.Team;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
