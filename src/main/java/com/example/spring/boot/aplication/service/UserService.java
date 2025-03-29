package com.example.spring.boot.aplication.service;

import com.example.spring.boot.aplication.Models.UserEntity;
import com.example.spring.boot.aplication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> saveUser(UserEntity user) {
      Optional<UserEntity> entity = userRepository.findByEmail(user.getEmail());
      if (entity.isPresent()) {
         return ResponseEntity.ok(String.format("User with email %s already exists", user.getEmail()));
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> new User(
                        user.getEmail(),
                        user.getPassword(),
                        user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getAuthority())).toList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}
