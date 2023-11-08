package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "produto")
public class Produto {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    //Explicação
    /*Esse manyToMany e o @jointalbe serve para definir um mapeamento manytoMany
     * entre a classe produto e a classe categoria*/
    /*o join table se refere a tabela secundaria onde o jpa/hibernate vai criar ou se conectar, ele define qual vai ser
     * o nome das colunas e o nome da tela*/
    /*o primeiro parametro {name}, se refere ao nome da nova tebela de conecção, o segundo paramentro {joinColuns} que
     * recebe a anotação joincolumn, se refere ao owner da tabela, o "dono dela", que nesse caso é a clasee produto
     * que esta sendo referenciado pelo produto_id, e por sua vez o InverseJoincolumn se refere a quem é o "secundario"
     * dessa tabela, que no caso é o ID de categoria*/
    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    /*O atributo updateble, se refere que esse atributo
     * nao pode ser atualizado depois que ele é definitdo*/
    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    /*Insertable, se refere na hora da persistencia
     * esse atributo nao vai ter valor no hora
     * da inserção no banco de dados*/
    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;
}
