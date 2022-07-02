package project.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDTO implements Serializable {
    private Integer player_id;
    private String player_name;
    private String player_surname;
    private String country;
    private Integer age;
    private String position;
}
