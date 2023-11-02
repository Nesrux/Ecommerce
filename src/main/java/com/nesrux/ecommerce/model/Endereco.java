package com.nesrux.ecommerce.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Endereco {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;
}
