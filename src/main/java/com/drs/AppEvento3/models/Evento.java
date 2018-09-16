package com.drs.AppEvento3.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity //Entidade tabela do Banco de Dados
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L; //Atributo

    @Id //Cria um ID
    @GeneratedValue(strategy = GenerationType.AUTO)//Gera um ID Automaticamente no Formulário
    private long codigo;

    @NotEmpty //Campo náo pode ser vazio
    private String nome;

    @NotEmpty //Campo náo pode ser vazio
    private String local;

    @NotEmpty //Campo náo pode ser vazio
    private String data;

    @NotEmpty //Campo náo pode ser vazio
    private String horario;

    @OneToMany // Um evento para muitos convidados
    private List<Convidado> convidados;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
