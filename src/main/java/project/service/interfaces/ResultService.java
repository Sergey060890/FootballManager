package project.service.interfaces;



import project.models.Result;
import project.models.Team;
import project.service.dto.ResultDTO;
import java.util.List;
import java.util.Set;

public interface ResultService {
    /**
     * Create result
     */
    Result createResult(Team team, String opponentTeam);

    /**
     * Show all result team
     */
    Set<Result> showAllResultTeamInfo(Integer id);

    /**
     * Delete result
     */
    void deleteResult(Integer id);

    /**
     * Update result
     */
    void updateResult(Integer id,
                      String opponentTeam);

    /**
     * Find result by id
     */
    Result findResultById(Integer id);

    /**
     * Find all result
     */
    List<ResultDTO> findAll();

}
