package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.Pedido.Pagamento;
import com.nesrux.ecommerce.model.cliente.Cliente;
import com.nesrux.ecommerce.model.cliente.SexoCliente;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.time.LocalDate;
import java.util.List;

public class HerancaTest extends EntityManagerTest {
    @Test
    public void salvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(2001, 3, 5));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Cliente clieenteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clieenteVerificacao.getId());

    }

    @Test
    public void buscarPagamento() {
        List<Pagamento> pagamentos = entityManager
                .createQuery("select p from Pagamento p")
                .getResultList();
        Assert.assertFalse(pagamentos.isEmpty());
    }
}
