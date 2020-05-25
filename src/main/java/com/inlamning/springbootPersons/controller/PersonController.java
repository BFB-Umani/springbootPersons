package com.inlamning.springbootPersons.controller;

import com.inlamning.springbootPersons.models.DeleteResponse;
import com.inlamning.springbootPersons.models.PersonEntity;
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

    @GetMapping("/addPerson")
    public String showNewProductPage(Model model)
    {PersonEntity person = new PersonEntity();
        model.addAttribute("personObject",person);
        return "addPerson";}

    @PostMapping("/save")
    public String saveProduct(PersonEntity person)
    {
        personService.save(person);
        return "redirect:/startsida";
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(@PathVariable Long id, Model model)
    {
        Optional<PersonEntity> person = personService.findPerson(id);
        if(person.isPresent())
        {model.addAttribute("editPerson", person.get());
            return "editPersonInfo";}
        else
            return "error";
    }

    @GetMapping("/{id}/delete")
    public DeleteResponse deleteProduct(@PathVariable Long id) {
        DeleteResponse deleteResponse = new DeleteResponse("Person deleted", false);
        personService.deletePerson(id);
        return deleteResponse;
    }


}
