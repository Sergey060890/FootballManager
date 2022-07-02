package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import project.models.YellowCard;
import project.repository.YellowCardRepository;
import project.service.interfaces.YellowCardService;

import java.util.List;

public class YellowCardServiceImpl implements YellowCardService {
    @Autowired
    private YellowCardRepository yellowCardRepository;

    @Override
    public List<YellowCard> showAllYellowCardInfo() {
        List<YellowCard> yellowCards = yellowCardRepository.findAll();
        return yellowCards;
    }
}
