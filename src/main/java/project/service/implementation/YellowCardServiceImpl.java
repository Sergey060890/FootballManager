package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.YellowCard;
import project.repository.YellowCardRepository;
import project.service.interfaces.YellowCardService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class YellowCardServiceImpl implements YellowCardService {
    @Autowired
    private YellowCardRepository yellowCardRepository;

    @Override
    public List<YellowCard> showAllYellowCardInfo() {
        List<YellowCard> yellowCards = yellowCardRepository.findAll();
        return yellowCards;
    }
}
