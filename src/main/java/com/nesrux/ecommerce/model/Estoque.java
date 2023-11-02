package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "produto_id")
    private Integer produtoId;
    private Integer quantidade;
}