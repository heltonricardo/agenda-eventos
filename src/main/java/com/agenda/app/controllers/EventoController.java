package com.agenda.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.agenda.app.models.Evento;
import com.agenda.app.repositories.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping("/cadastrarEvento")
	public String form() {
		return "evento/formEvento";
	}
	
	@PostMapping("/cadastrarEvento")
	public String form(Evento evento) {
		eventoRepository.save(evento);
		return "redirect:/cadastrarEvento";
	}
}
