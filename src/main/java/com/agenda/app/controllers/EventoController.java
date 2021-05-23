package com.agenda.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.app.models.Convidado;
import com.agenda.app.models.Evento;
import com.agenda.app.repositories.ConvidadoRepository;
import com.agenda.app.repositories.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private ConvidadoRepository convidadoRepository;

	@GetMapping("/cadastrarEvento")
	public String form() {
		return "evento/formEvento";
	}

	@PostMapping("/cadastrarEvento")
	public String form(@Valid Evento evento, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarEvento";
		}
		eventoRepository.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");
		return "redirect:/cadastrarEvento";
	}

	@GetMapping("/eventos")
	public ModelAndView listaEventos() {
		Iterable<Evento> eventos = eventoRepository.findAll();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/detalhesEvento/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}

	@PostMapping("/detalhesEvento/{codigo}")
	public String detalhesEventoPost(@PathVariable long codigo,
			@Valid Convidado convidado, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			System.out.println(result);
			return "redirect:/detalhesEvento/{codigo}";
		}
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		attributes.addFlashAttribute("mensagem",
				"Convidado adicionado com sucesso!");
		return "redirect:/detalhesEvento/{codigo}";
	}
}
