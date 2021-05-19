package com.agenda.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.agenda.app.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long> {
}
