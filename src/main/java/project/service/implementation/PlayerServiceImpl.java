package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Game;
import project.models.GoalConceded;
import project.models.GoalScore;
import project.models.Player;
import project.models.RedCard;
import project.models.Result;
import project.models.Substitution;
import project.models.Team;
import project.models.YellowCard;
import project.repository.PlayerRepository;
import project.service.dto.PlayerDTO;
import project.service.dto.mapper.PlayerMapper;
import project.service.interfaces.GameService;
import project.service.interfaces.GoalConcededService;
import project.service.interfaces.GoalScoreService;
import project.service.interfaces.PlayerService;
import project.service.interfaces.RedCardService;
import project.service.interfaces.ResultService;
import project.service.interfaces.SubsService;
import project.service.interfaces.YellowCardService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author SERGEY060890
 * @version 1.0
 * @create 2022-05-03 13:17
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player createPlayer(String name, String surname,
                               String country, Integer age,
                               String position) {
        Player player = Player.builder()
                .player_name(name)
                .player_surname(surname)
                .country(country)
                .age(age)
                .position(position)
                .build();
        playerRepository.save(player);
        return player;
    }

    @Override
    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Player addPlayerInTeam(Player player, Team team) {
        player.setTeamPlayer(team);
        playerRepository.save(player);
        return player;
    }

    @Override
    public void updateTeam(Player player, Team team) {
        player.setTeamPlayer(team);
        playerRepository.save(player);
    }

    @Override
    public void updatePlayer(Integer id, String playerName, String playerSurname,
                             String country, Integer age,
                             String position) {
        Player player = playerRepository.findById(id).orElseThrow();
        player.setPlayer_name(playerName);
        player.setPlayer_surname(playerSurname);
        player.setCountry(country);
        player.setAge(age);
        player.setPosition(position);
        playerRepository.save(player);
    }

    @Override
    public Player findPlayerById(Integer id) {
        return playerRepository.findById(id).orElseThrow();
    }

    @Override
    public void updatePosition(Player player, String position) {
        player.setPosition(position);
        playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow();
        playerRepository.delete(player);
    }

    @Override
    public void deleteAllGoalPlayer(GoalScoreService goalScoreService, Integer id) {
        for (GoalScore goalScore : goalScoreService.showAllGoalPlayer(id)
        ) {
            goalScoreService.deleteGoalScore(goalScore.getGoal_id());
        }
    }

    @Override
    public void deleteAllGoalConcededPlayer(GoalConcededService goalConcededService, Integer id) {
        for (GoalConceded goalConceded : goalConcededService.showAllGoalConcededPlayer(id)
        ) {
            goalConcededService.deleteGoalConceded(goalConceded.getGoal_conceded_id());
        }
    }

    @Override
    public void deleteAllYellowCardPlayer(YellowCardService yellowCardService, Integer id) {
        for (YellowCard yellowCard : yellowCardService.showAllYellowCardPlayer(id)
        ) {
            yellowCardService.deleteYellowCard(yellowCard.getYellow_card_id());
        }
    }

    @Override
    public void deleteAllRedCardPlayer(RedCardService redCardService, Integer id) {
        for (RedCard redCard : redCardService.showAllRedCardPlayer(id)
        ) {
            redCardService.deleteRedCard(redCard.getRed_card_id());
        }
    }

    @Override
    public void deleteAllSubsPlayer(SubsService subsService, Integer id) {
        for (Substitution subs : subsService.showAllSubsInPlayer(id)
        ) {
            subsService.deleteSubs(subs.getSubstitution_id());
        }

        for (Substitution subs : subsService.showAllSubsOutPlayer(id)
        ) {
            subsService.deleteSubs(subs.getSubstitution_id());
        }
    }

    @Override
    public void deleteAllGamePlayer(GameService gameService, Integer idTeam, Integer idPlayer) {
        for (Game game : gameService.showAllGameTeamInfo(idTeam)
        ) {
            game.getPlayers().removeIf(player -> Objects.equals(player.getPlayer_id(), idPlayer));
        }
    }

}
