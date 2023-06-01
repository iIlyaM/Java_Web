package com.example.java_web.contoller;

import com.example.java_web.dto.PersonDto;
import com.example.java_web.entity.Person;
import com.example.java_web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public List<PersonDto> getPersons() {
        return personService.getPersons().stream().map(this::convertToPersonDto).collect(Collectors.toList());
    }

    @GetMapping("/{name}")
    public List<PersonDto> getPerson(@PathVariable("name") String name) {
        return personService.getPersonsByName(name).stream().map(this::convertToPersonDto).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody PersonDto personDto) {
        personService.save(convertToPerson(personDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody PersonDto personDto) {
        personService.deleteByName(convertToPerson(personDto).getName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setAge(personDto.getAge());
        person.setSex(personDto.getSex());
        person.setDocument(personDto.getDocument());

        return person;
    }

    private PersonDto convertToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setAge(person.getAge());
        personDto.setSex(person.getSex());
        personDto.setDocument(person.getDocument());

        return personDto;
    }
}