package com.drs.AppEvento3.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Convidado {

    @Id // ID único
    @NotEmpty //Campo náo pode ser vazio
    private String rg;

    @NotEmpty //Campo náo pode ser vazio
    private String nomeConvidado;

    @ManyToOne //Muitos para um só ou seja Muitos convidades para um Evento
    private Evento evento;


    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }
}
