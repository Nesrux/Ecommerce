package com.nesrux.ecommerce.listener;

import com.nesrux.ecommerce.model.Pedido.Pedido;
import com.nesrux.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {
    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerarNotaFiscal(pedido);
        }
    }

    ;
}
