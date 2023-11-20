package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.listener.GerarNotaFiscalListener;
import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import com.nesrux.ecommerce.model.cliente.Cliente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(GerarNotaFiscalListener.class)
@Getter
@Setter
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {


    /*A propriedade optional, se refere a aquele valor pode ou nao pode estar lá, por exemplo
     * quando o hibernate faz a query para instanciar esse Pedido, ele faz joins para todas as propriedades
     * mapeadas de outra classe, como ele nao tem 100% de certeza que a quela propriedade mapeada pode ou nao
     * pode existir, ele faz um left join (caso nao entenda, olhe num grafico de conjunto, seria a parte esquerda
     * e a a intersecção desses 2 valores)*/
    /*Com essa propriedade marcada como false, ou seja todos os pedidos obrigatoriamente tem um cliente, ele faz um
     * inner join direto, ou seja só carrega a intersecção dos valores, que é mais performatico pois carrega menos dados*/
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;


    @Column(nullable = false)
    private BigDecimal total;

    /*Os métodos em cascata servem para salvar instancias no banco de
     * dados apenas com uma querry, nesse caso(sem a propriedade cascade)
     * se eu quisse salvar um Pedido e um itemPedido, eu teria que primeiro
     * salvar o Pedido, depois salvar o ItemPedido*/
    //com a propriedade cascade.Persist quando o Jpa salvar um pedido, ele
    //vai salvar todos os Itens do pedido

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private StatusPedido status;

    @Embedded
    private Endereco enderecoEntrega;

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

    //Callbacks do JPA
    /*Só pode existir uma anotação por classe de callback do JPa, caso o contrario ele da uma
     * Exception*/
    @PrePersist
    public void aoPersistir() {
        System.out.println("Ao persistir o dado");
        dataCriacao = LocalDateTime.now();
        calcularValorTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        System.out.println("ao atualizar o dado");
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        if (itens != null) {
            this.total = itens.stream()
                    .map(item -> item.getPrecoProduto()
                            .multiply(BigDecimal.valueOf(item.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            //Vivendo e aprendendo!
        } else {
            total = BigDecimal.ZERO;
        }
    }
}
