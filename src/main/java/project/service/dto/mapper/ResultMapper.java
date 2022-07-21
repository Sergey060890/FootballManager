package project.service.dto.mapper;


import project.models.Result;
import project.service.dto.ResultDTO;

/**
 * ResultMapper
 */
public class ResultMapper {
    public static ResultDTO mapFrom(Result result) {
        return new ResultDTO(result.getId(),
                result.getOpponent_name());
    }
}
