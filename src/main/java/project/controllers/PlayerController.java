package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Player;
import project.service.interfaces.GameService;
import project.service.interfaces.GoalConcededService;
import project.service.interfaces.GoalScoreService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.RedCardService;
import project.service.interfaces.SubsService;
import project.service.interfaces.TeamService;
import project.service.interfaces.YellowCardService;


@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GoalScoreService goalScoreService;

    @Autowired
    private GoalConcededService goalConcededService;

    @Autowired
    private YellowCardService yellowCardService;

    @Autowired
    private RedCardService redCardService;

    @Autowired
    private SubsService subsService;

    @Autowired
    private GameService gameService;

    @RequestMapping("/team/{team_id}/players")//вывод всех игроков
    public String teamPlayers(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("players", teamService.showAllPlayerTeamInfo(id));
        model.addAttribute("teamId", id);
        return "team-players";
    }

    @GetMapping("/team/{team_id}/players/addPlayer")//добавление игрока
    public String addPlayer(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("teamId", id);
        return "player-add";
    }

    @PostMapping("/team/{team_id}/players/addPlayer")//добавление игры (получение из формы)
    public String playerPostAdd(@PathVariable(value = "team_id") Integer id, @RequestParam String name,
                                @RequestParam String surname, @RequestParam String country, @RequestParam Integer age,
                                @RequestParam String position,
                                Model model) {
        Player player = playerService.createPlayer(name, surname, country, age, position);
        teamService.addPlayerInTeam(player, teamService.findTeamId(id));
        model.addAttribute("teamId", id);
        return "redirect:/team/{team_id}/players";
    }

    @GetMapping("/team/{team_id}/players/{player_id}/info/edit")//edit player
    public String playerEdit(@PathVariable(value = "team_id") Integer idTeam,
                             @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute("player", playerService.findPlayerById(idPlayer));
        model.addAttribute("teamId", idTeam);
        return "player-edit";
    }

    @PostMapping("/team/{team_id}/players/{player_id}/info/edit")//edit game(получение из формы)
    public String playerPostEdit(@PathVariable(value = "team_id") Integer id,@PathVariable(value = "player_id") Integer idPlayer, @RequestParam String name,
                                 @RequestParam String surname, @RequestParam String country, @RequestParam Integer age,
                                 @RequestParam String position,
                                 Model model) {
        model.addAttribute("teamId", id);
        playerService.updatePlayer(idPlayer, name, surname, country, age, position);
        return "redirect:/team/{team_id}/players";
    }

    @PostMapping("/team/{team_id}/players/{player_id}/info/remove")//delete game
    public String gamePostDelete(@PathVariable(value = "team_id") Integer idTeam, @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        playerService.deleteAllGoalPlayer(goalScoreService, idPlayer);
        playerService.deleteAllGoalConcededPlayer(goalConcededService, idPlayer);
        playerService.deleteAllYellowCardPlayer(yellowCardService, idPlayer);
        playerService.deleteAllRedCardPlayer(redCardService, idPlayer);
        playerService.deleteAllSubsPlayer(subsService, idPlayer);
        playerService.deleteAllGamePlayer(gameService, idTeam, idPlayer);
        playerService.deletePlayer(idPlayer);
        model.addAttribute("teamId", idTeam);
        return "redirect:/team/{team_id}/players";
    }

    @GetMapping("/team/{team_id}/players/{player_id}/info")//player info
    public String playerInfo(@PathVariable(value = "team_id") Integer idTeam,
                             @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute("player", playerService.findPlayerById(idPlayer));
        model.addAttribute("teamId", idTeam);
        return "player-info";
    }

}
