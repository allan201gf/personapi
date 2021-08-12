package com.allan201gf.personapi.service;

import com.allan201gf.personapi.dto.request.PersonDTO;
import com.allan201gf.personapi.dto.response.MessageResponseDTO;
import com.allan201gf.personapi.entity.Person;

import com.allan201gf.personapi.exception.PersonNotFoundException;
import com.allan201gf.personapi.mapper.PersonMapper;
import com.allan201gf.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .message("Pessoa criada com Id: " + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToUpdate);

        return createMessageResponse(savedPerson);

    }

    private MessageResponseDTO createMessageResponse(Person savedPerson) {
        return MessageResponseDTO
                .builder()
                .message("Pessoa criada com Id: " + savedPerson.getId())
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }


}
