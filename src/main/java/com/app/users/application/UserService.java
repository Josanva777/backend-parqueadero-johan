package com.app.users.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

// import com.app.parking_types.domain.ParkingTypes;
import com.app.shared.adapters.exception.ResourceNotFoundException;
import com.app.users.domain.IUserRepository;
import com.app.users.domain.IUserService;
import com.app.users.domain.User;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with ID: " + id));
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        User existingUser = findById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setUserName(user.getUserName());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
