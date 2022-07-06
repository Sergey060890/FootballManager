package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.interfaces.GameService;
import project.service.interfaces.ResultService;
import project.service.interfaces.TeamService;

@Controller
public class GameController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GameService gameService;

    @RequestMapping ("/team/{team_id}/games")//вывод всех матчей команды
    public String teamGames(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("games", resultService.showAllResultTeamInfo(id));
        model.addAttribute("teamId", id);
        return "team-games";
    }

    @GetMapping("/team/{team_id}/games/createGame")//создание игры
    public String addGame(@PathVariable(value = "team_id") Integer id, Model model) {
        model.addAttribute("teamId", id);
        return "game-create";
    }

    @PostMapping("/team/{team_id}/games/createGame")//добавление игры (получение из формы)
    public String gamePostAdd(@PathVariable(value = "team_id") Integer id, @RequestParam String opponent,
                              Model model) {
        resultService.createResult(teamService.findTeamId(id), opponent);
        return "redirect:/team/{team_id}/games";
    }

    @GetMapping("/team/{team_id}/games/{id}/edit")//edit game
    public String gameEdit(@PathVariable(value = "team_id") Integer idTeam, @PathVariable(value = "id") Integer idGame, Model model) {
        model.addAttribute("game", resultService.findResultById(idGame));
        model.addAttribute("teamId", idTeam);
        return "game-edit";
    }

    @PostMapping("/team/{team_id}/games/{id}/edit")//edit game(получение из формы)
    public String gamePostUpdate(@PathVariable(value = "team_id") Integer idTeam, @PathVariable(value = "id") Integer idGame,
                                 @RequestParam String opponent,
                                 Model model) {
        resultService.updateResult(idGame, opponent);
        return "redirect:/team/{team_id}/games";
    }

    @PostMapping("/team/{team_id}/games/{id}/remove")//delete game
    public String gamePostDelete(@PathVariable(value = "team_id") Integer idTeam, @PathVariable(value = "id") Integer idGame, Model model) {
        resultService.deleteResult(idGame);
        model.addAttribute("teamId", idTeam);
        return "redirect:/team/{team_id}/games";
    }

    @GetMapping("/team/{team_id}/games/{id}/play")//play game
    public String gamePlay(@PathVariable(value = "team_id") Integer idTeam, @PathVariable(value = "id") Integer idGame, Model model) {
        model.addAttribute("game", resultService.findResultById(idGame));
        model.addAttribute("teamId", idTeam);
        model.addAttribute("attendance",gameService.countAttendance());
        model.addAttribute("referee", gameService.refereeGame());
        return "game-play";
    }

    public static void main(String[] args) {

    }
}
