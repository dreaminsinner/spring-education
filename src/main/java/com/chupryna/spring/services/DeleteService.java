package com.chupryna.spring.services;

import com.chupryna.spring.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteService {

    private final PeopleRepository peopleRepository;

    public DeleteService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void deleteUser(long id) {
        peopleRepository.delete(peopleRepository.findById(id).get());
    }
}
