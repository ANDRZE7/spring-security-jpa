package io.javabrains.springsecurityjpa;

import io.javabrains.springsecurityjpa.models.MyUserDetails;
import io.javabrains.springsecurityjpa.models.User;
import io.javabrains.springsecurityjpa.repostitories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(userName);
//        user.orElseThrow( () -> new UsernameNotFoundException("User not found: " + userName));
        if (user.isEmpty()) {
            log.info("User not found: {}", userName);
            throw new UsernameNotFoundException("User not found: " + userName);
        } else {
            log.info("Found user: {}", user.toString());
        }
        return user.map(MyUserDetails::new).get();
    }
}
