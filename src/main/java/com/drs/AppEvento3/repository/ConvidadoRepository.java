package com.drs.AppEvento3.repository;

import com.drs.AppEvento3.models.Convidado;
import com.drs.AppEvento3.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

    Iterable<Convidado> findByEvento(Evento evento); //Busca Lista de Convidados do Evento Relacionado

    Convidado findByRg(String rg); //Busca específica, procurar por rg do tipo Stringw
}
