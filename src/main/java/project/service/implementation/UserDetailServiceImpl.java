package project.service.implementation;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.models.User;
import project.repository.UserRepository;

import static project.service.implementation.ConstansImpl.COULD_NOT_FIND_USER;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    /**
     * RequiredArgsConstructor creates a constructor with the required arguments
     */
    private final UserRepository userRepository;

    /**
     * Load user by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(COULD_NOT_FIND_USER);
        }
        return new UserDetailsImpl(user);
    }
}
