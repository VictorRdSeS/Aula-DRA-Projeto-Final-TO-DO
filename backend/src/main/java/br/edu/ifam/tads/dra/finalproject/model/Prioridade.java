package br.edu.ifam.tads.dra.finalproject.model;

import javax.persistence.*;
@Entity
public class Prioridade {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String tipo;
    public Prioridade(){
    }
    public Prioridade(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Prioridade{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
