package com.agenda.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.agenda.app.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
	Evento findByCodigo(long codigo);
}
