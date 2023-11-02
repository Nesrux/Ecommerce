package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private Integer categoriaPaiId;
}
