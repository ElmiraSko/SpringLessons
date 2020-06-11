package com.spboot.springbootlesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spboot.springbootlesson.persist.entity.User;
import com.spboot.springbootlesson.persist.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service // теперь это бин
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true) // что бы при чтении данных, никто не смог внести изменения
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
       userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
