package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import project.models.GoalConceded;
import project.repository.GoalConcededRepository;
import project.service.interfaces.GoalConcededService;

import java.util.List;

public class GoalConcededServiceImpl implements GoalConcededService {
    @Autowired
    private GoalConcededRepository goalConcededRepository;

    @Override
    public List<GoalConceded> showAllGoalConcededInfo() {
        List<GoalConceded> goalConcededs = goalConcededRepository.findAll();
        return goalConcededs;
    }
}
