package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Game;
import project.models.Player;
import project.models.Team;
import project.repository.PlayerRepository;
import project.repository.TeamRepository;
import project.service.dto.TeamDTO;
import project.service.dto.mapper.TeamMapper;
import project.service.interfaces.GameService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.TeamService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Team createTeam(String teamName, String teamCity,
                           String teamCountry, String teamStadium,
                           String teamCoach) {
        Team team = Team.builder()
                .team_name(teamName)
                .city(teamCity)
                .country(teamCountry)
                .stadium(teamStadium)
                .coach(teamCoach)
                .build();
        teamRepository.save(team);
        return team;
    }

//    @Override
//    public List<Team> showAllTeam() {
//       return enityDaoImplTeam.findAll();
//    }

//    @Override
//    public List<TeamDTO> findAll() {
//        return enityDaoImplTeam.findAll()
//                .stream()
//                .map(TeamMapper::mapFrom)
//                .collect(Collectors.toList());
//    }

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }


    @Override
    public void updateTeam(Integer id, String teamName, String teamCity,
                           String teamCountry, String teamStadium,
                           String teamCoach) {
        Team team = teamRepository.findById(id).orElseThrow();
        team.setTeam_name(teamName);
        team.setCity(teamCity);
        team.setCountry(teamCountry);
        team.setStadium(teamStadium);
        team.setCoach(teamCoach);
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Integer id)  {
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);
    }

    @Override
    public TeamDTO findTeamById(Integer id)  {
        return TeamMapper.mapFrom(teamRepository.findById(id).orElseThrow());
    }

    @Override
    public Team findTeamId(Integer id)  {
        Team team = teamRepository.findById(id).orElseThrow();
        return team;
    }

    @Override
    public Player addPlayerInTeam(Player player, Team team) {
        player.setTeamPlayer(team);
        playerRepository.save(player);
        return player;
    }

//    @Override
//    public void deletePlayerWithTeam(Player player) {
//        Team team = new Team();
//        team.setTeam_name("free agent");
//        player.setTeamPlayer(team);
//        enityDaoImplPlayer.update(player);
//    }

    @Override
    public Player showOnePlayerInfo(Integer id)  {
        Player player = playerRepository.findById(id).orElseThrow();
        return player;
    }

    @Override
    public Set<Player> showAllPlayerTeamInfo(Integer id) {
        Set<Player> players = new HashSet<>();
        List<Player> players1= playerRepository.findAll();
        for (Player p : playerRepository.findAll()
        ) {
            if (Objects.equals(p.getTeamPlayer().getTeam_id(), id)) {
                players.add(p);
            }
        }
        return players;
    }

    @Override
    public void deleteAllPlayerTeam(TeamService teamService, PlayerService playerService, Integer id) {
        for (Player p : teamService.showAllPlayerTeamInfo(id)
        ) {
            playerService.deletePlayer(p.getPlayer_id());
        }
    }

    @Override
    public void deleteAllGameTeam(GameService gameService, Integer id) {
        for (Game game : gameService.showAllGameTeamInfo(id)
        ) {
            gameService.deleteGame(game.getGame_id());
        }
    }
}
