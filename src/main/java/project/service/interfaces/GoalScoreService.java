package project.service.interfaces;



import project.models.GoalScore;

import java.util.List;

public interface GoalScoreService {
    List<GoalScore> showAllGoalPlayer(Integer id);

    void deleteGoalScore(Integer id);
}
