package project.service.interfaces;




import project.models.Game;
import project.models.GoalConceded;
import project.models.GoalScore;
import project.models.Player;
import project.models.RedCard;
import project.models.Substitution;
import project.models.Team;
import project.models.YellowCard;
import project.service.dto.GameDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameService {
    /**
     * Create game
     */
    Game createGame(Team team, String opponentTeam, Set<Player> players);

    /**
     * Create Goal score
     */
    GoalScore createGoalScore(Game game, Set<Player> players,List<Integer> timeMoment);

    /**
     * Create Goal conceded
     */
    GoalConceded createGoalConceded(Game game, Set<Player> players,List<Integer> timeMoment);

    /**
     * Create yellow card
     */
    YellowCard createYellowCard(Game game, Set<Player> players,List<Integer> timeMoment);

    /**
     * Create red card
     */
    RedCard createRedCard(Game game, Set<Player> players,List<Integer> timeMoment);

    /**
     * Create substitution
     */
    Substitution createSubs(Game game, Set<Player> playersIn, Set<Player> playersOut,List<Integer> timeMoment);

    /**
     * Print GameStats
     */
    Map<String, Integer> showGameAndStats(GameService service, Game game, Set<Player> start, Set<Player> noStart);

    /**
     * Find all
     */
    List<GameDTO> findAll();

    /**
     * ShowAllGameTeamInfo
     */
    Set<Game> showAllGameTeamInfo(Integer id);

    /**
     * Delete game
     */
    void deleteGame(Integer id);

    /**
     * StartGamePlayer
     */
    Set<Player> startGamePlayer(Integer id, String[] players);

    /**
     * NoStartGamePlayer
     */
    Set<Player> noStartGamePlayer(Integer id, String[] players);

    /**
     * GoalkeeperСheck
     */
    Integer goalkeeperСheck(Set<Player> playersGo);

    /**
     * CountAttendance
     */
    Integer countAttendance();

    /**
     * RefereeGame
     */
    String refereeGame();

}
