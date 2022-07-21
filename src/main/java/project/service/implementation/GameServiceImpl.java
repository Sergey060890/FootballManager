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
import project.repository.RedCardRepository;
import project.repository.SubstitutionRepository;
import project.repository.YellowCardRepository;
import project.service.dto.GameDTO;
import project.service.dto.mapper.GameMapper;
import project.service.interfaces.GameService;
import project.service.interfaces.TeamService;
import project.service.random.RandomResult;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static project.service.implementation.ConstansImpl.*;


@Service
@Transactional
public class GameServiceImpl implements GameService {
    /**
     * Spring dependency injection autocomplete
     */
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

    @Autowired
    private TeamService teamService;

    private RandomResult random = new RandomResult();

    /**
     * Method createGame
     */
    @Override
    public Game createGame(Team team,
                           String opponentTeam, Set<Player> players) {
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

    /**
     * Method findAll game
     */
    @Override
    public List<GameDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameMapper::mapFrom)
                .collect(Collectors.toList());
    }

    /**
     * Method showAllGameTeamInfo
     */
    @Override
    public Set<Game> showAllGameTeamInfo(Integer id) {
        Set<Game> games = new HashSet<>();
        for (Game game : gameRepository.findAll()
        ) {
            if (Objects.equals(game.getTeamGame().getTeam_id(), id)) {
                games.add(game);
            }
        }
        return games;
    }

    /**
     * Method goalkeeperСheck
     */
    @Override
    public Integer goalkeeperСheck(Set<Player> playersGo) {
        int count = 0;
        for (Player p : playersGo
        ) {
            if (p.getPosition().equals(GK)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method startGamePlayer
     */
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

    /**
     * Method noStartGamePlayer
     */
    @Override
    public Set<Player> noStartGamePlayer(Integer id, String[] players) {
        Set<Player> playerSet = teamService.showAllPlayerTeamInfo(id);
        Set<String> mySet = new HashSet<>(Arrays.asList(players));
        Set<Player> playersNoGo = new HashSet<>();
        for (Player player : playerSet
        ) {
            if (mySet.contains(player.getPlayer_surname())) {
                continue;
            } else {
                playersNoGo.add(player);
            }
        }
        return playersNoGo;
    }

    /**
     * Method countAttendance
     */
    @Override
    public Integer countAttendance() {
        Integer goal = random.attendance();
        return goal;
    }

    /**
     * Method refereeGame
     */
    @Override
    public String refereeGame() {
        String referee = random.referee();
        return referee;
    }

    /**
     * Method createGoalScore
     */
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

    /**
     * Method createGoalConceded
     */
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

    /**
     * Method createYellowCard
     */
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

    /**
     * Method createRedCard
     */
    @Override
    public RedCard createRedCard(Game game, Set<Player> players) {
        RedCard rc = RedCard.builder()
                .game(game)
                .player(random.createNoGk(players))
                .card_time(random.timeRandomRC())
                .build();
        redCardRepository.save(rc);
        return rc;
    }

    /**
     * Method createSubstitution
     */
    @Override
    public Substitution createSubs(Game game, Set<Player> playersIn, Set<Player> playersOut) {
        Substitution subs = Substitution.builder()
                .game(game)
                .playerIn(random.createNoGk(playersIn))
                .playerOut(random.createNoGk(playersOut))
                .subs_time(random.timeRandomSubs())
                .build();
        substitutionRepository.save(subs);
        return subs;
    }

    /**
     * Method showGameAndStats
     */
    @Override
    public Map<Integer, Map<String, Integer>> showGameAndStats(GameService service, Game game, Set<Player> start, Set<Player> noStart) {
        Set<Player> playersStart = new HashSet<>(start);
        Set<Player> playersNoStart = new HashSet<>(noStart);
        if (game.getRed_card_score() != INT) {
            for (int i = INT1; i <= game.getRed_card_score(); i++) {
                RedCard redCard = service.createRedCard(game, playersStart);
                playersStart.remove(redCard.getPlayer());
            }
        }

        if (game.getGoals_conceded() != INT) {
            for (int i = INT1; i <= game.getGoals_conceded(); i++) {
                service.createGoalConceded(game, playersStart);
            }
        }

        for (int i = 1; i <= random.countSubs(); i++) {
            Substitution subs = service.createSubs(game, playersNoStart, playersStart);
            playersStart.remove(subs.getPlayerOut());
            playersNoStart.remove(subs.getPlayerIn());
        }

        if (game.getGoal_score() != INT) {
            for (int i = INT1; i <= game.getGoal_score(); i++) {
                service.createGoalScore(game, playersStart);
            }
        }

        if (game.getYellow_card_score() != INT) {
            for (int i = INT1; i <= game.getYellow_card_score(); i++) {
                YellowCard yc = service.createYellowCard(game, playersStart);
                playersStart.remove(yc.getPlayer());
            }
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
            if (Objects.equals(goals.getGame().getGame_id(), game.getGame_id())) {
                timeMoment.add(goals.getGoal_time());
                goalAdd.add(goals);
            }
        }

        for (GoalConceded goalConceded : goalConc
        ) {
            if (Objects.equals(goalConceded.getGame().getGame_id(), game.getGame_id())) {
                timeMoment.add(goalConceded.getConceded_time());
                goalConcAdd.add(goalConceded);
            }
        }

        for (YellowCard yc : yellow
        ) {
            if (Objects.equals(yc.getGame().getGame_id(), game.getGame_id())) {
                timeMoment.add(yc.getCard_time());
                yellowAdd.add(yc);
            }
        }

        for (RedCard rc : red
        ) {
            if (Objects.equals(rc.getGame().getGame_id(), game.getGame_id())) {
                timeMoment.add(rc.getCard_time());
                redAdd.add(rc);
            }
        }

        for (Substitution subst : subs
        ) {
            if (Objects.equals(subst.getGame().getGame_id(), game.getGame_id())) {
                timeMoment.add(subst.getSubs_time());
                subsAdd.add(subst);
            }
        }

        final TreeSet<Integer> ts = new TreeSet(timeMoment);

        int countGoal = INT;
        int countConc = INT;

        Map<Integer, Map<String, Integer>> listInfo = new TreeMap<>();

        for (int counter : ts) {
            for (GoalScore goalScore : goalAdd
            ) {
                if (goalScore.getGoal_time() == counter) {
                    countGoal++;
                    Map<String, Integer> map = new HashMap<>();
                    map.put(GOOOOAAAAL
                            + goalScore.getPlayer().getPlayer_surname() + STRING1 +
                            countGoal + STRING + countConc, INT1);
                    listInfo.put(goalScore.getGoal_time(), map);
                }
            }

            for (GoalConceded gc : goalConcAdd
            ) {
                if (gc.getConceded_time() == counter) {
                    countConc++;
                    Map<String, Integer> map = new HashMap<>();
                    map.put(GOAL_MISSED
                            + countGoal + STRING + countConc, INT2);
                    listInfo.put(gc.getConceded_time(), map);
                }
            }

            for (YellowCard yellowCard : yellowAdd
            ) {
                if (yellowCard.getCard_time() == counter) {
                    Map<String, Integer> map = new HashMap<>();
                    map.put(YELLOW_CARD
                            + yellowCard.getPlayer().getPlayer_surname(), INT3);
                    listInfo.put(yellowCard.getCard_time(), map);
                }
            }

            for (RedCard redCard : redAdd
            ) {
                if (redCard.getCard_time() == counter) {
                    Map<String, Integer> map = new HashMap<>();
                    map.put(RED_CARD
                            + redCard.getPlayer().getPlayer_surname(), INT4);
                    listInfo.put(redCard.getCard_time(), map);
                }
            }

            for (Substitution substitution : subsAdd
            ) {
                if (substitution.getSubs_time() == counter) {
                    Map<String, Integer> map = new HashMap<>();
                    map.put(SUBSTITUTION_OUT
                            + substitution.getPlayerOut().getPlayer_surname() +
                            IN + substitution.getPlayerIn().getPlayer_surname(), INT5);
                    listInfo.put(substitution.getSubs_time(), map);
                }
            }
        }
        return listInfo;
    }

    /**
     * Method delete game
     */
    @Override
    public void deleteGame(Integer id) {
        Game game = gameRepository.findById(id).orElseThrow();
        gameRepository.delete(game);
    }

}