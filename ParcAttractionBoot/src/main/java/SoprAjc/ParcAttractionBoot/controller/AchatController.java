package SoprAjc.ParcAttractionBoot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import SoprAjc.ParcAttractionBoot.model.Achat;
import SoprAjc.ParcAttractionBoot.model.Attraction;
import SoprAjc.ParcAttractionBoot.model.Boutique;
import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.model.Restaurant;
import SoprAjc.ParcAttractionBoot.repositories.AchatRepository;
import SoprAjc.ParcAttractionBoot.repositories.AttractionRepository;
import SoprAjc.ParcAttractionBoot.repositories.BoutiqueRepository;
import SoprAjc.ParcAttractionBoot.repositories.CommoditeRepository;
import SoprAjc.ParcAttractionBoot.repositories.EmployeRepository;
import SoprAjc.ParcAttractionBoot.repositories.ParcRepository;
import SoprAjc.ParcAttractionBoot.repositories.RestaurantRepository;




@Controller
@RequestMapping("/achat")
public class AchatController {
	
	@Autowired
	private AchatRepository achatRepo;
	
	@Autowired
	private ParcRepository parcRepo;

	@Autowired
	private RestaurantRepository rRepo;

	@Autowired
	private AttractionRepository aRepo;

	@Autowired
	private BoutiqueRepository bRepo;

	@Autowired
	private CommoditeRepository cRepo;

	@Autowired
	private EmployeRepository eRepo;
	
	
	
	@GetMapping("")
	public ModelAndView achat(@RequestParam String type,HttpSession session) {
		if(type.equals("restaurant"))
		{
			return goListR(session);
		}
		else if(type.equals("attraction"))
		{
			return goListA(session);
		}
		else if(type.equals("commodite"))
		{
			return goListC();
		}
		else if(type.equals("boutique"))
		{
			return goListB(session);
		}
		else if(type.equals("employe"))
		{
			return goListE();
		}
		return new ModelAndView("jeu/achat");
	}


	
	private ModelAndView goListA(HttpSession session) {
		List<Attraction> allAttractions = aRepo.findAll();
		List<Achat> allAchatA = achatRepo.findByTypeElementAndParc("attraction",(Parc) session.getAttribute("parc"));
		Attraction a1 = null;
		for (int i=0; i<allAchatA.size();i++) 
		{
			a1 = (Attraction) allAchatA.get(i).getElement();

			for (int j=0; j<allAttractions.size();j++)
			{	
				if (a1.getId()==allAttractions.get(j).getId())
				{			
					allAttractions.remove(j);
				}
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("jeu/achat");
		modelAndView.addObject("achatsA", allAttractions);
		return modelAndView;
	}

	
	private ModelAndView goListR(HttpSession session) {
		List<Restaurant> allRestaurants = rRepo.findAll();
		List<Achat> allAchatRestaurants = achatRepo.findByTypeElementAndParc("restaurant",(Parc) session.getAttribute("parc"));
		Restaurant r1 = null;
		for (int i=0; i<allAchatRestaurants.size();i++) 
		{
			r1 = (Restaurant) allAchatRestaurants.get(i).getElement();

			for (int j=0; j<allRestaurants.size();j++)
			{	
				if (r1.getId()==allRestaurants.get(j).getId())
				{			
					allRestaurants.remove(j);
				}
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("jeu/achat");
		modelAndView.addObject("achatsR", allRestaurants);
		return modelAndView;
	}
	
	
	private ModelAndView goListB(HttpSession session) {
		List<Boutique> allB = bRepo.findAll();
		List<Achat> allAchatB = achatRepo.findByTypeElementAndParc("boutique",(Parc) session.getAttribute("parc"));
		Boutique b1 = null;
		for (int i=0; i<allAchatB.size();i++) 
		{
			b1 = (Boutique) allAchatB.get(i).getElement();

			for (int j=0; j<allB.size();j++)
			{	
				if (b1.getId()==allB.get(j).getId())
				{			
					allB.remove(j);
				}
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("jeu/achat");
		modelAndView.addObject("achatsB", allB);
		return modelAndView;
	}
	
	
	private ModelAndView goListC() {
		ModelAndView modelAndView = new ModelAndView("jeu/achat");
		modelAndView.addObject("achatsC", cRepo.findAll());
		return modelAndView;
	}
			

	
	private ModelAndView goListE() {
		ModelAndView modelAndView = new ModelAndView("achat/achat");
		modelAndView.addObject("achatsE", eRepo.findAll());
		return modelAndView;
	}




	//A REVOIR 
	/*
	@PostMapping("/valid")
	public ModelAndView valid(@RequestParam Integer idParc, HttpSession session) {
		Achat achats = new Achat(element, idParc, achatRepo.findByTypeElementAndParc(Achat.getTypeElement(), parc));
		achatRepo.save(achats);
		
		
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("achat/ok");
		modelAndView.addObject("achat", achats);
		return modelAndView;
	

	}
	*/


	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("achat") Achat achat, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("parcs", parcRepo.findAll());
			model.addAttribute("achat", achat);
		}
		achatRepo.save(achat);
		return "redirect:/achat";
	}


}



