package com.nesrux.ecommerce.criteria;

import com.nesrux.ecommerce.model.Pedido.ItemPedido;
import com.nesrux.ecommerce.model.Pedido.Pagamento;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class JoinCriteriaTest extends EntityManagerTest {

    @Test
    public void fazerJoin() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        //Isso aqui é um inner join, por isso retorna apenas 4 pedidos dos 5
        Join<Pedido, Pagamento> joinPagamento = root.join("pagamento");
        Join<Pedido, ItemPedido> joinItens = root.join("itens");
        Join<ItemPedido, Produto> joinProduto = joinItens.join("produto");

        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
