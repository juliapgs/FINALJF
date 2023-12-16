package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {

    AGUARDANDOPAG(1, "Aguardadndo Pagamento"),
    PAGO(2, "Pago"),
    ENVIADO(3,"Enviado"),
    CANCELADO(4,"Cancelado"),
    FINALIZADO(5,"Finalizado");

    private final Integer id;
    private final String label;

    private StatusPedido(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (StatusPedido status : StatusPedido.values()) {
            if (status.getId().equals(id))
                return status;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
    
}