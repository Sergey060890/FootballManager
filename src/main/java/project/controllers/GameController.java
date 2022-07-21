package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Game;
import project.models.Player;
import project.models.Result;
import project.models.Team;
import project.service.dto.PlayerDTO;
import project.service.dto.TeamDTO;
import project.service.implementation.PlayerServiceImpl;
import project.service.implementation.TeamServiceImpl;
import project.service.interfaces.GameService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.ResultService;
import project.service.interfaces.TeamService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static project.controllers.Constants.*;

@Controller
public class GameController {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private ResultService resultService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    /**
     * All games
     */
    @RequestMapping("/team/{team_id}/games")
    public String teamGames(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(GAMES, resultService.showAllResultTeamInfo(id));
        model.addAttribute(TEAM_ID, id);
        return TEAM_GAMES;
    }

    /**
     * Creating a game
     */
    @GetMapping("/team/{team_id}/games/createGame")
    public String addGame(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute(TEAM_ID, id);
        return GAME_CREATE;
    }

    /**
     * Creating a game (method post)
     */
    @PostMapping("/team/{team_id}/games/createGame")
    public String gamePostAdd(@PathVariable(value = "team_id") Integer id,
                              @RequestParam String opponent,
                              Model model) {
        resultService.createResult(teamService.findTeamId(id), opponent);
        return REDIRECT_TEAM_TEAM_ID_GAMES;
    }

    /**
     * Game change
     */
    @GetMapping("/team/{team_id}/games/{id}/edit")//edit game
    public String gameEdit(@PathVariable(value = "team_id") Integer idTeam,
                           @PathVariable(value = "id") Integer idGame, Model model) {
        model.addAttribute(GAME, resultService.findResultById(idGame));
        model.addAttribute(TEAM_ID, idTeam);
        return GAME_EDIT;
    }

    /**
     * Game change (method post)
     */
    @PostMapping("/team/{team_id}/games/{id}/edit")//edit game(получение из формы)
    public String gamePostUpdate(@PathVariable(value = "team_id") Integer idTeam,
                                 @PathVariable(value = "id") Integer idGame,
                                 @RequestParam String opponent,
                                 Model model) {
        resultService.updateResult(idGame, opponent);
        return "redirect:/team/{team_id}/games";
    }

    /**
     * Deleting a game
     */
    @PostMapping("/team/{team_id}/games/{id}/remove")
    public String gamePostDelete(@PathVariable(value = "team_id") Integer idTeam,
                                 @PathVariable(value = "id") Integer idGame, Model model) {
        resultService.deleteResult(idGame);
        model.addAttribute(TEAM_ID, idTeam);
        return REDIRECT_TEAM_TEAM_ID_GAMES;
    }

    /**
     * Play game
     */
    @GetMapping("/team/{team_id}/games/{id}/play")
    public String gamePlay(@PathVariable(value = "team_id") Integer idTeam,
                           @PathVariable(value = "id") Integer idGame, Model model) {
        model.addAttribute(GAME, resultService.findResultById(idGame));
        model.addAttribute(TEAM_ID, idTeam);
        model.addAttribute(ATTENDANCE, gameService.countAttendance());
        model.addAttribute(REFEREE, gameService.refereeGame());
        return GAME_PLAY;
    }

    /**
     * Start line-up game
     */
    @GetMapping("/team/{team_id}/games/{id}/play/startling")
    public String gameStartLineUpPlay(@PathVariable(value = "team_id") Integer idTeam,
                                      @PathVariable(value = "id") Integer idGame, Model model) {
        model.addAttribute(GAME, resultService.findResultById(idGame));
        model.addAttribute(TEAM_ID, idTeam);
        model.addAttribute(PLAYERS, teamService.showAllPlayerTeamInfo(idTeam));
        return GAME_PLAY_STARTLING;
    }

    /**
     * Start line-up game (method post) & Game show and stats
     */
    @PostMapping("/team/{team_id}/games/{id}/play/startling")
    public String gameStartPlay(@PathVariable(value = "team_id") Integer idTeam,
                                @PathVariable(value = "id") Integer idGame,
                                @RequestParam String[] player, Model model) {
        Result result = resultService.findResultById(idGame);
        if (player.length != INT2) {
            return TEAM_ID_GAMES_ID_PLAY_STARTLING;
        } else {
            Set<Player> playersGo = gameService.startGamePlayer(idTeam, player);
            Set<Player> playersNoGo = gameService.
                    noStartGamePlayer(idTeam, player);
            if (gameService.goalkeeperСheck(playersGo) == INT
                    || gameService.goalkeeperСheck(playersGo) > INT1) {
                return TEAM_ID_GAMES_ID_PLAY_STARTLING;
            } else {
                Game game = gameService.createGame(result.getTeamGame()
                        , result.getOpponent_name(), playersGo);
                Map<Integer, Map<String, Integer>> info = gameService.
                        showGameAndStats(gameService, game, playersGo, playersNoGo);
                model.addAttribute(TEAM_ID, idTeam);
                model.addAttribute(GAME_ID, idGame);
                model.addAttribute(PLAYERS, playersGo);
                model.addAttribute(GAME, game);
                model.addAttribute(INFO, info);
                resultService.deleteResult(idGame);
            }
        }
        return GAME_RESULT;
    }
}
