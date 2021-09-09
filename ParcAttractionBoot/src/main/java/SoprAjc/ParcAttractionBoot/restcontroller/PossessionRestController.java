package SoprAjc.ParcAttractionBoot.restcontroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SoprAjc.ParcAttractionBoot.exception.AttractionException;
import SoprAjc.ParcAttractionBoot.exception.BoutiqueException;
import SoprAjc.ParcAttractionBoot.exception.CommoditeException;
import SoprAjc.ParcAttractionBoot.exception.EmployeException;
import SoprAjc.ParcAttractionBoot.exception.ParcException;
import SoprAjc.ParcAttractionBoot.exception.RestaurantException;
import SoprAjc.ParcAttractionBoot.model.Achat;
import SoprAjc.ParcAttractionBoot.model.Attraction;
import SoprAjc.ParcAttractionBoot.model.Boutique;
import SoprAjc.ParcAttractionBoot.model.Commodite;
import SoprAjc.ParcAttractionBoot.model.Element;
import SoprAjc.ParcAttractionBoot.model.Employe;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.model.Restaurant;
import SoprAjc.ParcAttractionBoot.repositories.AchatRepository;
import SoprAjc.ParcAttractionBoot.repositories.AttractionRepository;
import SoprAjc.ParcAttractionBoot.repositories.BoutiqueRepository;
import SoprAjc.ParcAttractionBoot.repositories.CommoditeRepository;
import SoprAjc.ParcAttractionBoot.repositories.EmployeRepository;
import SoprAjc.ParcAttractionBoot.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api/possessions")
@CrossOrigin(origins = "*")
public class PossessionRestController {

	@Autowired
	private AttractionRepository aRepo;
	@Autowired
	private BoutiqueRepository bRepo;
	@Autowired
	private CommoditeRepository cRepo;
	@Autowired
	private EmployeRepository eRepo;
	@Autowired
	private AchatRepository achatRepo;
	@Autowired
	private RestaurantRepository rRepo;


	@GetMapping("/attraction")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> getAllAttractionsP(@Valid @RequestBody Parc parc){

		List<Achat> allA = achatRepo.findByParc(parc);
		List<Attraction> allAttractions = new ArrayList();

		if (!allA.isEmpty()){
			for (Achat a : allA){
				if(a.getTypeElement()=="attraction") 
				{

					Attraction att = (Attraction) a.getElement();
					allAttractions.add(att);               

				}
			}	
		}
		return allAttractions;

	}

	@GetMapping("/boutique")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> getAllBoutiquesP(@Valid @RequestBody Parc parc){
		List<Achat> allA = achatRepo.findByParc(parc);
		List<Boutique> allB = new ArrayList();

		if (!allA.isEmpty()){
			for (Achat a : allA){
				if(a.getTypeElement()=="boutique") 
				{

					Boutique b = (Boutique) a.getElement();
					allB.add(b);               

				}
			}	
		}
		return allB;

	}


	@GetMapping("/commodite")
	@JsonView(JsonViews.Common.class)
	public List<Commodite> getAllCommoditesP(@Valid @RequestBody Parc parc){
		List<Achat> allA = achatRepo.findByParc(parc);
		List<Commodite> allC = new ArrayList();

		if (!allA.isEmpty()){
			for (Achat a : allA){
				if(a.getTypeElement()=="commodite") 
				{

					Commodite c = (Commodite) a.getElement();
					allC.add(c);               

				}
			}	
		}
		return allC;

	}

	@GetMapping("/employe")
	@JsonView(JsonViews.Common.class)
	public List<Employe> getAllEmployesP(@Valid @RequestBody Parc parc){
		List<Achat> allA = achatRepo.findByParc(parc);
		List<Employe> allE = new ArrayList();

		if (!allA.isEmpty()){
			for (Achat a : allA){
				if(a.getTypeElement()=="employe") 
				{

					Employe e = (Employe) a.getElement();
					allE.add(e);               

				}
			}	
		}
		return allE;

	}


	@GetMapping("/restaurant")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> getAllRestaurantsP(@Valid @RequestBody Parc parc){
		List<Achat> allA = achatRepo.findByParc(parc);
		List<Restaurant> allR = new ArrayList();

		if (!allA.isEmpty()){
			for (Achat a : allA){
				if(a.getTypeElement()=="restaurant") 
				{

					Restaurant r = (Restaurant) a.getElement();
					allR.add(r);               

				}
			}	
		}
		return allR;

	}


	//get possession par id?

	@GetMapping("ameliorer")
	public Element ameliorationElement(@RequestBody Map<String, Object> fields, @PathVariable Integer id, @RequestParam String type) {
		switch(type) 
		{
		case "attraction" : 
			Optional<Attraction> opt = aRepo.findById(id);

			if (opt.isPresent()) {
				Attraction attractionEnBase = opt.get();
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Attraction.class, key);
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, attractionEnBase, value);
				});
				return aRepo.save(attractionEnBase);	
			}
			else {
				throw new AttractionException("Attraction inexistante - amélioration impossible");
			} 

		case "boutique" : 
			Optional<Boutique> optb = bRepo.findById(id);

			if (optb.isPresent()) {
				Boutique boutiqueEnBase = optb.get();
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Boutique.class, key);
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, boutiqueEnBase, value);
				});
				return bRepo.save(boutiqueEnBase);
			}
			else {
				throw new BoutiqueException("Boutique inexistante - amélioration impossible");
			} 
		case "restaurant" : 
			Optional<Restaurant> optr = rRepo.findById(id);

			if (optr.isPresent()) {
				Restaurant restaurantEnBase = optr.get();
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Restaurant.class, key);
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, restaurantEnBase, value);
				});
				return rRepo.save(restaurantEnBase);
			}
			else {
				throw new RestaurantException("Restaurant inexistant - amélioration impossible");
			} 
		}
		throw new ParcException("Amélioration impossible dans le parc");
	}

	@GetMapping("/vendre/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vendrePossession(@PathVariable Integer id, @RequestParam String type){
		switch(type) 
		{
		case "attraction":
			Optional<Attraction> opt = aRepo.findById(id);
			if (opt.isPresent()) {
				aRepo.deleteById(id);
			} else {
				throw new AttractionException("Attraction inexistante - suppression impossible");
			} break;

		case "boutique":
			Optional<Boutique> optb = bRepo.findById(id);
			if (optb.isPresent()) {
				bRepo.deleteById(id);
			} else {
				throw new BoutiqueException("Boutique inexistante - suppression impossible");
			} break;

		case "restaurant":
			Optional<Restaurant> optr = rRepo.findById(id);
			if (optr.isPresent()) {
				rRepo.deleteById(id);
			} else {
				throw new RestaurantException("Restaurant inexistant - suppression impossible");
			} break;

		case "commodite":
			Optional<Commodite> optc = cRepo.findById(id);
			if (optc.isPresent()) {
				cRepo.deleteById(id);
			} else {
				throw new CommoditeException("Commodité inexistante - suppression impossible");
			} break;

		case "employe":
			Optional<Employe> opte = eRepo.findById(id);
			if (opte.isPresent()) {
				eRepo.deleteById(id);
			} else {
				throw new EmployeException("Employé inexistant - suppression impossible");
			} break;
		}

		//achatRepo.deleteById(id);

	}



}
