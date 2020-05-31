package com.devculi.bhyt.services;

import com.devculi.bhyt.configs.CustomUserDetails;
import com.devculi.bhyt.dto.UserDTO;
import com.devculi.bhyt.entities.User;
import com.devculi.bhyt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    public Object login(String username, String password) {
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findUserByEmail(customUserDetails.getUsername());
        System.out.println(user);
        return UserDTO.builder().email(user.getEmail()).role(user.getRole()).build();
    }

    public Authentication authenticate(String username, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    }


}
