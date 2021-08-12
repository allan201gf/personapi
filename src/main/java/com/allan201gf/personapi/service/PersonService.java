package com.allan201gf.personapi.service;

import com.allan201gf.personapi.dto.request.PersonDTO;
import com.allan201gf.personapi.dto.response.MessageResponseDTO;
import com.allan201gf.personapi.entity.Person;

import com.allan201gf.personapi.mapper.PersonMapper;
import com.allan201gf.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // indica ao Spring que as regras de negócio estarão aqui
public class PersonService {

    private final PersonRepository personRepository; // acessa o repositorio com JPA Repository

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) { //Informa que esta vindo uma requisição do tipo pessoas

        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);

        return MessageResponseDTO
                .builder()
                .message("Created person with ID + " + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
