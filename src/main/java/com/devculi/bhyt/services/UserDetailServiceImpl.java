package com.devculi.bhyt.services;

import com.devculi.bhyt.configs.CustomUserDetails;
import com.devculi.bhyt.entities.User;
import com.devculi.bhyt.exceptions.EntityNotFoundException;
import com.devculi.bhyt.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByEmail(username));
        return optionalUser
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "username or email", username));
    }

    public UserDetails loadUserById(Long id) {
        return userRepository
                .findById(id)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
    }
}

