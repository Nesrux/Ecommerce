package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    /*Auto relacionamento*/
    /*Many to one é o owner*/
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias = new ArrayList<>();
    /*Nesse caso nao precisa de muitas configuraçoes, pois o Jpa sabe que essa entidade
     * nao é p owner da relação.*/
    /*Ele só esta definindo onde ele vai pegar essa relação, que no caso e na propriedade
     * "categorias" da entidade produto*/
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
}
