package com.allan201gf.personapi.entity;

import com.allan201gf.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Indica que é um entidade para o bd
@Entity
// lombok substitui os getter e setters
@Data
@Builder
// Insere os construtores
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //faz a geração automatica dos Id
    private Long id;

    @Enumerated(EnumType.STRING) // Para acessar os valores do arquivo enum
    @Column(nullable = false) // indica que não pode estar vazio
    private PhoneType type;

    @Column(nullable = false) // indica que não pode estar vazio
    private String number;

}
