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

import com.mysql.cj.Session;

import attraction.model.Admin;
import attraction.model.Compte;
import attraction.model.Difficulte;
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
	
	
	
	@PostMapping("/connexion")
	public ModelAndView logIn(@Valid @ModelAttribute Joueur connected,BindingResult br,HttpSession session) {
		String login = connected.getLogin();
		String password = connected.getPassword();
		if (br.hasErrors())
		{
			System.out.println(br.getAllErrors());
		}
		else if (compteRepo.findByLoginAndPassword(login,password).isEmpty())
		{
			System.out.println("Login ou mot de passe incorect");
		}
		else
		{
			Compte compte = compteRepo.findByLoginAndPassword(login,password).get();
			if(compte instanceof Joueur) 
			{
				session.setAttribute("joueur", (Joueur) compte);
				return new ModelAndView("redirect:/parcs");
			}
//			else if (compte instanceof Admin) 
//			{
//				session.setAttribute("admin", (Admin) compte);
//				return new ModelAndView("MenuAdmin");
//			}
		}
		return new ModelAndView("redirect:/connexion");
	}
	
	
	
	
	@PostMapping("/inscription")
	public ModelAndView signIn(@Valid @ModelAttribute Joueur joueur,BindingResult br,HttpSession session) {
		if (compteRepo.findByLogin(joueur.getLogin()).isEmpty()) 
		{
			session.setAttribute("joueur", compteRepo.save(joueur));
			return new ModelAndView("redirect:/parcs");			
		}
		else 
		{
			System.out.println("Login déjà pris");
		}
		
		return new ModelAndView("redirect:/inscription");
	}


}
