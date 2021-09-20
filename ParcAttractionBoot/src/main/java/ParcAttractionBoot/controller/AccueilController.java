package ParcAttractionBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ParcAttractionBoot.model.Joueur;

@Controller
public class AccueilController {

	
	@GetMapping("/accueil")
	public ModelAndView accueil() {
		return displayView("flex","none","none");
	}


	@GetMapping("/connexion")
	public ModelAndView connexion() {
		return displayView("none","flex","none");
	}

	
	@GetMapping("/inscription")
	public ModelAndView inscription() {
		return displayView("none","none","flex");
	}

	
	private ModelAndView displayView(String accueil, String connexion, String inscription) {
		ModelAndView modelAndView=new ModelAndView("accueil");
		modelAndView.addObject("display_accueil", accueil);
		modelAndView.addObject("display_connexion", connexion);
		modelAndView.addObject("display_inscription", inscription);
		modelAndView.addObject("compte",new Joueur());
		return modelAndView;
	}
}
