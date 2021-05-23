package com.agenda.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.agenda.app.models.Convidado;
import com.agenda.app.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long> {
	Iterable<Convidado> findByEvento(Evento evento);
}
