package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.Pedido.NotaFiscal;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Test;
import util.EntityManagerTest;

public class SalvandoArquivosTest extends EntityManagerTest {
    @Test
    public void salvarXmlBancoDeDados() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);


        entityManager.getTransaction().begin();

        entityManager.persist(notaFiscal);

        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());


    }

}
