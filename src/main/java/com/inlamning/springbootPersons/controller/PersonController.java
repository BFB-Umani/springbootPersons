package com.inlamning.springbootPersons.controller;

import com.inlamning.springbootPersons.models.request.PersonRequestModel;
import com.inlamning.springbootPersons.models.response.AddResponse;
import com.inlamning.springbootPersons.models.response.DeleteResponse;
import com.inlamning.springbootPersons.models.PersonEntity;
import com.inlamning.springbootPersons.models.response.EditResponse;
import com.inlamning.springbootPersons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    @GetMapping()
    public List<PersonEntity> StartPage() {
        return personService.listAll();
    }

    @PostMapping("/add")
    public AddResponse addNewPerson(@RequestBody PersonRequestModel personRequestModel) {

        personService.save(personRequestModel);
        AddResponse returnvalue = new AddResponse("person added");
        return returnvalue;}


    @GetMapping("/{id}/edit")
    public EditResponse editPerson(@PathVariable Long id) {

        EditResponse returnValue = new EditResponse("person edited");
        Optional<PersonEntity> person = personService.findPerson(id);
        if(person.isPresent()) {
            personService.edit(person);
            return returnValue;
        }
        else {
            returnValue.setMessage("person not found in database");
            return returnValue;
        }
    }

    @GetMapping("/{id}/delete")
    public DeleteResponse deleteProduct(@PathVariable Long id) {
        DeleteResponse returnvalue = new DeleteResponse("Person deleted");
        personService.deletePerson(id);
        return returnvalue;
    }


}
