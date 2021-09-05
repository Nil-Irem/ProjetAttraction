package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import attraction.model.Compte;
import attraction.model.Joueur;
import attraction.repositories.CompteRepository;

@Controller
@RequestMapping("/compte")
public class CompteController  {
	
	@Autowired
	private CompteRepository compteRepo;
	
	
	
	@GetMapping("")
	public ModelAndView list() {
		ModelAndView modelAndView=new ModelAndView("compte/Connexion");
		modelAndView.addObject("comptes", compteRepo.findAll());
		return modelAndView;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete (@PathVariable Integer id) {
		compteRepo.deleteById(id);
		return new ModelAndView("redirect:/compte/");
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		return goEdit(new Joueur());
	}
	
	@GetMapping("/edit")
	public ModelAndView edit (@RequestParam Integer id ) {
		return goEdit(compteRepo.findById(id).get());
	}
	
	private ModelAndView goEdit(Compte compte) {
		ModelAndView modelAndView = new ModelAndView("compte/edit");
		modelAndView.addObject("compte",compte);
		return modelAndView;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid @ModelAttribute Compte compte, BindingResult br) {
		if(br.hasErrors()) {
			return goEdit(compte);
		}
		compteRepo.save(compte);
		return new ModelAndView("redirect:/compte");
		
	}
	
	@GetMapping("/connexion")
	public ModelAndView connect(@RequestParam Integer id ,HttpSession session) {
		session.setAttribute("joueur", compteRepo.findById(id).get());
		return new ModelAndView("Connexion");
	}


}
