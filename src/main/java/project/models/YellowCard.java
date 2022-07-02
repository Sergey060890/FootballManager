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
 * Class YellowCard
 */
@Getter
@Setter
@Entity
@Table(name = "yellow_card_score")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class YellowCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yellow_card_id")
    private Integer yellow_card_id;

    @Column(name = "card_time")
    private Integer card_time;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id_number")
    private Game game;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id")
    private Player player;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        YellowCard yellowCard = (YellowCard) o;
        return yellow_card_id != null
                && Objects.equals(yellow_card_id, yellowCard.yellow_card_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

