package com.inlamning.springbootPersons.service;

import com.inlamning.springbootPersons.entities.PersonEntity;
import com.inlamning.springbootPersons.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonEntity> listAll()
    {
        return personRepository.findAll();
    }

    public Optional<PersonEntity> findPerson(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void save(PersonEntity person) {
        personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
