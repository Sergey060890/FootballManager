package project.service.dto.mapper;


import project.models.Game;
import project.service.dto.GameDTO;
/**
 * GameMapper
 */
public class GameMapper {
    public static GameDTO mapFrom(Game game) {
        return new GameDTO(game.getGame_id(),
                game.getOpponent_name(), game.getResult(), game.getGoal_score(),
                game.getGoals_conceded(), game.getYellow_card_score(), game.getRed_card_score());
    }
}