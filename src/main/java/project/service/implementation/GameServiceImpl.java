package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Game;
import project.models.GoalConceded;
import project.models.GoalScore;
import project.models.Player;
import project.models.RedCard;
import project.models.Substitution;
import project.models.Team;
import project.models.YellowCard;
import project.repository.GameRepository;
import project.repository.GoalConcededRepository;
import project.repository.GoalScoreRepository;
import project.repository.PlayerRepository;
import project.repository.RedCardRepository;
import project.repository.SubstitutionRepository;
import project.repository.YellowCardRepository;
import project.service.dto.GameDTO;
import project.service.dto.mapper.GameMapper;
import project.service.interfaces.GameService;
import project.service.interfaces.TeamService;
import project.service.random.RandomResult;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
@Service
@Transactional
public class GameServiceImpl implements GameService {
    public static final String RED_CARD = "' Red card! ";
    public static final String YELLOW_CARD = "' Yellow card! ";
    public static final String STRING = ":";
    public static final String GOAL_MISSED = "' Goal missed! ";
    public static final String GOOOOAAAAL = "' GOOOOAAAAL! ";
    public static final String STRING1 = " ";
    public static final String GK = "GK";
    public static final int INT = 0;
    public static final int INT1 = 1;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GoalScoreRepository goalScoreRepository;

    @Autowired
    private GoalConcededRepository goalConcededRepository;

    @Autowired
    private YellowCardRepository yellowCardRepository;

    @Autowired
    private RedCardRepository redCardRepository;

    @Autowired
    private SubstitutionRepository substitutionRepository;

    RandomResult random = new RandomResult();
    TeamService teamService = new TeamServiceImpl();



    @Override
    public Game createGame(Team team,
                           String opponentTeam, Set<Player> players) throws SQLException {
        int goalS = random.randomGoal();
        int goalC = random.randomGoal();
        Game game = Game.builder()
                .teamGame(team)
                .opponent_name(opponentTeam)
                .goal_score(goalS)
                .goals_conceded(goalC)
                .result(random.randomResult(goalS, goalC))
                .yellow_card_score(random.randomYellowCardScore())
                .red_card_score(random.randomRedCardScore())
                .players(players)
                .build();
        gameRepository.save(game);
        return game;
    }

    @Override
    public Game createGameNoPlayers(Team team,
                                    String opponentTeam) {
        Game game = Game.builder()
                .teamGame(team)
                .opponent_name(opponentTeam)
                .build();
        gameRepository.save(game);
        return game;
    }

    @Override
    public Game addPlayersInStartGame(Integer id, Set<Player> players) {
        Game game = gameRepository.findById(id).orElseThrow();
        game.setPlayers(players);
        gameRepository.save(game);
        return game;
    }



    @Override
    public List<GameDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, String> showAllOpponentTeamInfo(Set<Game> gameSet) {
        Map<Integer, String> map = new HashMap<>();
        for (Game g : gameSet
        ) {
            map.put(g.getGame_id(), g.getOpponent_name());
        }
        return map;
    }


    @Override
    public Map<Integer, String> opponentRemoveTeam(GameService service, Game game, Set<Game> gameSet) {
        Map<Integer, String> games =
                service.showAllOpponentTeamInfo(gameSet);
        for (Game g : gameSet
        ) {
            if (g.getOpponent_name() == game.getOpponent_name()) {
                games.remove(g.getGame_id());
            }
        }
        return games;
    }

    @Override
    public Set<Game> showAllGameTeamInfo(Integer id) {
        Set<Game> games = new HashSet<>();
        for (Game game : gameRepository.findAll()
        ) {
            if (game.getTeamGame().getTeam_id() == id) {
                games.add(game);
            }
        }
        return games;
    }

    @Override
    public Set<Player> showAllGamePlayerInfo(Integer id) {
        Game game = gameRepository.findById(id).orElseThrow();
        Set<Player> players = game.getPlayers();
        return players;
    }

