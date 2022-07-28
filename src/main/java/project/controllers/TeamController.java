package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.dto.TeamDTO;
import project.service.interfaces.GameService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.ResultService;
import project.service.interfaces.TeamService;

import static project.controllers.Constants.*;


@Controller
public class TeamController {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ResultService resultService;

    /**
     * Home page
     */
    @GetMapping("/")
    public String home(Model model) {
        return HOME;
    }

    /**
     * All teams
     */
    @RequestMapping("/teams")
    public String teamMain(Model model) {
        model.addAttribute(TEAMS, teamService.findAll());
        return TEAM_MAIN;
    }

    /**
     * Add team
     */
    @RequestMapping("/teams/add")
    public String teamAdd(Model model) {
        return TEAM_ADD;
    }

    /**
     * Add team (method post)
     */
    @PostMapping("/teams/add")
    public String teamPostAdd(@RequestParam String name,
                              @RequestParam String city,
                              @RequestParam String country,
                              String stadium, String coach, Model model) {
        teamService.createTeam(name, city, country, stadium, coach);
        return REDIRECT_TEAM;
    }

    /**
     * Team Information
     */
    @GetMapping("/teams/{team_id}")
    public String teamDetails(@PathVariable(value = "team_id") Integer id, Model model) {
        TeamDTO team = teamService.findTeamById(id);
        model.addAttribute(TEAM, team);
        return TEAM_DETAILS;
    }

    /**
     * Edit team
     */
    @GetMapping("/teams/{team_id}/edit")
    public String teamEdit(@PathVariable(value = "team_id") Integer id, Model model) {
        TeamDTO team = teamService.findTeamById(id);
        model.addAttribute(TEAM, team);
        return TEAM_EDIT;
    }

    /**
     * Edit team (method post)
     */
    @PostMapping("/teams/{team_id}/edit")
    public String teamPostUpdate(@PathVariable(value = "team_id") Integer id,
                                 @RequestParam String name,
                                 @RequestParam String city,
                                 @RequestParam String country,
                                 String stadium, String coach, Model model) {
        teamService.updateTeam(id, name, city, country, stadium, coach);
        return REDIRECT_TEAM_TEAM_ID;
    }

    /**
     * Delete team
     */
    @PostMapping("/teams/{team_id}/remove")//delete
    public String teamPostDelete(@PathVariable(value = "team_id") Integer id, Model model) {
        teamService.deleteAllGameTeam(gameService, id);
        teamService.deleteAllResultTeam(resultService, id);
        teamService.deleteAllPlayerTeam(teamService, playerService, id);
        teamService.deleteTeam(id);
        return REDIRECT_TEAM;
    }
}
