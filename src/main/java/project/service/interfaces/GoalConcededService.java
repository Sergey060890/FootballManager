package project.service.interfaces;


import project.models.GoalConceded;

import java.util.List;

public interface GoalConcededService {
    /**
     * Show all goal conceded player
     */
    List<GoalConceded> showAllGoalConcededPlayer(Integer id);

    /**
     * Delete goal conceded
     */
    void deleteGoalConceded(Integer id);
}
