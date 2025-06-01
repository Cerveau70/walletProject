package com.wave.backend.repository;

import com.wave.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNumero(String numero);
    User findByNumero(String numero);
}
