package com.damw.usersapp.repository;

import com.damw.usersapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Esta interfaz extiende JpaRepository, que proporciona operaciones CRUD para
    // la entidad User.
}
