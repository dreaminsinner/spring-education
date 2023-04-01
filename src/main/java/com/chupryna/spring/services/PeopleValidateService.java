package com.chupryna.spring.services;

import com.chupryna.spring.models.User;
import com.chupryna.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleValidateService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleValidateService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public User findByEmail(String email){
        Optional<User> user = peopleRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User findByUsername(String username){
        Optional<User> user = peopleRepository.findByUsername(username);
        return user.orElse(null);
    }

    public boolean isEmailExists(String email) {
        return peopleRepository.findByEmail(email).isPresent();
    }

    public boolean isUsernameExists(String username) {
        return peopleRepository.findByUsername(username).isPresent();
    }
}
