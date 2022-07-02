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

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameService {
    /**
     * Create game
     */
    Game createGame(Team team, String opponentTeam, Set<Player> players) throws SQLException;

    Game createGameNoPlayers(Team team,
                             String opponentTeam);


    Game addPlayersInStartGame(Integer idGame, Set<Player> players) throws SQLException;

    /**
     * Create Goal score
     */
    GoalScore createGoalScore(Game game, Set<Player> players);

    /**
     * Create Goal conceded
     */
    GoalConceded createGoalConceded(Game game, Set<Player> players);

    /**
     * Create yellow card
     */
    YellowCard createYellowCard(Game game, Set<Player> players);

    /**
     * Create red card
     */
    RedCard createRedCard(Game game, Set<Player> players);

    /**
     * Create substitution
     */
    Substitution createSubs(Game game, Set<Player> playersIn, Set<Player> playersOut);

    /**
     * Print AllPlayerGameInfo
     */
    Set<Game> showAllPlayerGame(Game game);

    /**
     * Print GameStats
     */
    List<String> showGameAndStats(GameService service, Game game, Set<Player> start, Set<Player> noStart);

    List<GameDTO> findAll();

    Map<Integer, String> showAllOpponentTeamInfo(Set<Game> gameSet);

    Set<Game> showAllGameTeamInfo(Integer id);

    void deleteGame(Integer id);

    void updateGame(Integer id,
                    String opponentTeam);

    Game findGameById(Integer id);

    Set<Player> showAllGamePlayerInfo(Integer id);

    Set<Player> startGamePlayer(Integer id, String[] players);

    Set<Player> noStartGamePlayer(Integer id, String[] players);

    Map<Integer, String> opponentRemoveTeam(GameService service, Game game, Set<Game> gameSet);

    Integer goalkeeperСheck(Set<Player> playersGo);

    Integer countAttendance();

    String refereeGame();

}
