package project.service.interfaces;



import project.models.GoalScore;

import java.util.List;

public interface GoalScoreService {
    /**
     * Show all goal player
     */
    List<GoalScore> showAllGoalPlayer(Integer id);

    /**
     * Delete goal score
     */
    void deleteGoalScore(Integer id);
}
