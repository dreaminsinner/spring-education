package com.chupryna.spring.services;

import com.chupryna.spring.models.Role;
import com.chupryna.spring.models.User;
import com.chupryna.spring.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(User user){
        user.setRole(new Role(2, "ROLE_USER"));
        peopleRepository.save(user);
    }

}
