package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.GoalScore;
import project.repository.GoalScoreRepository;
import project.service.interfaces.GoalScoreService;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class GoalScoreServiceImpl implements GoalScoreService {
    @Autowired
    private GoalScoreRepository goalScoreRepository;

    @Override
    public List<GoalScore> showAllGoalInfo() {
        List<GoalScore> goalScores = goalScoreRepository.findAll();
        return goalScores;
    }
}
