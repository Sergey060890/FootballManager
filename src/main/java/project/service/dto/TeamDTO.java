package project.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.models.Game;
import project.models.Player;

import java.io.Serializable;
import java.util.Set;

/**
 * TeamDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDTO implements Serializable {
    private Integer team_id;
    private String team_name;
    private String city;
    private String country;
    private String stadium;
    private String coach;
    private Set<Player> players;
    private Set<Game> games;
}
