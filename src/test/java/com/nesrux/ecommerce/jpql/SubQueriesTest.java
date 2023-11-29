package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.cliente.Cliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubQueriesTest extends EntityManagerTest {
    @Test
    public void pesquisarSubQueries() {
//        //o Produto mais caro da base de dados
//        String jpql = "select p from Produto p where " +
//                "p.preco = (select max(preco) from Produto)";
//        //Todos os pedidos acima da média de vendas;
//        String jpql = "select p from Pedido p where " +
//                "p.total > (select avg(total) from Pedido)";

        String jpql = "select c from Cliente c where " +
                "100 < (select sum(p.total) from c.pedidos p)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

//        lista.forEach(p -> System.out.println(p.getNome() + ", " + p.getPreco()));
//        lista.forEach(p -> System.out.println(p.getId() + ", " + p.getCliente().getNome()
//        + p.getTotal()));
        lista.forEach(c -> System.out.println(c.getNome() + ", " + c.getId()));
    }
}
