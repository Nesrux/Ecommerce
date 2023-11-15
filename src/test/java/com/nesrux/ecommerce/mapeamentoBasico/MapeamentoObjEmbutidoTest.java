package com.nesrux.ecommerce.mapeamentoBasico;

import com.nesrux.ecommerce.model.Pedido.Endereco;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.Pedido.StatusPedido;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.cliente.SexoCliente;
import org.junit.Test;
import util.EntityManagerTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapeamentoObjEmbutidoTest extends EntityManagerTest {
    @Test
    public void analisarObjEmbutido() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste mapeamentoEmbutido");
        cliente.setCpf("123546789");
        cliente.setSexo(SexoCliente.FEMININO);

        Endereco endereco = new Endereco();
        endereco.setBairro("Santa Etelvina");
        endereco.setCep("08487-130");
        endereco.setNumero("65");
        endereco.setComplemento("Do lado deu arvore de jambu");
        endereco.setCidade("São paulo");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(endereco);

        entityManager.getTransaction().begin();

        entityManager.persist(cliente);
        entityManager.persist(pedido);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersitido = entityManager.find(Pedido.class, pedido.getId());
        Endereco enderecoPersistido = pedidoPersitido.getEnderecoEntrega();


        assertNotNull(pedidoPersitido);
        assertNotNull(enderecoPersistido);
        assertEquals(endereco, enderecoPersistido);
    }
}
