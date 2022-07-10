package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.RedCard;
import project.models.YellowCard;
import project.repository.RedCardRepository;
import project.service.interfaces.RedCardService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RedCardServiceImpl implements RedCardService {
    @Autowired
    RedCardRepository redCardRepository;

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

    @Override
    public void deleteRedCard(Integer id) {
        RedCard redCard = redCardRepository.findById(id).orElseThrow();
        redCardRepository.delete(redCard);
    }
}
