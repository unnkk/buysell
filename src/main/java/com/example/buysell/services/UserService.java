package com.example.buysell.services;

import com.example.buysell.entities.User;
import com.example.buysell.enums.Role;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        log.info("user:\n{}\n{}\n{}", user.getId(), user.getUsername(), user.getName());
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new user with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id);
    }

    public void banUser(long id) {
        User user = userRepository.findById(id);
        if(user != null){
            user.setActive(!user.isActive());
            log.info("Banning user with id: {}", id);
            userRepository.save(user);
        }
    }

    public void changeUserRoles(Long id, Map<String, String> form) {
        User user = userRepository.findById(id);
        if(user != null) {
            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());
            user.getRoles().clear();
            for (String key : form.keySet()) {
                if(roles.contains(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
            userRepository.save(user);
        }
    }
}
