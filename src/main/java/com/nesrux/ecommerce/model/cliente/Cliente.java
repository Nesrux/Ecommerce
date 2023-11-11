package com.nesrux.ecommerce.model.cliente;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class Cliente {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    /*Essa anotção faz o Jpa ignorar essa propriedade*/
    @Transient
    private String primeiroNome;

    //TODO util para o amimais
    @ElementCollection
    @CollectionTable(name = "cliente_contato",
            joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos = new HashMap<>();

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @PostLoad
    public void configurarPrimeiro() {
        if (this.nome != null && !this.nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}
