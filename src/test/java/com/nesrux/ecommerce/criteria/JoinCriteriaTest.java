package com.nesrux.ecommerce.criteria;

import com.nesrux.ecommerce.model.Pedido.ItemPedido;
import com.nesrux.ecommerce.model.Pedido.Pagamento;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPagamento;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    @Test
    public void fazerJoinComOn() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> pedidoPagamentoJoin = root.join("pagamento");
        pedidoPagamentoJoin.on(criteriaBuilder.equal(pedidoPagamentoJoin.get("status"),
                StatusPagamento.PROCESSANDO));

        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void fazerJoinComLeftOuterJoin() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> pedidoPagamentoJoin = root.join("pagamento",
                JoinType.LEFT);

        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void fazerJoinComFetch() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        root.fetch("itens", JoinType.LEFT);
        root.fetch("notaFiscal", JoinType.LEFT);
        root.fetch("pagamento", JoinType.LEFT);
        root.fetch("cliente");

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        Pedido pedido = typedQuery.getSingleResult();

        Assert.assertNotNull(pedido);
        Assert.assertFalse(pedido.getItens().isEmpty());
    }

}
