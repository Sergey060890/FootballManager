package project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Game
 */
@Getter
@Setter
@Entity
@Table(name = "game")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Game entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer game_id;

    @Column(name = "opponent_name")
    private String opponent_name;

    @Column(name = "result")
    private String result;

    @Column(name = "goal_score")
    private Integer goal_score;

    @Column(name = "goals_conceded")
    private Integer goals_conceded;

    @Column(name = "yellow_card_score")
    private Integer yellow_card_score;

    @Column(name = "red_card_score")
    private Integer red_card_score;

    /**
     * Connection with table "player_information"
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "game_player",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")}
    )
    @ToString.Exclude
    @Builder.Default
    private Set<Player> players = new HashSet<>();

    /**
     * Connection with table "team"
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id")
    private Team teamGame;

    /**
     * Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return game_id != null && Objects.equals(game_id, game.game_id);
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
