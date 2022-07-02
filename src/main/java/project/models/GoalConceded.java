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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class GoalConceded
 */
@Getter
@Setter
@Entity
@Table(name = "goal_conceded")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GoalConceded implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_conceded_id")
    private Integer goal_conceded_id;

    @Column(name = "conceded_time")
    private Integer conceded_time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id_number")
    private Game game;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "goalkeeper_id")
    private Player player;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        GoalConceded goalConceded = (GoalConceded) o;
        return goal_conceded_id != null &&
                Objects.equals(goal_conceded_id, goalConceded.goal_conceded_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
