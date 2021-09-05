package controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import attraction.model.*;
import attraction.repositories.*;


@Controller
@RequestMapping("/possessions")
public class PossessionController {


	@Autowired
	AchatRepository daoA;
	
	@Autowired
	AttractionRepository daoAt;
	
	@Autowired
	BoutiqueRepository daoB;
	
	@Autowired
	CommoditeRepository daoC;
	
	@Autowired
	EmployeRepository daoE;
	
	@Autowired
	RestaurantRepository daoR;

	double prixAmeliorationAttraction = 5.00;
	double prixAmeliorationBoutique = 5.00;
	double prixAmeliorationRestaurant = 5.00;
	double prixVenteAttraction=100.5;
	double prixVenteBoutique=100.5;
	double prixVenteRestaurant=100.5;
	double prixVenteCommodite=100.5;


	@GetMapping("")
	public ModelAndView listPossessions(HttpSession session) {
		ModelAndView mAv  = new ModelAndView("jeu/Possession");
		List<Achat> allAchat = daoA.findByParc((Parc) session.getAttribute("parc"));
		
		HashMap<Attraction, Integer> attractions = new HashMap<Attraction, Integer>();
		HashMap<Restaurant, Integer> restaurants = new HashMap<Restaurant, Integer>();
		HashMap<Commodite, Integer> commodites = new HashMap<Commodite, Integer>();
		HashMap<Boutique, Integer> boutiques = new HashMap<Boutique, Integer>();
		HashMap<Employe, Integer> employes = new HashMap<Employe, Integer>();
		
		if (!allAchat.isEmpty()){
			for (Achat a : allAchat){
				switch(a.getTypeElement()) 
				{
					case "attraction" : attractions.put((Attraction) a.getElement(), a.getNiveauAmelioration());break;
					case "boutique" : boutiques.put((Boutique) a.getElement(), a.getNiveauAmelioration());break;
					case "restaurant" : restaurants.put((Restaurant) a.getElement(), a.getNiveauAmelioration());break;
					case "commodite" : commodites.put((Commodite) a.getElement(), a.getNbSameElement());break;
					case "employe" : employes.put((Employe) a.getElement(), a.getNbSameElement());break;
				}
			}	
		}
		
		mAv.addObject("display_attraction","block");
		mAv.addObject("display_restaurant","block");
		mAv.addObject("display_commodite","block");
		mAv.addObject("display_boutique","block");
		mAv.addObject("display_employe","block");
		
		mAv.addObject("attractions",attractions);
		mAv.addObject("restaurants",restaurants);
		mAv.addObject("commodites",commodites);
		mAv.addObject("boutiques",boutiques);
		mAv.addObject("employes",employes);
		return mAv;
	}
		
	

	@GetMapping("ameliorer")
	public ModelAndView ameliorationElement(@RequestParam String type,@RequestParam Integer id,HttpSession session) {
		Element element = new Attraction();
		Parc parc = (Parc) session.getAttribute("parc");
		double prix=0;
		switch(type) 
		{
			case "attraction" : element=daoAt.findById(id).get();prix=prixAmeliorationAttraction;break;
			case "boutique" : element=daoB.findById(id).get();prix=prixAmeliorationBoutique;break;
			case "restaurant" : element=daoR.findById(id).get();prix=prixAmeliorationRestaurant;break;
			default : return new ModelAndView("redirect:/possessions");
		}
		Achat achat = daoA.findByElementAndParc(element,parc).get();
		achat.setNiveauAmelioration(achat.getNiveauAmelioration()+1);
		daoA.save(achat);
		
		parc.setArgent(parc.getArgent()-prix);
		session.setAttribute("parc", parc);
		return new ModelAndView("redirect:/possessions");
	}
		
	

	@GetMapping("vendre")
	public ModelAndView vendrePossession(@RequestParam String type,@RequestParam Integer id,HttpSession session) {
		Element element = new Attraction();
		Parc parc = (Parc) session.getAttribute("parc");
		double prix=0;
		switch(type) 
		{
			case "attraction" : element=daoAt.findById(id).get();prix=prixVenteAttraction;break;
			case "boutique" : element=daoB.findById(id).get();prix=prixVenteBoutique;break;
			case "restaurant" : element=daoR.findById(id).get();prix=prixVenteRestaurant;break;
			case "commodite" : element=daoC.findById(id).get();prix=prixVenteCommodite;break;
			case "employe" : element=daoE.findById(id).get();break;
			default : return new ModelAndView("redirect:/possessions");
		}
		Achat achat = daoA.findByElementAndParc(element,parc).get();
		daoA.delete(achat);
		
		parc.setArgent(parc.getArgent()+prix);
		session.setAttribute("parc", parc);
		return new ModelAndView("redirect:/possessions");
	}
}
