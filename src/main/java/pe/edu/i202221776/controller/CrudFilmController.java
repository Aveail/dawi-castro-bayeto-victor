package pe.edu.i202221776.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.i202221776.dto.FilmTosaveDTO;
import pe.edu.i202221776.service.CrudFilmService;

@Controller
public class CrudFilmController {
	@Autowired
	private CrudFilmService _CrudFilmService;
	
	@GetMapping("/films")
	public String list(Model model,RedirectAttributes flash) {
		model.addAttribute("films", _CrudFilmService.findAll());
		return "ListaFilm";
	}
	
	@GetMapping("/film/{id}")
	public String viewDetails(Model model, @PathVariable Integer id) {
		model.addAttribute("filmSelected" ,_CrudFilmService.getFilmDetail(id));
		return "viewDetail";
	}
	@GetMapping("/save/{id}")
	public String viewToSave(Model model, @PathVariable Integer id) {
		model.addAttribute("languages" ,_CrudFilmService.getAllLanguages());
		model.addAttribute("filmSelected" ,_CrudFilmService.getFilmToSave(id));
		return "saveToFilm";
	}
	
	@PostMapping("/film")
	public String putFilm(@ModelAttribute FilmTosaveDTO filmSelected,RedirectAttributes flash) {		
		 if (filmSelected.filmId()!=null)	{
			 if(_CrudFilmService.update(filmSelected)!=null)
				 flash.addFlashAttribute("success", "Se guardo el film correctamente");
			 else
				 flash.addFlashAttribute("error", "No se guardo el film correctamente");
		 }		
		else
			if(_CrudFilmService.create(filmSelected)!=null)
				 flash.addFlashAttribute("info", "Se guardo el film correctamente");
			 else
				 flash.addFlashAttribute("error", "No se guardo el film correctamente");
			
			 return "redirect:/films";
	}
	
	@PostMapping("/film/{id}")
	public String delFilm(@PathVariable Integer id, Model model,RedirectAttributes flash) {
		 if(_CrudFilmService.delete(id))
		 	flash.addFlashAttribute("error", "Se elimino el film correctamente");
		 else
			 flash.addFlashAttribute("warning", "No Se elimino el film");
		return "redirect:/films";
	}
	
}
