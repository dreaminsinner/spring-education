package com.chupryna.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chupryna.spring.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername (String username);

    Optional<User> findByEmail (String email);
}
