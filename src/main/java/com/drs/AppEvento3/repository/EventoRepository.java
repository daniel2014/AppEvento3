package com.drs.AppEvento3.repository;

import com.drs.AppEvento3.models.Evento;
import org.springframework.data.repository.CrudRepository;


public interface EventoRepository extends CrudRepository<Evento, String> {

    Evento findByCodigo(long codigo);//Busca de 1 Evento


}
