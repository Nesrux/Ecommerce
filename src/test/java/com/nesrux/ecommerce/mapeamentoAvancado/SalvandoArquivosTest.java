package com.nesrux.ecommerce.mapeamentoAvancado;

import com.nesrux.ecommerce.model.Pedido.NotaFiscal;
import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.model.produto.Produto;
import org.junit.Assert;
import org.junit.Test;
import util.EntityManagerTest;

import java.io.IOException;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    private static byte[] carregarFoto() {
        return carregarArquivo("/kindle.jpeg");
    }

    private static byte[] carregarNotaFiscal() {
        return carregarArquivo("/nota-fiscal.xml");
    }

    private static byte[] carregarArquivo(String nome) {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(nome).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void salvarXmlBancoDeDados() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date(2000, 5 , 7));
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

    @Test
    public void salvarArquivoJpeg() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setFoto(carregarFoto());
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);
        Assert.assertNotNull(produtoVerificacao.getFoto());
        Assert.assertTrue(produtoVerificacao.getFoto().length > 0);
    }

}
