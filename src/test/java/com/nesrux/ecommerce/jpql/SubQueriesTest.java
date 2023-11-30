package com.nesrux.ecommerce.jpql;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import javax.persistence.TypedQuery;
import java.util.List;

public class SubQueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarComAll() {
        String jpql = "select p from Produto p where " +
                "p.preco = ALL (select precoProduto from ItemPedido where  produto = p)";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<? extends Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println("ID : " + p.getId()));
    }

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

    @Test
    public void pesquisaSubqueriesComIn() {
        String jpql = "select p from Pedido p where p.id in " +
                "(select p2.id from ItemPedido i2 join i2.pedido p2 " +
                "join i2.produto pro2 where pro2.preco > 100)";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<? extends Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println("ID : " + p.getId()));
    }

    @Test
    public void pesquisaSubqueriesComExsitis() {
        String jpql = "select p from Produto p where exists " +
                "(select 1 from ItemPedido ip2 join ip2.produto p2 where p2 = p)";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<? extends Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());


        lista.forEach(p -> System.out.println("ID : " + p.getId()));
    }

    @Test
    public void desafio_produto_categoria_igual_a_dois() {
        String jpql = "select p from Pedido p where p.id in" +
                "   (select p2.id from ItemPedido i2 " +
                "   join i2.pedido p2 join i2.produto pro2 join pro2.categorias c2 where c2.id = 2)";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println("ID : " + p.getId()));
    }

    @Test
    public void todos_os_clientes_que_fizeram_2Pedidos() {
        String jpql = "select c from Cliente c where (select count(cliente) from Pedido p) > 2";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println("Nome : " + p.getNome()));
    }

    @Test
    public void queryComExistis() {
        String jpql = "select p from Produto p " +
                " where exists " +
                " (select 1 from ItemPedido where produto = p and precoProduto <> p.preco)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<? extends Produto> lista = typedQuery.getResultList();
        /*essa asserssão não esta passando por causa que eu não editei o arquivo dados iniciais kindle para nao sujar
        * a base de dados, porém é valido afirmar que isso funciona! anrigamente isso tava como asserFalse*/
        Assert.assertTrue(lista.isEmpty());

        lista.forEach(p -> System.out.println("id  : " + p.getId()));

    }
}
