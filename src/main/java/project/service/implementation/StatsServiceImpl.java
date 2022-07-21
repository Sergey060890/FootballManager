package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Game;
import project.models.GoalConceded;
import project.models.GoalScore;
import project.models.Player;
import project.models.RedCard;
import project.models.YellowCard;
import project.service.interfaces.GameService;
import project.service.interfaces.GoalConcededService;
import project.service.interfaces.GoalScoreService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.RedCardService;
import project.service.interfaces.StatsService;
import project.service.interfaces.YellowCardService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static project.service.implementation.ConstansImpl.*;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    GoalScoreService goalScoreService;

    @Autowired
    GoalConcededService goalConcededService;

    @Autowired
    YellowCardService yellowCardService;

    @Autowired
    RedCardService redCardService;

    /**
     * Stats player count start game
     */
    @Override
    public Integer statsPlayerCountStartGame(Integer id) {
        Player player = playerService.findPlayerById(id);
        Set<Game> gameSet = gameService
                .showAllGameTeamInfo(player.getTeamPlayer().getTeam_id());
        int count = INT;
        for (Game g : gameSet
        ) {
            for (Player pl : g.getPlayers()
            ) {
                if (pl.getPlayer_id().equals(player.getPlayer_id())) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Stats player count all game
     */
    @Override
    public Integer statsPlayerCountAllGame(Integer id) {
        Player player = playerService.findPlayerById(id);
        Set<Game> gameSet = gameService
                .showAllGameTeamInfo(player.getTeamPlayer().getTeam_id());
        return gameSet.size();
    }

    /**
     * Stats player all goal
     */
    @Override
    public Integer statsPlayerAllGoal(Integer id) {
        return goalScoreService.showAllGoalPlayer(id).size();
    }

    /**
     * Stats player all goal conceded game
     */
    @Override
    public Integer statsGoalkeeperConcededGoal(Integer id) {
        return goalConcededService.showAllGoalConcededPlayer(id).size();
    }

    /**
     * Stats player yellow card
     */
    @Override
    public Integer statsPlayerYellowCard(Integer id) {
        return yellowCardService.showAllYellowCardPlayer(id).size();
    }

    /**
     * Stats player red card
     */
    @Override
    public Integer statsPlayerRedCard(Integer id) {
        return redCardService.showAllRedCardPlayer(id).size();
    }

    /**
     * Stats player start game percent
     */
    @Override
    public String statsPlayerStartPercent(Integer id) {
        double percent = statsPlayerCountStartGame(id).doubleValue()
                * INT100 / statsPlayerCountAllGame(id).doubleValue();
        return String.format(FORMAT, percent);
    }

    /**
     * Stats team count game
     */
    @Override
    public Integer statsTeamCountGame(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        return gameSet.size();
    }

    /**
     * Stats team count win game
     */
    @Override
    public Integer statsTeamWinGame(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameWin = INT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(WIN)) {
                countGameWin++;
            }
        }
        return countGameWin;
    }

    /**
     * Stats team count lose game
     */
    @Override
    public Integer statsTeamLoseGame(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameLose = INT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(LOSE)) {
                countGameLose++;
            }
        }
        return countGameLose;
    }

    /**
     * Stats team count draw game
     */
    @Override
    public Integer statsTeamDrawGame(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameDraw = INT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(DRAW)) {
                countGameDraw++;
            }
        }
        return countGameDraw;
    }

    /**
     * Stats team goal score
     */
    @Override
    public Integer statsTeamGoalScore(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalScore = INT;
        for (Game g : gameSet
        ) {
            countGoalScore += g.getGoal_score();
        }
        return countGoalScore;
    }

    /**
     * Stats team goal conceded
     */
    @Override
    public Integer statsTeamGoalConc(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalConc = INT;
        for (Game g : gameSet
        ) {
            countGoalConc += g.getGoals_conceded();
        }
        return countGoalConc;
    }

    /**
     * Stats team yellow card
     */
    @Override
    public Integer statsTeamYellowCard(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countYellowCard = INT;
        for (Game g : gameSet) {
            countYellowCard += g.getYellow_card_score();
        }
        return countYellowCard;
    }

    /**
     * Stats team red card
     */
    @Override
    public Integer statsTeamRedCard(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countRedCard = INT;
        for (Game g : gameSet
        ) {
            countRedCard += g.getRed_card_score();
        }
        return countRedCard;
    }

    /**
     * Stats team lose percent
     */
    @Override
    public String statsTeamLosePercent(Integer id) {
        double percent = statsTeamLoseGame(id).doubleValue()
                * INT100 / statsTeamCountGame(id).doubleValue();
        return String.format(FORMAT, percent);
    }

    /**
     * Stats team win percent
     */
    @Override
    public String statsTeamWinPercent(Integer id) {
        double percent = statsTeamWinGame(id).doubleValue()
                * INT100 / statsTeamCountGame(id).doubleValue();
        return String.format(FORMAT, percent);
    }

    /**
     * Stats team draw percent
     */
    @Override
    public String statsTeamDrawPercent(Integer id) {
        double percent = statsTeamDrawGame(id).doubleValue()
                * INT100 / statsTeamCountGame(id).doubleValue();
        return String.format(FORMAT, percent);
    }
}
