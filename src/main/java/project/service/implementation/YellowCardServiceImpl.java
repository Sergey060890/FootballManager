package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.GoalScore;
import project.models.YellowCard;
import project.repository.YellowCardRepository;
import project.service.interfaces.YellowCardService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class YellowCardServiceImpl implements YellowCardService {
    @Autowired
    private YellowCardRepository yellowCardRepository;

    @Override
    public List<YellowCard> showAllYellowCardPlayer(Integer id) {//id игрока
        List<YellowCard> yellowCards = new ArrayList<>();
        for (YellowCard yellowCard : yellowCardRepository.findAll()
        ) {
            if (Objects.equals(yellowCard.getPlayer().getPlayer_id(), id)) {
                yellowCards.add(yellowCard);
            }
        }
        return yellowCards;
    }

    @Override
    public void deleteYellowCard(Integer id) {
        YellowCard yellowCard = yellowCardRepository.findById(id).orElseThrow();
        yellowCardRepository.delete(yellowCard);
    }
}
