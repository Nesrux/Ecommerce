package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class NotaFiscal {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer pedidoId;
    private String xml;
    private Date dataEmissao;
}

