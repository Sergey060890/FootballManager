package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.GoalConceded;
import project.repository.GoalConcededRepository;
import project.service.interfaces.GoalConcededService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class GoalConcededServiceImpl implements GoalConcededService {
    @Autowired
    private GoalConcededRepository goalConcededRepository;

    @Override
    public List<GoalConceded> showAllGoalConcededInfo() {
        List<GoalConceded> goalConcededs = goalConcededRepository.findAll();
        return goalConcededs;
    }
}
