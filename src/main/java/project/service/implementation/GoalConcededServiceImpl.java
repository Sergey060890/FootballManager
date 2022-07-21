package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.GoalConceded;
import project.repository.GoalConcededRepository;
import project.service.interfaces.GoalConcededService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GoalConcededServiceImpl implements GoalConcededService {
    /**
     * Spring dependency injection autocomplete
     */
    @Autowired
    private GoalConcededRepository goalConcededRepository;

    /**
     * ShowAllGoalConcededPlayer
     */
    @Override
    public List<GoalConceded> showAllGoalConcededPlayer(Integer id) {//id игрока
        List<GoalConceded> goalConcededs = new ArrayList<>();
        for (GoalConceded goalConceded : goalConcededRepository.findAll()
        ) {
            if (Objects.equals(goalConceded.getPlayer().getPlayer_id(), id)) {
                goalConcededs.add(goalConceded);
            }
        }
        return goalConcededs;
    }

    /**
     * DeleteGoalConceded
     */
    @Override
    public void deleteGoalConceded(Integer id) {
        GoalConceded goalConceded = goalConcededRepository.findById(id).orElseThrow();
        goalConcededRepository.delete(goalConceded);
    }
}
