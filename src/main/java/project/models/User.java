package project.models;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Class User
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    private static final long serialVersionUID = 1L;

    /**
     * User entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Column check user_name (NotBlank,Size and Pattern)
     */
    @NotBlank(message = "Must not be empty")
    @Size(min = 3, max = 16, message = "UserName must be between 3 and 15 characters long")
    @Pattern(regexp = "[a-zA-Z]*", message = "Only latin letters")
    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    /**
     * Connection with table "user-role"
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
