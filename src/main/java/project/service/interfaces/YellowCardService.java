package project.service.interfaces;

import project.models.YellowCard;

import java.util.List;

public interface YellowCardService {
    /**
     * Show all yellow card player
     */
    List<YellowCard> showAllYellowCardPlayer(Integer id);

    /**
     * Delete yellow card
     */
    void deleteYellowCard(Integer id);
}
