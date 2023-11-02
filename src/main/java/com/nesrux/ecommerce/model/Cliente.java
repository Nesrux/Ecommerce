package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class Cliente {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;


}
