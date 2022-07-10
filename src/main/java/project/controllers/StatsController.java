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

@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @RequestMapping("/team/{team_id}/statistics")//stats Team
    public String teamStats(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("team", teamService.findTeamId(id));
        model.addAttribute("teamId", id);
        model.addAttribute("countGame", statsService.statsTeamCountGame(id));
        model.addAttribute("countWinGame", statsService.statsTeamWinGame(id));
        model.addAttribute("countDrawGame", statsService.statsTeamDrawGame(id));
        model.addAttribute("countLoseGame", statsService.statsTeamLoseGame(id));
        model.addAttribute("countGoalScore", statsService.statsTeamGoalScore(id));
        model.addAttribute("countGoalConceded", statsService.statsTeamGoalConc(id));
        model.addAttribute("countYellowCard", statsService.statsTeamYellowCard(id));
        model.addAttribute("countRedCard", statsService.statsTeamRedCard(id));
        model.addAttribute("win", statsService.statsTeamWinPercent(id));
        model.addAttribute("draw", statsService.statsTeamDrawPercent(id));
        model.addAttribute("lose", statsService.statsTeamLosePercent(id));
        return "team-statistics";
    }

    @GetMapping("/team/{team_id}/statistics/learnMore")//подробнее
    public String teamStatsLearn(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("teamId", id);
        model.addAttribute("games", gameService.showAllGameTeamInfo(id));
        return "team-statistics-learn";
    }

    @GetMapping("/team/{team_id}/players/{player_id}/info/stats")//edit player
    public String playerStats(@PathVariable(value = "team_id") Integer idTeam,
                              @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute("player", playerService.findPlayerById(idPlayer));
        model.addAttribute("teamId", idTeam);
        model.addAttribute("countGamePlayer", statsService.statsPlayerCountAllGame(idPlayer));
        model.addAttribute("countStartGamePlayer", statsService.statsPlayerCountStartGame(idPlayer));
        model.addAttribute("countGoalPlayer", statsService.statsPlayerAllGoal(idPlayer));
        model.addAttribute("countGoalConcededPlayer", statsService.statsGoalkeeperConcededGoal(idPlayer));
        model.addAttribute("countYellowCardPlayer", statsService.statsPlayerYellowCard(idPlayer));
        model.addAttribute("countRedCardPlayer", statsService.statsPlayerRedCard(idPlayer));
        model.addAttribute("start",statsService.statsPlayerStartPercent(idPlayer));
        return "player-stats";
    }

}
