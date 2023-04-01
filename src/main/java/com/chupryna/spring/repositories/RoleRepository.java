package com.chupryna.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.chupryna.spring.models.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (String name);

}
