package project.service.interfaces;

import project.models.YellowCard;

import java.util.List;

public interface YellowCardService {

    public List<YellowCard> showAllYellowCardPlayer(Integer id);

    public void deleteYellowCard(Integer id);
}
