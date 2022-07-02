package project.service.implementation;



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

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class StatsServiceImpl implements StatsService {
    public static final String WIN = "WIN";
    public static final String LOSE = "LOSE";
    public static final String DRAW = "DRAW";
    public static final int COUNT= 0;

    PlayerService playerService = new PlayerServiceImpl();
    GameService gameService = new GameServiceImpl();
    GoalScoreService goalScoreService = new GoalScoreServiceImpl();
    GoalConcededService goalConcededService = new GoalConcededServiceImpl();
    YellowCardService yellowCardService = new YellowCardServiceImpl();
    RedCardService redCardService = new RedCardServiceImpl();

    @Override
    public Integer statsPlayerCountStartGame(Integer id) throws SQLException {
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
    public Integer statsPlayerCountAllGame(Integer id) throws SQLException {
        Player player = playerService.findPlayerById(id);
        Set<Game> gameSet = gameService
                .showAllGameTeamInfo(player.getTeamPlayer().getTeam_id());
        return gameSet.size();
    }

    @Override
    public Integer statsPlayerAllGoal(Integer id) throws SQLException {
        Player player = playerService.findPlayerById(id);
        List<GoalScore> goalScores = goalScoreService.showAllGoalInfo();
        int countGoalPlayer = COUNT;
        for (GoalScore goal : goalScores
        ) {
            if (goal.getPlayer().getPlayer_id().equals(player.getPlayer_id())) {
                countGoalPlayer++;

            }
        }
        return countGoalPlayer;
    }

    @Override
    public Integer statsGoalkeeperConcededGoal(Integer id) throws SQLException {
        Player player = playerService.findPlayerById(id);
        List<GoalConceded> goalConcededs = goalConcededService.showAllGoalConcededInfo();
        int countConcededPlayer = COUNT;
        for (GoalConceded goal : goalConcededs
        ) {
            if (goal.getPlayer().getPlayer_id().equals(player.getPlayer_id())) {
                countConcededPlayer++;
            }
        }
        return countConcededPlayer;
    }

    @Override
    public Integer statsYellowCard(Integer id) throws SQLException {
        Player player = playerService.findPlayerById(id);
        List<YellowCard> yellowCards = yellowCardService.showAllYellowCardInfo();
        int countYellowCard = COUNT;
        for (YellowCard yc : yellowCards
        ) {
            if (yc.getPlayer().getPlayer_id().equals(player.getPlayer_id())) {
                countYellowCard++;
            }
        }
        return countYellowCard;
    }

    @Override
    public Integer statsRedCard(Integer id) throws SQLException {
        Player player = playerService.findPlayerById(id);
        List<RedCard> redCards = redCardService.showAllRedCardInfo();
        int countRedCard = COUNT;
        for (RedCard rc : redCards
        ) {
            if (rc.getPlayer().getPlayer_id().equals(player.getPlayer_id())) {
               countRedCard++;
            }
        }
        return countRedCard;
    }



//        @Override
//    public String statsPlayer(Player player) {
//
//        System.out.println("Goals:");
//        int countPlayerGoles = 0;
//        String opponentName = null;
//        for (GoalScore g : GameServiceImpl.goalScores
//        ) {
//            if (g.getPlayer().getPlayer_surname() ==
//                    player.getPlayer_surname()) {
//                if (opponentName != g.getGame().getOpponent_name()) {
//                    opponentName = g.getGame().getOpponent_name();
//                }
//                countPlayerGoles++;
//            }
//        }
//        System.out.println(opponentName + " - " + countPlayerGoles);
//
//        int countYellowCard = 0;
//        for (YellowCard ycrd : GameServiceImpl.yellowCards
//        ) {
//            if (ycrd.getPlayer().getPlayer_surname() ==
//                    player.getPlayer_surname()) {
//                countYellowCard++;
//            }
//        }
//        System.out.println("Yellow cards: " + countYellowCard);
//
//        int countRedCard = 0;
//        for (RedCard rc : GameServiceImpl.redCards
//        ) {
//            if (rc.getPlayer().getPlayer_surname() ==
//                    player.getPlayer_surname()) {
//                countRedCard++;
//            }
//        }
//        System.out.println("Red cards: " + countRedCard);
//        String str = player.toString();
//        return str;
//
//    }

    @Override
    public Integer statsTeamCountGame(Integer id) throws SQLException {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        return gameSet.size();
    }

    @Override
    public Integer statsTeamWinGame(Integer id) throws SQLException {
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
    public Integer statsTeamLoseGame(Integer id) throws SQLException {
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
    public Integer statsTeamDrawGame(Integer id) throws SQLException {
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
    public Integer statsTeamGoalScore(Integer id) throws SQLException {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalScore = COUNT;
        for (Game g : gameSet
        ) {
            countGoalScore += g.getGoal_score();
        }
        return countGoalScore;
    }

    @Override
    public Integer statsTeamGoalConc(Integer id) throws SQLException {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countGoalConc = COUNT;
        for (Game g : gameSet
        ) {
            countGoalConc += g.getGoals_conceded();
        }
        return countGoalConc;
    }

    @Override
    public Integer statsTeamYellowCard(Integer id) throws SQLException {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countYellowCard = COUNT;
        for (Game g : gameSet) {
            countYellowCard += g.getYellow_card_score();
        }
        return countYellowCard;
    }

    @Override
    public Integer statsTeamRedCard(Integer id) throws SQLException {
        Set<Game> gameSet = gameService.showAllGameTeamInfo(id);
        int countRedCard = COUNT;
        for (Game g : gameSet
        ) {
            countRedCard += g.getRed_card_score();
        }
        return countRedCard;
    }
}
