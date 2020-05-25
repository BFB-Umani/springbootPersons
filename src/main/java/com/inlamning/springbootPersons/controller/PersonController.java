package com.inlamning.springbootPersons.controller;

import com.inlamning.springbootPersons.models.response.AddResponse;
import com.inlamning.springbootPersons.models.response.DeleteResponse;
import com.inlamning.springbootPersons.models.PersonEntity;
import com.inlamning.springbootPersons.models.response.EditResponse;
import com.inlamning.springbootPersons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonEntity> getAllPersons() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public Optional<PersonEntity> getById(@PathVariable Long id) {

        Optional<PersonEntity> person = personService.findPerson(id);

        if(person.isPresent()) {
            if(!person.get().hasLink("all_persons")) {
                Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).getAllPersons()).withRel("all_persons");
                person.get().add(link1);
            }

            if(!person.get().hasLink("delete_Person")) {
                Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).deletePerson(id)).withRel("delete_Person");
                person.get().add(link1);
            }
            if(!person.get().hasLink("add_Person")) {
                Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).addNewPerson(person.get())).withRel("add_Person");
                person.get().add(link1);
            }
            if(!person.get().hasLink("edit_Person")) {
                Link link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).editPerson(id, person.get())).withRel("edit_Person");
                person.get().add(link1);
            }
            return person;
        }
        return Optional.empty();
    }

    @PostMapping("/add")
    public AddResponse addNewPerson(@RequestBody PersonEntity personEntity) {

        personService.save(personEntity);

        return  new AddResponse("person added");
    }


    @PostMapping("/{id}/edit")
    public EditResponse editPerson(@PathVariable Long id, @RequestBody PersonEntity personEntity) {

        EditResponse returnValue = new EditResponse("person edited");
        Optional<PersonEntity> person = personService.findPerson(id);
        if(person.isPresent()) {
            personService.edit(person.get(), personEntity);
        }
        else {
            returnValue.setMessage("person not found in database");
        }
        return returnValue;
    }

    @GetMapping("/{id}/delete")
    public DeleteResponse deletePerson(@PathVariable Long id) {
        DeleteResponse returnValue = new DeleteResponse("Person deleted");

        Optional<PersonEntity> person = personService.findPerson(id);
        if(person.isPresent()) {
            personService.deletePerson(person.get());
        }
        else {
            returnValue.setMessage("person not found in database");
        }
        return returnValue;
    }


}
