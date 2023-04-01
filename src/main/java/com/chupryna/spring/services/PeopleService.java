package com.chupryna.spring.services;


import com.chupryna.spring.repositories.PeopleRepository;
import com.chupryna.spring.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chupryna.spring.models.User;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PeopleService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
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
    public void save(User user){
        peopleRepository.save(user);
    }



}
