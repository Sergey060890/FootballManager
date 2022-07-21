package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Game;
import project.models.Player;
import project.models.Result;
import project.models.Team;
import project.repository.PlayerRepository;
import project.repository.TeamRepository;
import project.service.dto.TeamDTO;
import project.service.dto.mapper.TeamMapper;
import project.service.interfaces.GameService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.ResultService;
import project.service.interfaces.TeamService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Create team
     */
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

    /**
     * Find all team
     */
    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }

    /**
     * Update team
     */
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

    /**
     * Delete team
     */
    @Override
    public void deleteTeam(Integer id) {
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.delete(team);
    }

    /**
     * Find all team (DTO)
     */
    @Override
    public TeamDTO findTeamById(Integer id) {
        return TeamMapper.mapFrom(teamRepository.findById(id).orElseThrow());
    }

    /**
     * Find all team
     */
    @Override
    public Team findTeamId(Integer id) {
        return teamRepository.findById(id).orElseThrow();
    }

    /**
     * Add player in team
     */
    @Override
    public Player addPlayerInTeam(Player player, Team team) {
        player.setTeamPlayer(team);
        playerRepository.save(player);
        return player;
    }

    /**
     * Show all player team
     */
    @Override
    public Set<Player> showAllPlayerTeamInfo(Integer id) {
        Set<Player> players = new HashSet<>();
        for (Player p : playerRepository.findAll()
        ) {
            if (Objects.equals(p.getTeamPlayer().getTeam_id(), id)) {
                players.add(p);
            }
        }
        return players;
    }

    /**
     * Delete all player team
     */
    @Override
    public void deleteAllPlayerTeam(TeamService teamService, PlayerService playerService, Integer id) {
        for (Player p : teamService.showAllPlayerTeamInfo(id)
        ) {
            playerService.deletePlayer(p.getPlayer_id());
        }
    }

    /**
     * Delete all game team
     */
    @Override
    public void deleteAllGameTeam(GameService gameService, Integer id) {
        for (Game game : gameService.showAllGameTeamInfo(id)
        ) {
            gameService.deleteGame(game.getGame_id());
        }
    }

    /**
     * Delete all result team
     */
    @Override
    public void deleteAllResultTeam(ResultService resultService, Integer id) {
        for (Result result : resultService.showAllResultTeamInfo(id)
        ) {
            resultService.deleteResult(result.getId());
        }
    }
}
