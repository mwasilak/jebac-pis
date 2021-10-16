package pl.sixpinetrees.tournament.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sixpinetrees.tournament.domain.Player;
import pl.sixpinetrees.tournament.repository.PlayerRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public User addUser(UserRegistrationRequest userRegistrationRequest) {
        User user = userRepository.save(new User(userRegistrationRequest.getUsername(),
                passwordEncoder.encode(userRegistrationRequest.getPassword())));
        playerRepository.save(new Player(user.getId(),
                userRegistrationRequest.getFirstName(),
                userRegistrationRequest.getLastName()));
        return user;
    }
}
