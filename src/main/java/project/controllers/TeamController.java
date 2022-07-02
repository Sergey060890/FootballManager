package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.service.interfaces.TeamService;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/")//главная страница
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/team")//все записи
    public String horsesMain(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "team-main";
    }

    @RequestMapping("/team/add")//добавление команды
    public String blogAdd(Model model) {
        return "team-add";
    }

}
