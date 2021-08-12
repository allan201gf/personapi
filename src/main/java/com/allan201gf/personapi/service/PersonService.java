package com.allan201gf.personapi.service;

import com.allan201gf.personapi.dto.response.MessageResponseDTO;
import com.allan201gf.personapi.entity.Person;
import com.allan201gf.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // indica ao Spring que as regras de negócio estarão aqui
public class PersonService {

    private PersonRepository personRepository; // acessa o repositorio com JPA Repository

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) { //Informa que esta vindo uma requisição do tipo pessoas
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID + " + savedPerson.getId())
                .build();
    }



}
