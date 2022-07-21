package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.RedCard;
import project.repository.RedCardRepository;
import project.service.interfaces.RedCardService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RedCardServiceImpl implements RedCardService {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    RedCardRepository redCardRepository;

    /**
     * Show all red card player
     */
    @Override
    public List<RedCard> showAllRedCardPlayer(Integer id) {//id игрока
        List<RedCard> redCards = new ArrayList<>();
        for (RedCard redCard : redCardRepository.findAll()
        ) {
            if (Objects.equals(redCard.getPlayer().getPlayer_id(), id)) {
                redCards.add(redCard);
            }
        }
        return redCards;
    }

    /**
     * Delete red card
     */
    @Override
    public void deleteRedCard(Integer id) {
        RedCard redCard = redCardRepository.findById(id).orElseThrow();
        redCardRepository.delete(redCard);
    }
}
