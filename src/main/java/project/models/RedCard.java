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
 * Class RedCard
 */
@Getter
@Setter
@Entity
@Table(name = "red_card_score")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RedCard implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * RedCard entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "red_card_id")
    private Integer red_card_id;

    @Column(name = "card_time")
    private Integer card_time;

    /**
     * Connection with table "game"
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id_number")
    private Game game;

    /**
     * Connection with table "player"
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id")
    private Player player;

    /**
     * Equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        RedCard redCard = (RedCard) o;
        return red_card_id != null
                && Objects.equals(red_card_id, redCard.red_card_id);
    }

    /**
     * HashCode
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}