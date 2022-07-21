package project.service.interfaces;




import project.models.Player;
import project.models.Team;
import project.service.dto.TeamDTO;
import java.util.Set;

public interface TeamService {
    /**
     * Create team
     */

    Team createTeam(String teamName, String teamCity,
                    String teamCountry, String teamStadium,
                    String teamCoach);

    /**
     * Add player in team
     */
    Player addPlayerInTeam(Player player, Team team);

    /**
     * Delete player with team
     */

    Iterable<Team> findAll();

    /**
     * Print all player info
     */

    Set<Player> showAllPlayerTeamInfo(Integer id);

    /**
     * UpdateTeam
     */
    void updateTeam(Integer id, String teamName, String teamCity,
                    String teamCountry, String teamStadium,
                    String teamCoach);

    /**
     * DeleteTeam
     */
    void deleteTeam(Integer id);

    /**
     * Find team by id (dto)
     */
    TeamDTO findTeamById(Integer id);

    /**
     * Find team by id
     */
    Team findTeamId(Integer id);

    /**
     * Delete all player team
     */
    void deleteAllPlayerTeam(TeamService teamService, PlayerService playerService, Integer id);

    /**
     * Delete all game team
     */
    void deleteAllGameTeam(GameService gameService, Integer id);

    /**
     * Delete all result team
     */
    void deleteAllResultTeam(ResultService resultService, Integer id);
}
