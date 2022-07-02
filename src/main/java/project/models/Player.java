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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Player
 */
@Getter
@Setter
@Entity
@Table(name = "player_information")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer player_id;

    @Column(name = "player_name")
    private String player_name;

    @Column(name = "player_surname")
    private String player_surname;

    @Column(name = "country")
    private String country;

    @Column(name = "age")
    private  Integer age;

    @Column(name = "position")
    private String position;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id")
    private Team teamPlayer;

    /**
     * Connection with table "game"
     */
    @ManyToMany(mappedBy = "players")
    @ToString.Exclude
    @Builder.Default
    private Set<Game> games = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Player player = (Player) o;
        return player_id!= null && Objects.equals(player_id, player.player_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
