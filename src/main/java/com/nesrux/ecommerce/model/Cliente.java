package com.nesrux.ecommerce.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Cliente {
    @Id
    private Integer id;
    private  String nome;
}
