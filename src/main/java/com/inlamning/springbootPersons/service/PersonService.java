package com.inlamning.springbootPersons.service;

import com.inlamning.springbootPersons.models.PersonEntity;
import com.inlamning.springbootPersons.models.request.PersonRequestModel;
import com.inlamning.springbootPersons.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
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
    public void save(PersonRequestModel personRequest) {

        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personRequest, personEntity);

        personRepository.save(personEntity);
    }

    @Transactional
    public void edit(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }

    @Transactional
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
