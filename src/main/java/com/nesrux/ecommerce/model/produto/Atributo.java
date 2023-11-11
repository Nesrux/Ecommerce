package com.nesrux.ecommerce.model.produto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Atributo {
    private String nome;
    private String valor;
}