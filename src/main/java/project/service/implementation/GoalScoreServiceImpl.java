package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import project.models.GoalScore;
import project.repository.GoalScoreRepository;
import project.service.interfaces.GoalScoreService;

import java.util.List;

public class GoalScoreServiceImpl implements GoalScoreService {
    @Autowired
    private GoalScoreRepository goalScoreRepository;

    @Override
    public List<GoalScore> showAllGoalInfo() {
        List<GoalScore> goalScores = goalScoreRepository.findAll();
        return goalScores;
    }
}
