package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import java.util.List;

import static project.controllers.Constants.*;


@Controller
public class PlayerController {
    /**
     * Spring dependency injection autocomplete
     */
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

    /**
     * All players
     */
    @RequestMapping("/team/{team_id}/players")
    public String teamPlayers(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(PLAYERS, teamService.showAllPlayerTeamInfo(id));
        model.addAttribute(TEAM_ID, id);
        return TEAM_PLAYERS;
    }

    /**
     * Add player
     */
    @GetMapping("/team/{team_id}/players/addPlayer")
    public String addPlayer(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(TEAM_ID, id);
        return PLAYER_ADD;
    }

    /**
     * Add player (method post)
     */
    @PostMapping("/team/{team_id}/players/addPlayer")//добавление игры (получение из формы)
    public String playerPostAdd(@PathVariable(value = "team_id") Integer id,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String country,
                                @RequestParam Integer age,
                                @RequestParam String position,
                                Model model) {
        Player player = playerService.createPlayer(name, surname, country, age, position);
        teamService.addPlayerInTeam(player, teamService.findTeamId(id));
        model.addAttribute(TEAM_ID, id);
        return REDIRECT_TEAM_TEAM_ID_PLAYERS;
    }

    /**
     * Edit player
     */
    @GetMapping("/team/{team_id}/players/{player_id}/info/edit")//edit player
    public String playerEdit(@PathVariable(value = "team_id") Integer idTeam,
                             @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute(PLAYER, playerService.findPlayerById(idPlayer));
        model.addAttribute(TEAM_ID, idTeam);
        return PLAYER_EDIT;
    }

    /**
     * Edit player (method post)
     */
    @PostMapping("/team/{team_id}/players/{player_id}/info/edit")//edit game(получение из формы)
    public String playerPostEdit(@PathVariable(value = "team_id") Integer id,
                                 @PathVariable(value = "player_id") Integer idPlayer,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String country,
                                 @RequestParam Integer age,
                                 @RequestParam String position,
                                 Model model) {
        model.addAttribute(TEAM_ID, id);
        playerService.updatePlayer(idPlayer, name, surname, country, age, position);
        return REDIRECT_TEAM_TEAM_ID_PLAYERS;
    }

    /**
     * Delete player
     */
    @PostMapping("/team/{team_id}/players/{player_id}/info/remove")//delete game
    public String gamePostDelete(@PathVariable(value = "team_id") Integer idTeam,
                                 @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        playerService.deleteAllGoalPlayer(goalScoreService, idPlayer);
        playerService.deleteAllGoalConcededPlayer(goalConcededService, idPlayer);
        playerService.deleteAllYellowCardPlayer(yellowCardService, idPlayer);
        playerService.deleteAllRedCardPlayer(redCardService, idPlayer);
        playerService.deleteAllSubsPlayer(subsService, idPlayer);
        playerService.deleteAllGamePlayer(gameService, idTeam, idPlayer);
        playerService.deletePlayer(idPlayer);
        model.addAttribute(TEAM_ID, idTeam);
        return REDIRECT_TEAM_TEAM_ID_PLAYERS;
    }

    /**
     * Player info
     */
    @GetMapping("/team/{team_id}/players/{player_id}/info")//player info
    public String playerInfo(@PathVariable(value = "team_id") Integer idTeam,
                             @PathVariable(value = "player_id") Integer idPlayer, Model model) {
        model.addAttribute(PLAYER, playerService.findPlayerById(idPlayer));
        model.addAttribute(TEAM_ID, idTeam);
        return PLAYER_INFO;
    }

}
