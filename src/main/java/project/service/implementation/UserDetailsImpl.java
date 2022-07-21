package project.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.models.User;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    /**
     * RequiredArgsConstructor creates a constructor with the required arguments
     */
    private final User user;

    /**
     * Authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * getPassword
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * getUserName
     */
    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * isAccountNonExpired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * isEnabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
