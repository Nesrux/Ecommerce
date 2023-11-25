package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class EspressoesCondicionaisTest extends EntityManagerTest {
    @Test
    public void usarLike() {
        String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "a");

        List<Cliente> clientes = typedQuery.getResultList();
        Assert.assertFalse(clientes.isEmpty());
    }

    @Test
    public void usarIsnull() {
        String jpql = "select p from Produto p where p.foto is null";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarIsEmpty() {
        String jpql = "select p from Produto p where p.categorias is empty";
        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);
        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarmaiorMenor() {
        String jpql = "select p from Produto p where p.preco > :preco";

        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);
        typedQuery.setParameter("preco", new BigDecimal("499"));

        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarBetween() {
        String jpql = "select p from Produto p where p.preco between :precoInicial and :precoFinal";

        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);
        typedQuery.setParameter("precoInicial", new BigDecimal("499"));
        typedQuery.setParameter("precoFinal", new BigDecimal("1400"));

        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarBetweenComData() {
        String jpql = "select p from Pedido p where p.dataCriacao between :dataInical and :dataFinal";

        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);
        typedQuery.setParameter("dataInical", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }


    @Test
    public void desafioTestData() {
        String jpql = "select p from Pedido p where p.dataCriacao > :data";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("data", LocalDateTime.now().minusDays(3));
        List<Pedido> pedidos = typedQuery.getResultList();

        Assert.assertFalse(pedidos.isEmpty());
    }

    @Test
    public void usarExpressaoDiferente(){
        // o operador <> é igual ao operador !=
        String jpql = "select p from Pedido p where p.id <> 1";
        TypedQuery<?> typedQuery = entityManager.createQuery(jpql, Object.class);

        List<?> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

}
