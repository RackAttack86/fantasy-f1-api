package com.fantasyf1.fantasy_f1_api.user.repository;

import com.fantasyf1.fantasy_f1_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository // Spring data JPA automatically generates save, findById, findAll, delete, count, etc.
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email); // Spring parses the method name to generate the query

    boolean existsByEmail(String email);
}
