package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.interfaces.GameService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.StatsService;
import project.service.interfaces.TeamService;

import static project.controllers.Constants.*;

@Controller
public class StatsController {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private StatsService statsService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    /**
     * Team stats
     */
    @RequestMapping("/team/{team_id}/statistics")
    public String teamStats(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(TEAM, teamService.findTeamId(id));
        model.addAttribute(TEAM_ID, id);
        model.addAttribute(COUNT_GAME, statsService.statsTeamCountGame(id));
        model.addAttribute(COUNT_WIN_GAME, statsService.statsTeamWinGame(id));
        model.addAttribute(COUNT_DRAW_GAME, statsService.statsTeamDrawGame(id));
        model.addAttribute(COUNT_LOSE_GAME, statsService.statsTeamLoseGame(id));
        model.addAttribute(COUNT_GOAL_SCORE, statsService.statsTeamGoalScore(id));
        model.addAttribute(COUNT_GOAL_CONCEDED, statsService.statsTeamGoalConc(id));
        model.addAttribute(COUNT_YELLOW_CARD, statsService.statsTeamYellowCard(id));
        model.addAttribute(COUNT_RED_CARD, statsService.statsTeamRedCard(id));
        model.addAttribute(WIN, statsService.statsTeamWinPercent(id));
        model.addAttribute(DRAW, statsService.statsTeamDrawPercent(id));
        model.addAttribute(LOSE, statsService.statsTeamLosePercent(id));
        return TEAM_STATISTICS;
    }

    /**
     * Team stats (more)
     */
    @GetMapping("/team/{team_id}/statistics/learnMore")
    public String teamStatsLearn(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(TEAM_ID, id);
        model.addAttribute(GAMES, gameService.showAllGameTeamInfo(id));
        return TEAM_STATISTICS_LEARN;
    }

    /**
     * Player stats
     */
    @GetMapping("/team/{team_id}/players/{player_id}/info/stats")
    public String playerStats(@PathVariable(value = "team_id") Integer idTeam,
                              @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute(PLAYER, playerService.findPlayerById(idPlayer));
        model.addAttribute(TEAM_ID, idTeam);
        model.addAttribute(COUNT_GAME_PLAYER, statsService.statsPlayerCountAllGame(idPlayer));
        model.addAttribute(COUNT_START_GAME_PLAYER, statsService.statsPlayerCountStartGame(idPlayer));
        model.addAttribute(COUNT_GOAL_PLAYER, statsService.statsPlayerAllGoal(idPlayer));
        model.addAttribute(COUNT_GOAL_CONCEDED_PLAYER, statsService.statsGoalkeeperConcededGoal(idPlayer));
        model.addAttribute(COUNT_YELLOW_CARD_PLAYER, statsService.statsPlayerYellowCard(idPlayer));
        model.addAttribute(COUNT_RED_CARD_PLAYER, statsService.statsPlayerRedCard(idPlayer));
        model.addAttribute(START, statsService.statsPlayerStartPercent(idPlayer));
        return PLAYER_STATS;
    }

}
