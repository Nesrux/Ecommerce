package com.nesrux.ecommerce.model.Pedido;

import com.nesrux.ecommerce.model.EntidadeBaseInteger;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
@DiscriminatorColumn(name = "dtaype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pagamento extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    /*o JPa sa pré-seta isso que esta definido explicitamente, como DiscriminatorColumn e Inheritance, só esta ai
     * para deixar didático, dito isso */

    /*O discriminationColumn serve para definir o nome da coluna de descriminação, ou seja a coluna que vai fazer
     * a diferença, em que tipo de pagamento vai ser execurado, como PagamentoBoleto ou PagamentoCartao*/

    /*Inheritance serve para definir a estarégia que vai ser utilizado, nesse exemplo esta tudo numa
     * mesma tabela, mas poderia ser tudo em tabelas diferentes, com valors e dados difetentes*/

}
