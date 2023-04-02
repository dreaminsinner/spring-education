package com.chupryna.spring.services;


import com.chupryna.spring.dto.UserDto;
import com.chupryna.spring.repositories.PeopleRepository;
import com.chupryna.spring.security.UserDetail;
import com.chupryna.spring.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chupryna.spring.models.User;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    private final Mapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, Mapper mapper, @Lazy PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> foundUser = peopleRepository.findByUsername(username);
        if(foundUser.isEmpty()) throw  new UsernameNotFoundException("User not found!");
        return new UserDetail(foundUser.get());
    }

    public List<User> findAll(){
        return peopleRepository.findAll();
    }

    public Optional<User> findById(long id){
        return peopleRepository.findById(id);
    }

    @Transactional
    public void save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        peopleRepository.save(mapper.dtoToUser(userDto));
    }

}
