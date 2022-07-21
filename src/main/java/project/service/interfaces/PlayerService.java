package project.service.interfaces;

import project.models.Player;
import project.service.dto.PlayerDTO;
import java.util.List;

public interface PlayerService {
    /**
     * Create player
     */
    Player createPlayer(String name, String surname,
                        String country, Integer age,
                        String position);

    /**
     * Find all player
     */
    List<PlayerDTO> findAll();

    /**
     * Delete player
     */
    void deletePlayer(Integer id);

    /**
     * Find player by id
     */
    Player findPlayerById(Integer id);

    /**
     * Update player
     */
    void updatePlayer(Integer id, String playerName, String playerSurname,
                      String country, Integer age,
                      String position);

    /**
     * Delete all goal player
     */
    void deleteAllGoalPlayer(GoalScoreService goalScoreService, Integer id);

    /**
     * Delete all goal conceded player
     */
    void deleteAllGoalConcededPlayer(GoalConcededService goalConcededService, Integer id);

    /**
     * Delete all yellow card player
     */
    void deleteAllYellowCardPlayer(YellowCardService yellowCardService, Integer id);

    /**
     * Delete all red card player
     */
    void deleteAllRedCardPlayer(RedCardService redCardService, Integer id);

    /**
     * Delete all substitution player
     */
    void deleteAllSubsPlayer(SubsService subsService, Integer id);

    /**
     * Delete all game player
     */
    void deleteAllGamePlayer(GameService gameService, Integer idTeam, Integer idPlayer);
}
