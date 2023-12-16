package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CategoriaTenis {
    CASUAL(1, "Casual"),
    BASQUETE(2,"Basquete"),
    SKATE(3, "Skate"),
    CORRIDA(4, "Corrida");

    private final Integer id;
    private final String label;

    private CategoriaTenis(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static CategoriaTenis ValueOf(Integer id) throws IllegalArgumentException {
        if (id ==null)
            return null;
        for (CategoriaTenis categoriaTenis :CategoriaTenis.values()) {
            if (categoriaTenis.getId().equals(id))
                return categoriaTenis;
        }

        throw new IllegalArgumentException("Id inválido" + id);
    }
}
