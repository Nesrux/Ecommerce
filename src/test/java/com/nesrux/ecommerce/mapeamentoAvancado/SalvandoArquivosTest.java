package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.Pedido.NotaFiscal;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.io.IOException;

public class SalvandoArquivosTest extends EntityManagerTest {

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void salvarXmlBancoDeDados() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();

        entityManager.persist(notaFiscal);

        entityManager.getTransaction().commit();
        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class,
                notaFiscal.getId());
        Assert.assertNotNull(notaFiscalVerificacao);
        Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);
    }

}
