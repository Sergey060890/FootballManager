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
    Result createResult(Team team, String opponentTeam) ;

    Set<Result> showAllResultTeamInfo(Integer id);

    void deleteResult(Integer id) ;

    void updateResult(Integer id,
                      String opponentTeam);

    Result findResultById(Integer id);

    List<ResultDTO> findAll();

}
