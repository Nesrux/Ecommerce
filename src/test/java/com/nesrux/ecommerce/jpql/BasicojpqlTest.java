package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.ClientInfoStatus;
import java.util.List;

public class BasicojpqlTest extends EntityManagerTest {

    /*JPQL é java persistence query languague*/

    //JPql select p from Pedido p where p.id = ?

    //SQL select * from pedido  where id = ?

    @Test
    public void buscarIdentificador() {
        TypedQuery<Pedido> pedidoQuery = entityManager
                .createQuery("select p from Pedido p where p.id=1", Pedido.class);
        Pedido pedido = pedidoQuery.getSingleResult();
        Assert.assertNotNull(pedido);
    }

    @Test
    public void mostrarDiferencaQuerys() {
        String jpql = "select p from Pedido p where p.id=1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedidoTipado = typedQuery.getSingleResult();

        Query query = entityManager.createQuery(jpql);
        Pedido pedidoDois = (Pedido) query.getSingleResult();

        Assert.assertNotNull(pedidoTipado);
        Assert.assertNotNull(pedidoDois);
    }

    @Test
    public void selecionarUmAtributo(){
        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> nomes = typedQuery.getResultList();
        Assert.assertEquals(String.class, nomes.get(0).getClass());

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> clientesTypados = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> clientesList = clientesTypados.getResultList();

        Assert.assertEquals(Cliente.class, clientesList.get(0).getClass());
    }
    @Test
    public void projetarResultado(){
        String jpql = "select id, nome from Produto p";
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> resultados = typedQuery.getResultList();
        Assert.assertTrue(resultados.get(0).length == 2);
        resultados.forEach(System.out::println);
    }
}
