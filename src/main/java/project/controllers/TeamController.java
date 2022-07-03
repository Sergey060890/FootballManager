package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.models.Team;
import project.service.dto.TeamDTO;
import project.service.interfaces.TeamService;

import java.util.ArrayList;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/")//главная страница
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/team")//все записи
    public String teamMain(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "team-main";
    }

    @RequestMapping("/team/add")//добавление команды
    public String teamAdd(Model model) {
        return "team-add";
    }

    @PostMapping("/team/add")//добавление (получение из формы)
    public String teamPostAdd(@RequestParam String name, @RequestParam String city, @RequestParam String country,
                              String stadium, String coach, Model model) {
        teamService.createTeam(name, city, country, stadium, coach);
        return "redirect:/team";
    }

    @GetMapping("/team/{team_id}")//вывод одной записи
    public String teamDetails(@PathVariable(value = "team_id") Integer id, Model model) {
        TeamDTO team = teamService.findTeamById(id);
        model.addAttribute("team", team);
        return "team-details";
    }

    @GetMapping("/team/{team_id}/edit")//edit
    public String teamEdit(@PathVariable(value = "team_id") Integer id, Model model) {
        TeamDTO team = teamService.findTeamById(id);
        model.addAttribute("team", team);
        return "team-edit";
    }

}
