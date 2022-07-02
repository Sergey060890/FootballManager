package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import project.models.Player;
import project.models.Team;
import project.repository.PlayerRepository;
import project.service.dto.PlayerDTO;
import project.service.dto.mapper.PlayerMapper;
import project.service.interfaces.PlayerService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SERGEY060890
 * @version 1.0
 * @create 2022-05-03 13:17
 */
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

}
