package project.service.dto.mapper;


import project.models.Player;
import project.service.dto.PlayerDTO;

/**
 * PlayerMapper
 */
public class PlayerMapper {
    public static PlayerDTO mapFrom(Player player) {
        return new PlayerDTO(player.getPlayer_id(), player.getPlayer_name(),
                player.getPlayer_surname(), player.getCountry(), player.getAge(),
                player.getPosition());
    }
}
