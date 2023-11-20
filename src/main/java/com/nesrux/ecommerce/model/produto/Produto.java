package com.nesrux.ecommerce.model.produto;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "produto", uniqueConstraints = {@UniqueConstraint(name = "unq_nome", columnNames = "nome")},
        indexes = @Index(name = "idx_nome", columnList = "nome"))
public class Produto extends EntidadeBaseInteger {

    @Column(length = 100, nullable = false)
    private String nome;

    @Lob
    private String descricao;

    // @Column(precision = 10, scale = 2) //preco decimal(10, 2) 10 digitos com 2 casas decimais
    private BigDecimal preco;

    @Lob
    private byte[] foto;

    //Explicação
    /*Esse manyToMany e o @jointalbe serve para definir um mapeamento manytoMany
     * entre a classe produto e a classe categoria*/
    /*o join table se refere a tabela secundaria onde o jpa/hibernate vai criar ou se conectar, ele define qual vai ser
     * o nome das colunas e o nome da tela*/
    /*o primeiro parametro {name}, se refere ao nome da nova tebela de conecção, o segundo paramentro {joinColuns} que
     * recebe a anotação joincolumn, se refere ao owner da tabela, o "dono dela", que nesse caso é a clasee produto
     * que esta sendo referenciado pelo produto_id, e por sua vez o InverseJoincolumn se refere a quem é o "secundario"
     * dessa tabela, que no caso é o ID de categoria*/
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id",
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id",
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
    private List<Categoria> categorias = new ArrayList<>();

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    /*O atributo updateble, se refere que esse atributo
     * nao pode ser atualizado depois que ele é definitdo*/
    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    /*Insertable, se refere na hora da persistencia
     * esse atributo nao vai ter valor no hora
     * da inserção no banco de dados*/
    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ElementCollection
    @CollectionTable(name = "produto_tag",
            joinColumns = @JoinColumn(name = "produto_tag",
            foreignKey = @ForeignKey(name = "fk_produto_tag_produto")))
    @Column(name = "tag", nullable = false, length = 50)
    private List<String> tags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id",
                    foreignKey = @ForeignKey(name = "fk_produto_atributo_produto")))
    private List<Atributo> atributos = new ArrayList<>();
}
