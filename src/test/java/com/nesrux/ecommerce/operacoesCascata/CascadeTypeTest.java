package com.nesrux.ecommerce.operacoesCascata;

import com.nesrux.ecommerce.model.Pedido.ItemPedido;
import com.nesrux.ecommerce.model.Pedido.ItemPedidoId;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.cliente.SexoCliente;
import com.nesrux.ecommerce.model.produto.Categoria;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CascadeTypeTest extends EntityManagerTest {
   // @Test
    public void persistirProdutoComCategoria(){
        Produto produto = new Produto();
        produto.setNome("Fone de ouvido");
        produto.setPreco(BigDecimal.TEN);
        produto.setDescricao("Um fone foda da JBL imagina aí");

        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        produto.getCategorias().add(categoria);

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Categoria categoriaTest = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertNotNull(categoriaTest);
    }

    @Test
    public void persistirComItens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(BigDecimal.TEN);

        pedido.getItens().add(itemPedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();


        Pedido pedidoTest = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertFalse(pedido.getItens().isEmpty());
    }

    //  @Test
    public void persistirPedidoComCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("1256256");
        cliente.setNome("Elma maria pinto");
        cliente.setSexo(SexoCliente.FEMININO);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoTest = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoTest.getCliente());
    }

    @Test
    public void persitirItemPedidoComPedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.AGUARDANDO);


        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(BigDecimal.TEN);

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        /*Como o Id de itemPedido, precisa necessariamente de um Pedido, pois é uma
         * chave composta, não existe a necessidade de colocar o cascade na relação
         * dessas duas propriedades*/
        Pedido pedidoTest = entityManager.find(pedido.getClass(),
                pedido.getId());
        Assert.assertNotNull(pedidoTest);
    }
}
