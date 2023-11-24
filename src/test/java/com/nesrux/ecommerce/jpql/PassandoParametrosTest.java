package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.NotaFiscal;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PassandoParametrosTest extends EntityManagerTest {
    @Test
    public void passarParametro() {
        String jpql = "select p from Pedido p join p.pagamento pag where p.id = :pedidoId" +
                " and pag.status = ?2";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        /*o ? dentro da JPQL serve para passar parametros para a jpql sem precisar
         * concatenar os dados, deixando a string de query mais legivel
         * o ? + numero significa a posição onde  aquele parametro vai, nao precisa
         * necessariamente estar em ordem númerica, só precisa obriagatoriamente ter
         * um parametro e um setparameter correspondentes*/
        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter(2, StatusPagamento.PROCESSANDO);

        List<Pedido> pedidos = typedQuery.getResultList();

        Assert.assertEquals(1, pedidos.size());

    }

    @Test
    public void passarParametroDate() {
        String jpql = "select  nf from NotaFiscal nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);
        List<NotaFiscal> lista = typedQuery.getResultList();
    }
}
