package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {
    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
}
