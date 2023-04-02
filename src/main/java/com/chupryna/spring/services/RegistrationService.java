package com.chupryna.spring.services;

import com.chupryna.spring.dto.UserDto;
import com.chupryna.spring.models.Role;
import com.chupryna.spring.repositories.PeopleRepository;
import com.chupryna.spring.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final Mapper mapper;

    private final PeopleRepository peopleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(Mapper mapper, PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(new Role(2, "ROLE_USER"));
        peopleRepository.save(mapper.dtoToUser(userDto));
    }

}
