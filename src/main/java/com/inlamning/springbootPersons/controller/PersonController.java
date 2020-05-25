package com.inlamning.springbootPersons.controller;

import com.inlamning.springbootPersons.entities.PersonEntity;
import com.inlamning.springbootPersons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/startsida")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String StartPage(Model model)
    {
        List<PersonEntity> personList = personService.listAll();
        model.addAttribute("listPersons", personList);
        return "startsida";}

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

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model)
    {
        Optional<PersonEntity> person = personService.findPerson(id);
        if(person.isPresent())
        {model.addAttribute("editPerson", person.get());
            return "editPersonInfo";}
        else
            return "error";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
    {personService.deletePerson(id);
        return "redirect:/startsida";
    }


}
