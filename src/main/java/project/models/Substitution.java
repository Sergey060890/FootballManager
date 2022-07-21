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
 * Class Substitution
 */
@Getter
@Setter
@Entity
@Table(name = "substitution")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Substitution implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Substitution entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "substitution_id")
    private Integer substitution_id;

    @Column(name = "subs_time")
    private Integer subs_time;

    /**
     * Connection with table "game"
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id_number")
    private Game game;

    /**
     * Connection with table "player-information"
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id_in")
    private Player playerIn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id_out")
    private Player playerOut;

    /**
     * Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Substitution substitution = (Substitution) o;
        return substitution_id != null
                && Objects.equals(substitution_id, substitution.substitution_id);
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
