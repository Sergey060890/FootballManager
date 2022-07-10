package project.service.implementation;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.GoalScore;
import project.models.Result;
import project.repository.GoalScoreRepository;
import project.service.interfaces.GoalScoreService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GoalScoreServiceImpl implements GoalScoreService {
    @Autowired
    private GoalScoreRepository goalScoreRepository;

    @Override
    public List<GoalScore> showAllGoalPlayer(Integer id) {//id игрока
        List<GoalScore> goalScores = new ArrayList<>();
        for (GoalScore goalScore : goalScoreRepository.findAll()
        ) {
            if (Objects.equals(goalScore.getPlayer().getPlayer_id(), id)) {
                goalScores.add(goalScore);
            }
        }
        return goalScores;
    }

    @Override
    public void deleteGoalScore(Integer id) {
        GoalScore goalScore = goalScoreRepository.findById(id).orElseThrow();
        goalScoreRepository.delete(goalScore);
    }
}
