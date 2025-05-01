package com.damw.usersapp.service;

import com.damw.usersapp.model.User;
import com.damw.usersapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.NoSuchElementException;
import java.lang.reflect.Field;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User update(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User not found with id " + id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User patchUpdate(Long id, Map<String, Object> updates) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        updates.forEach((key, value) -> {
            try {
                Field field = User.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(existingUser, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // No se requiere acción, el campo no existe o no es accesible
            }
        });
        return userRepository.save(existingUser);
    }
}
