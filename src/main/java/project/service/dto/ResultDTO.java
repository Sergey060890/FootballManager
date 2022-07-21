package project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResultDTO
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResultDTO implements Serializable {
    private Integer id;
    private String opponent_name;
}
