package project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Team
 */
@Getter
@Setter
@Entity
@Table(name = "team")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer team_id;

    @Column(name = "team_name")
    private String team_name;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "coach")
    private String coach;

    @OneToMany(mappedBy = "teamPlayer", fetch = FetchType.EAGER)//Eager
    @ToString.Exclude
    @Builder.Default
    private Set<Player> players = new HashSet<>();

    @OneToMany(mappedBy = "teamGame", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "teamGame", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    private Set<Result> results = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return team_id != null && Objects.equals(team_id, team.team_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

