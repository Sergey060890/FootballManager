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
@Service
@Transactional
public class StatsServiceImpl implements StatsService {
    public static final String WIN = "WIN";
    public static final String LOSE = "LOSE";
    public static final String DRAW = "DRAW";
    public static final int COUNT= 0;

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

    @Override
    public Integer statsPlayerCountStartGame(Integer id) {
        Player player = playerService.findPlayerById(id);
        Set<Game> gameSet = gameService
                .showAllGameTeamInfo(player.getTeamPlayer().getTeam_id());
        int count = COUNT;
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

    @Override
    public Integer statsPlayerCountAllGame(Integer id) {
        Player player = playerService.findPlayerById(id);
        Set<Game> gameSet = gameService
                .showAllGameTeamInfo(player.getTeamPlayer().getTeam_id());
        return gameSet.size();
    }

    @Override
    public Integer statsPlayerAllGoal(Integer id) {
        return goalScoreService.showAllGoalPlayer(id).size();
    }

    @Override
    public Integer statsGoalkeeperConcededGoal(Integer id)  {
        return goalConcededService.showAllGoalConcededPlayer(id).size();
    }

    @Override
    public Integer statsPlayerYellowCard(Integer id)  {
        return yellowCardService.showAllYellowCardPlayer(id).size();
    }

    @Override
    public Integer statsPlayerRedCard(Integer id)  {
        return redCardService.showAllRedCardPlayer(id).size();
    }

    @Override
    public String statsPlayerStartPercent(Integer id) {
        double percent = statsPlayerCountStartGame(id).doubleValue()
                * 100 / statsPlayerCountAllGame(id).doubleValue();
        return String.format("%.2f", percent);
    }

    @Override
    public Integer statsTeamCountGame(Integer id)  {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        return gameSet.size();
    }

    @Override
    public Integer statsTeamWinGame(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameWin = COUNT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(WIN)) {
                countGameWin++;
            }
        }
        return countGameWin;
    }

    @Override
    public Integer statsTeamLoseGame(Integer id)  {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameLose = COUNT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(LOSE)) {
                countGameLose++;
            }
        }
        return countGameLose;
    }


    @Override
    public Integer statsTeamDrawGame(Integer id)  {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGameDraw = COUNT;
        for (Game g : gameSet
        ) {
            if (g.getResult().equals(DRAW)) {
                countGameDraw++;
            }
        }
        return countGameDraw;
    }


    @Override
    public Integer statsTeamGoalScore(Integer id){
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalScore = COUNT;
        for (Game g : gameSet
        ) {
            countGoalScore += g.getGoal_score();
        }
        return countGoalScore;
    }

    @Override
    public Integer statsTeamGoalConc(Integer id)  {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalConc = COUNT;
        for (Game g : gameSet
        ) {
            countGoalConc += g.getGoals_conceded();
        }
        return countGoalConc;
    }

    @Override
    public Integer statsTeamYellowCard(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countYellowCard = COUNT;
        for (Game g : gameSet) {
            countYellowCard += g.getYellow_card_score();
        }
        return countYellowCard;
    }

    @Override
    public Integer statsTeamRedCard(Integer id) {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countRedCard = COUNT;
        for (Game g : gameSet
        ) {
            countRedCard += g.getRed_card_score();
        }
        return countRedCard;
    }

    @Override
    public String statsTeamLosePercent(Integer id) {
        double percent = statsTeamLoseGame(id).doubleValue()
                * 100 / statsTeamCountGame(id).doubleValue();
        return String.format("%.2f", percent);
    }

    public static void main(String[] args) {
        System.out.println();
    }

    @Override
    public String statsTeamWinPercent(Integer id) {
        double percent = statsTeamWinGame(id).doubleValue()
                * 100 / statsTeamCountGame(id).doubleValue();
        return String.format("%.2f", percent);
    }

    @Override
    public String statsTeamDrawPercent(Integer id) {
        double percent = statsTeamDrawGame(id).doubleValue()
                * 100 / statsTeamCountGame(id).doubleValue();
        return String.format("%.2f", percent);
    }
}
