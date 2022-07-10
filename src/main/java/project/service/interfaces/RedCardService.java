package project.service.interfaces;



import project.models.RedCard;

import java.util.List;

public interface RedCardService {

    List<RedCard> showAllRedCardPlayer(Integer id);

    void deleteRedCard(Integer id);
}
