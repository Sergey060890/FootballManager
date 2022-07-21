package project.service.interfaces;



import project.models.RedCard;

import java.util.List;

public interface RedCardService {
    /**
     * Show all red card player
     */
    List<RedCard> showAllRedCardPlayer(Integer id);

    /**
     * Delete red card
     */
    void deleteRedCard(Integer id);
}