    @Override
    public Set<Player> startGamePlayer(Integer id, String[] players) {
        Set<Player> playerSet = teamService.showAllPlayerTeamInfo(id);
        Set<String> mySet = new HashSet<>(Arrays.asList(players));
        Set<Player> playersGo = new HashSet<>();
        for (Player player : playerSet
        ) {
            for (String str : mySet
            ) {
                if (player.getPlayer_surname().equals(str)) {
                    playersGo.add(player);
                }
            }
        }
        return playersGo;
    }

    @Override
    public Integer goalkeeperСheck(Set<Player> playersGo)  {
        int count = 0;
        for (Player p : playersGo
        ) {
            if (p.getPosition().equals(GK)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Set<Player> noStartGamePlayer(Integer id, String[] players)  {
        Set<Player> playerSet = teamService.showAllPlayerTeamInfo(id);
        Set<String> mySet = new HashSet<>(Arrays.asList(players));
        Set<Player> playersNoGo = new HashSet<>();
        for (Player player : playerSet
        ) {
            for (String str : mySet
            ) {
                if (player.getPlayer_surname().equals(str)) {
                    continue;
                } else {
                    playersNoGo.add(player);
                }
            }
        }
        return playersNoGo;
    }

    @Override
    public GoalScore createGoalScore(Game game, Set<Player> players) {
        GoalScore goal = GoalScore.builder()
                .game(game)
                .player(random.createNoGk(players))
                .goal_time(random.timeRandomGoal())
                .build();
        goalScoreRepository.save(goal);
        return goal;
    }

    @Override
    public Integer countAttendance() {
        Integer goal = random.attendance();
        return goal;
    }

    @Override
    public String refereeGame() {
        String referee = random.referee();
        return referee;
    }

    @Override
    public GoalConceded createGoalConceded(Game game, Set<Player> players) {
        GoalConceded goalConc = GoalConceded.builder()
                .game(game)
                .player(random.createGoalConcPlayer(players))
                .conceded_time(random.timeRandomGoal())
                .build();
        goalConcededRepository.save(goalConc);
        return goalConc;
    }

    @Override
    public YellowCard createYellowCard(Game game, Set<Player> players) {
        YellowCard yc = YellowCard.builder()
                .game(game)
                .player(random.create(players))
                .card_time(random.timeRandomYC())
                .build();
        yellowCardRepository.save(yc);
        return yc;
    }

    @Override
    public RedCard createRedCard(Game game, Set<Player> players) {
        RedCard rc = RedCard.builder()
                .game(game)
                .player(random.create(players))
                .card_time(random.timeRandomRC())
                .build();
        redCardRepository.save(rc);
        return rc;
    }

    @Override
    public Substitution createSubs(Game game, Set<Player> playersIn, Set<Player> playersOut) {
        Substitution subs = Substitution.builder()
                .game(game)
                .playerIn(random.create(playersIn))
                .playerOut(random.create(playersOut))
                .subs_time(random.timeRandomSubs())
                .build();
        substitutionRepository.save(subs);
        return subs;
    }


    @Override
    public Set<Game> showAllPlayerGame(Game game) {
        Set<Game> games = new HashSet<>();
        games.add(game);
        System.out.println(game.getPlayers());
        return games;
    }

    @Override
    public List<String> showGameAndStats(GameService service, Game game, Set<Player> start, Set<Player> noStart) {
        if (game.getGoals_conceded() != INT) {
            for (int i = INT1; i <= game.getGoals_conceded(); i++) {

                service.createGoalConceded(game, start);
            }
        }

        if (game.getGoal_score() != INT) {
            for (int i = INT1; i <= game.getGoal_score(); i++) {
                service.createGoalScore(game, start);
            }
        }

        if (game.getYellow_card_score() != INT) {
            for (int i = INT1; i <= game.getYellow_card_score(); i++) {
                service.createYellowCard(game, start);
            }
        }

        if (game.getRed_card_score() != INT) {
            for (int i = INT1; i <= game.getRed_card_score(); i++) {
                service.createYellowCard(game, start);
            }
        }


        for (int i = 1; i <= random.countSubs(); i++) {
            service.createSubs(game, start, noStart);
        }

        List<GoalScore> goal = goalScoreRepository.findAll();
        List<GoalConceded> goalConc = goalConcededRepository.findAll();
        List<YellowCard> yellow = yellowCardRepository.findAll();
        List<RedCard> red = redCardRepository.findAll();
        List<Substitution> subs = substitutionRepository.findAll();

        List<GoalScore> goalAdd = new ArrayList<>();
        List<GoalConceded> goalConcAdd = new ArrayList<>();
        List<YellowCard> yellowAdd = new ArrayList<>();
        List<RedCard> redAdd = new ArrayList<>();
        List<Substitution> subsAdd = new ArrayList<>();


        Set<Integer> timeMoment = new HashSet<>();
        for (GoalScore goals : goal
        ) {
            if (goals.getGame().getGame_id() == game.getGame_id()) {
                timeMoment.add(goals.getGoal_time());
                goalAdd.add(goals);
            }
        }

        for (GoalConceded goalConceded : goalConc
        ) {
            if (goalConceded.getGame().getGame_id() == game.getGame_id()) {
                timeMoment.add(goalConceded.getConceded_time());
                goalConcAdd.add(goalConceded);
            }
        }

        for (YellowCard yc : yellow
        ) {
            if (yc.getGame().getGame_id() == game.getGame_id()) {
                timeMoment.add(yc.getCard_time());
                yellowAdd.add(yc);
            }
        }

        for (RedCard rc : red
        ) {
            if (rc.getGame().getGame_id() == game.getGame_id()) {
                timeMoment.add(rc.getCard_time());
                redAdd.add(rc);
            }
        }

        for (Substitution subst : subs
        ) {
            if (subst.getGame().getGame_id() == game.getGame_id()) {
                timeMoment.add(subst.getSubs_time());
                subsAdd.add(subst);
            }
        }


        final TreeSet<Integer> ts = new TreeSet(timeMoment);
        ts.comparator();

        int countGoal = INT;
        int countConc = INT;

        List<String> listInfo = new ArrayList<>();

        for (int counter : ts) {
            for (GoalScore goalScore : goalAdd
            ) {
                if (goalScore.getGoal_time() == counter) {
                    countGoal++;
                    listInfo.add(goalScore.getGoal_time() + GOOOOAAAAL
                            + goalScore.getPlayer().getPlayer_surname() + STRING1 +
                            countGoal + STRING + countConc);
                }
            }

            for (GoalConceded gc : goalConcAdd
            ) {
                if (gc.getConceded_time() == counter) {
                    countConc++;
                    listInfo.add(gc.getConceded_time() + GOAL_MISSED
                            + countGoal + STRING + countConc);
                }
            }

            for (YellowCard yellowCard : yellowAdd
            ) {
                if (yellowCard.getCard_time() == counter) {
                    listInfo.add(yellowCard.getCard_time() + YELLOW_CARD
                            + yellowCard.getPlayer().getPlayer_surname());
                }
            }

//            for (RedCard redCard : redAdd
//            ) {
//                if (redCard.getCard_time() == counter) {
//                    listInfo.add(redCard.getCard_time() + RED_CARD
//                            + redCard.getPlayer().getPlayer_surname());
//                    start.remove(redCard.getPlayer());
//                }
//            }

//            for (Substitution substitution : subsAdd
//            ) {
//                if (substitution.getSubs_time() == counter) {
//                    listInfo.add(substitution.getSubs_time() + " Substitution! Out: "
//                            + substitution.getPlayerIn().getPlayer_surname() + " In:" + substitution.getPlayerOut().getPlayer_surname());
//                    start.remove(substitution.getPlayerOut());
//                    start.add(substitution.getPlayerIn());
//                }
//            }
        }
        return listInfo;
    }


    @Override
    public void deleteGame(Integer id) {
        Game game = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(game);
    }

    @Override
    public void updateGame(Integer id,
                           String opponentTeam) {
        Game game = gameRepository.findById(id).orElseThrow();
        game.setOpponent_name(opponentTeam);
        gameRepository.save(game);
    }

    @Override
    public Game findGameById(Integer id) {
        return gameRepository.findById(id).orElseThrow();
    }
}
