package com.nesrux.ecommerce.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*A propriedade optional, se refere a aquele valor pode ou nao pode estar lá, por exemplo
     * quando o hibernate faz a query para instanciar esse Pedido, ele faz joins para todas as propriedades
     * mapeadas de outra classe, como ele nao tem 100% de certeza que a quela propriedade mapeada pode ou nao
     * pode existir, ele faz um left join (caso nao entenda, olhe num grafico de conjunto, seria a parte esquerda
     * e a a intersecção desses 2 valores)*/
    /*Com essa propriedade marcada como false, ou seja todos os pedidos obrigatoriamente tem um cliente, ele faz um
     * inner join direto, ou seja só carrega a intersecção dos valores, que é mais performatico pois carrega menos dados*/
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private Endereco enderecoEntrega;

    //Callbacks do JPA
    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
    }
}
