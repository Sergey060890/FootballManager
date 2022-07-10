package project.service.interfaces;




import project.models.Player;
import project.models.Team;
import project.service.dto.TeamDTO;

import java.sql.SQLException;
import java.util.List;
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
//    void deletePlayerWithTeam(Player player)
//            throws InvocationTargetException,
//            NoSuchMethodException, IllegalAccessException;

//    /**
//     * Print AllTeam
//     */
//    List<Team> showAllTeam();

    /**
     * FindAllTeam
     */
//    List<TeamDTO> findAll();

    Iterable<Team> findAll();

    Player showOnePlayerInfo(Integer id);

    /**
     * Print AllPlayerInfo
     */

    Set<Player> showAllPlayerTeamInfo(Integer id) ;

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

    TeamDTO findTeamById(Integer id);

    Team findTeamId(Integer id);

    void deleteAllPlayerTeam(TeamService teamService, PlayerService playerService, Integer id);

    void deleteAllGameTeam(GameService gameService, Integer id);

    void deleteAllResultTeam(ResultService resultService, Integer id);
}
