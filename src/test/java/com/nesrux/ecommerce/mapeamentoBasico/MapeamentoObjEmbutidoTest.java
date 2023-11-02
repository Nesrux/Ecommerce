package com.nesrux.ecommerce.mapeamentoBasico;

import com.nesrux.ecommerce.model.Endereco;
import com.nesrux.ecommerce.model.Pedido;
import com.nesrux.ecommerce.model.StatusPedido;
import org.junit.Test;
import util.EntityManagerTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapeamentoObjEmbutidoTest extends EntityManagerTest {
    @Test
    public void analisarObjEmbutido() {
        Endereco endereco = new Endereco();
        endereco.setBairro("Santa Etelvina");
        endereco.setCep("08487-130");
        endereco.setNumero("65");
        endereco.setComplemento("Do lado de uma arvore de jambu");
        endereco.setCidade("São paulo");

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        pedido.setEnderecoEntrega(endereco);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);


        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoPersitido = entityManager.find(Pedido.class, 1);
        Endereco enderecoPersistido = pedidoPersitido.getEnderecoEntrega();

        assertNotNull(pedidoPersitido);
        assertNotNull(enderecoPersistido);
        assertEquals(endereco, enderecoPersistido);
    }
}