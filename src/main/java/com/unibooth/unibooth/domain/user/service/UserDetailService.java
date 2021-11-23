package com.unibooth.unibooth.domain.user.service;

import com.unibooth.unibooth.config.JwtTokenProvider;
import com.unibooth.unibooth.domain.user.dto.UserCreateDto;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailElseThrow(username);
    }

    public String userJoin(UserCreateDto userCreateDto, JwtTokenProvider jwtTokenProvider) {
        User user = User.of(userCreateDto);
        userRepository.save(user);
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

    public String oauthGateway(UserCreateDto userCreateDto, JwtTokenProvider jwtTokenProvider) {

        Optional<User> user = userRepository.findByEmail(userCreateDto.getEmail());

        if(!user.isPresent()) {
           return userJoin(userCreateDto, jwtTokenProvider);
        }else {
            System.out.println("user.get().getUsername() = " + user.get().getUsername());
            return jwtTokenProvider.createToken(user.get().getUsername(), user.get().getRoles());
        }
    }
}
