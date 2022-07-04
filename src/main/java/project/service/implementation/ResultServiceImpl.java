package project.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Result;
import project.models.Team;
import project.repository.ResultRepository;
import project.service.dto.ResultDTO;
import project.service.dto.mapper.ResultMapper;
import project.service.interfaces.ResultService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Transactional
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result createResult(Team team, String opponentTeam) {
        Result result = Result.builder()
                .teamGame(team)
                .opponent_name(opponentTeam)
                .build();
        resultRepository.save(result);
        return result;
    }

    @Override
    public Set<Result> showAllResultTeamInfo(Integer id) {
        Set<Result> results = new HashSet<>();
        for (Result result : resultRepository.findAll()
        ) {
            if (result.getTeamGame().getTeam_id() == id) {
                results.add(result);
            }
        }
        return results;
    }

    @Override
    public void deleteResult(Integer id) {
        Result result = resultRepository.findById(id).orElseThrow();
        resultRepository.delete(result);
    }

    @Override
    public void updateResult(Integer id, String opponentTeam) {
        Result result = resultRepository.findById(id).orElseThrow();
        result.setOpponent_name(opponentTeam);
        resultRepository.save(result);
    }

    @Override
    public Result findResultById(Integer id) {
        return resultRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ResultDTO> findAll() {
        return resultRepository.findAll()
                .stream()
                .map(ResultMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
