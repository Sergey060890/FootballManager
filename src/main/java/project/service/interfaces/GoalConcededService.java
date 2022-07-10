package project.service.interfaces;


import project.models.GoalConceded;

import java.util.List;

public interface GoalConcededService {
    List<GoalConceded> showAllGoalConcededPlayer(Integer id);

    public void deleteGoalConceded(Integer id);
}
