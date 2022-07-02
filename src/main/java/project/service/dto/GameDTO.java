package project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDTO implements Serializable {
    private Integer id;
    private String opponent_name;
    private String result;
    private Integer goal_score;
    private Integer goals_conceded;
    private Integer yellow_card_score;
    private Integer red_card_score;
}

