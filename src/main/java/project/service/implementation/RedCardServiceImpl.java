package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import project.models.RedCard;
import project.repository.RedCardRepository;
import project.service.interfaces.RedCardService;

import java.util.List;

public class RedCardServiceImpl implements RedCardService {
    @Autowired
    RedCardRepository redCardRepository;


    @Override
    public List<RedCard> showAllRedCardInfo() {
        List<RedCard> redCards = redCardRepository.findAll();
        return redCards;
    }
}
