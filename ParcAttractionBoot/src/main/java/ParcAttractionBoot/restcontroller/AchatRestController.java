package ParcAttractionBoot.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.exception.AchatException;
import ParcAttractionBoot.model.Achat;
import ParcAttractionBoot.model.Attraction;
import ParcAttractionBoot.model.Boutique;
import ParcAttractionBoot.model.Commodite;
import ParcAttractionBoot.model.Element;
import ParcAttractionBoot.model.Employe;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Parc;
import ParcAttractionBoot.model.Restaurant;
import ParcAttractionBoot.repositories.AchatRepository;
import ParcAttractionBoot.repositories.AttractionRepository;
import ParcAttractionBoot.repositories.BoutiqueRepository;
import ParcAttractionBoot.repositories.CommoditeRepository;
import ParcAttractionBoot.repositories.EmployeRepository;
import ParcAttractionBoot.repositories.ParcRepository;
import ParcAttractionBoot.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api/achat")
@CrossOrigin(origins="*")
public class AchatRestController {

	@Autowired
	AchatRepository daoA;
	@Autowired
	AttractionRepository daoAt;
	@Autowired
	BoutiqueRepository daoB;
	@Autowired
	CommoditeRepository daoC;
	@Autowired
	RestaurantRepository daoR;
	@Autowired
	EmployeRepository daoE;
	@Autowired
	ParcRepository daoP;


	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchat(){
		return daoA.findAll();
	}


	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat get(@PathVariable Integer id){
		Optional<Achat> opt = daoA.findById(id);
		if (opt.isPresent()){
			return opt.get();
		}
		throw new AchatException("Achat demandé inexistant");
	}


	@PostMapping("/create/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat create(@Valid @RequestBody Achat achat, @PathVariable Integer id, BindingResult br){
		if (br.hasErrors()){
			throw new AchatException(br.getGlobalError().toString());
		}
		else if(id==null || daoA.findById(achat.getId()).isPresent() || !daoP.findById(achat.getParc().getId()).isPresent())
		{
			throw new AchatException("Achat avec des données incorrectes - création impossible");			
		}

		switch (achat.getTypeElement()) {
		case "attraction" : 
			if(daoAt.findById(id).isPresent()) 
			{achat.setElement(daoAt.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "boutique" : 
			if(daoB.findById(id).isPresent()) 
			{achat.setElement(daoB.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "restaurant" : 
			if(daoR.findById(id).isPresent()) 
			{achat.setElement(daoR.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "employe" : 
			if(daoE.findById(id).isPresent()) 
			{achat.setElement(daoE.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "commodite" : 
			if(daoC.findById(id).isPresent()) 
			{achat.setElement(daoC.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		default : throw new AchatException("Element innexistant - création achat impossible");
		}
		return daoA.save(achat);
	}


	@PutMapping("/replace/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat replace (@Valid @RequestBody Achat achat,@PathVariable Integer id,BindingResult br){
		if (br.hasErrors()){
			throw new AchatException(br.getGlobalError().toString());
		}
		else if(achat.getId()==null 
				|| achat.getParc().getId()==null 
				|| !daoA.findById(achat.getId()).isPresent()
				|| !daoP.findById(achat.getParc().getId()).isPresent())
		{
			throw new AchatException("Achat demandé inexistant - remplacement impossible");			
		}
		switch (achat.getTypeElement()) {
		case "attraction" : 
			if(daoAt.findById(id).isPresent()) 
			{achat.setElement(daoAt.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "boutique" : 
			if(daoB.findById(id).isPresent()) 
			{achat.setElement(daoB.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "restaurant" : 
			if(daoR.findById(id).isPresent()) 
			{achat.setElement(daoR.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "employe" : 
			if(daoE.findById(id).isPresent()) 
			{achat.setElement(daoE.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		case "commodite" : 
			if(daoC.findById(id).isPresent()) 
			{achat.setElement(daoC.findById(id).get());}
			else
			{throw new AchatException("Element innexistant - création achat impossible");}
			break;
		default : throw new AchatException("Element innexistant - création achat impossible");
		}
		return daoA.save(achat);
	}


	@PatchMapping("/save/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat save (@RequestBody Map<String, Object> fields,BindingResult br,@PathVariable Integer id) {
		Optional<Achat> opt = daoA.findById(id);
		if (opt.isPresent()) {
			Achat achat = opt.get();
			fields.forEach((key, value) -> {
				Field field = ReflectionUtils.findField(Achat.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, achat, value);
			});
			return daoA.save(achat);
		}
		throw new AchatException("Achat demandé inexistant - modification impossible");
	}


	@DeleteMapping("/delete/{id}")
	@JsonView(JsonViews.Common.class)
	public void delete(@PathVariable Integer id){
		if (id==null || id==0)
		{
			throw new AchatException("Achat avec des données incorrectes - suppression impossible");
		}
		else if (!daoA.findById(id).isPresent())
		{
			throw new AchatException("Achat demandé inexistant - suppression impossible");		
		}

		daoA.deleteById(id);
	}


	@GetMapping("/byParc/{id}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByIdParc(@PathVariable Integer id){
		if(id==null || !daoP.findById(id).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		return daoA.findByIdParc(id);
	}


	@PostMapping("/byParc")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByParc(@Valid @RequestBody Parc parc){
		if(parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		return daoA.findByParc(parc);
	}



	@PostMapping("/byElementAndParc/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat getAllAchatByElementAndParc(@Valid @RequestBody Parc parc,@PathVariable Integer id){
		if(parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}

		Optional<Achat> achat = daoA.findByParcAndIdElement(parc,id);
		if (achat.isPresent()) {
			return achat.get();
		}

		throw new AchatException("Achat inexistant");
	}


	@PostMapping("/byTypeElementAndParc/{type}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByTypeElementAndParc(@Valid @RequestBody Parc parc,@PathVariable String type){
		if(parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		else if(!type.equalsIgnoreCase("attraction") &&
				!type.equalsIgnoreCase("boutique") &&
				!type.equalsIgnoreCase("commodite") &&
				!type.equalsIgnoreCase("employe") &&
				!type.equalsIgnoreCase("restaurant") )
		{
			throw new AchatException("Type d'élement inexistant - liste des achats associés impossible");

		}
		return daoA.findByTypeElementAndParc(type,parc);
	}


	@PostMapping("/byElement")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByElement(@Valid @RequestBody Element element){
		boolean exist = false;

		if (element instanceof Attraction) {
			exist = daoAt.findById(element.getId()).isPresent() ? true : false;
		}
		else if (element instanceof Boutique) {
			exist = daoB.findById(element.getId()).isPresent() ? true : false;
		}
		else if (element instanceof Restaurant) {
			exist = daoR.findById(element.getId()).isPresent() ? true : false;
		}
		else if (element instanceof Employe) {
			exist = daoE.findById(element.getId()).isPresent() ? true : false;
		}
		else if (element instanceof Commodite) {
			exist = daoC.findById(element.getId()).isPresent() ? true : false;
		}

		if (exist)
		{
			return daoA.findByElement(element);
		}
		else
		{
			throw new AchatException("Element inexistant - listes des achats associés impossible");
		}
	}


	@PostMapping("/byElement/{type}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByTypeElement(@PathVariable String type){
		if(!type.equalsIgnoreCase("attraction") &&
				!type.equalsIgnoreCase("boutique") &&
				!type.equalsIgnoreCase("commodite") &&
				!type.equalsIgnoreCase("employe") &&
				!type.equalsIgnoreCase("restaurant") )
		{
			throw new AchatException("Type d'élement inexistant - liste des achats associés impossible");

		}
		return daoA.findByTypeElement(type);
	}




	@DeleteMapping("/delete/allByParc")
	@JsonView(JsonViews.Common.class)
	public void deleteAllByParc(@Valid @RequestBody Parc parc,BindingResult br){
		if (br.hasErrors())
		{
			throw new AchatException(br.getGlobalError().toString());
		}
		else if (parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - suppression des achats associés impossible");		
		}
		else
		{
			List<Achat> achats = daoA.findByParc(parc);
			for (Achat a : achats)
			{
				daoA.delete(a);	
			}
		}
	}



	@DeleteMapping("/delete/allByElement")
	@JsonView(JsonViews.Common.class)
	public void deleteAllByElement(@Valid @RequestBody Element element,BindingResult br){
		if (br.hasErrors())
		{
			throw new AchatException(br.getGlobalError().toString());
		}
		else if (element.getId()==null)
		{
			throw new AchatException("Element avec des données incorrectes - suppression des achats associés impossible");		
		}

		boolean exist = false;

		switch (element.getClass().toString())
		{
		case "attraction": exist = daoAt.findById(element.getId()).isPresent() ? true : false; break;
		case "boutique" : exist = daoB.findById(element.getId()).isPresent() ? true : false; break;
		case "employe" : exist = daoE.findById(element.getId()).isPresent() ? true : false; break;
		case "commodite" : exist = daoC.findById(element.getId()).isPresent() ? true : false; break;
		case "restaurant" : exist = daoR.findById(element.getId()).isPresent() ? true : false; break;
		}

		if (exist)
		{
			List<Achat> achats = daoA.findByElement(element);
			for (Achat a : achats)
			{
				daoA.delete(a);	
			}
		}
		else
		{
			throw new AchatException("Element inexistant - suppression des achats associés impossible");
		}
	}



	@DeleteMapping("/delete/byElementAndParc")
	@JsonView(JsonViews.Common.class)
	public void deleteByElementAndParc(@Valid @RequestBody Parc parc,@Valid @RequestBody Element element,BindingResult br){
		if (br.hasErrors())
		{
			throw new AchatException(br.getGlobalError().toString());
		}
		else if (parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - suppression des achats associés impossible");		
		}
		else if (element.getId()==null)
		{
			throw new AchatException("Element avec des données incorrectes - suppression des achats associés impossible");		
		}

		boolean exist = false;

		switch (element.getClass().toString())
		{
		case "attraction": exist = daoAt.findById(element.getId()).isPresent() ? true : false; break;
		case "boutique" : exist = daoB.findById(element.getId()).isPresent() ? true : false; break;
		case "employe" : exist = daoE.findById(element.getId()).isPresent() ? true : false; break;
		case "commodite" : exist = daoC.findById(element.getId()).isPresent() ? true : false; break;
		case "restaurant" : exist = daoR.findById(element.getId()).isPresent() ? true : false; break;
		}

		if (exist){
			List<Achat> achats = daoA.findByParc(parc);
			for (Achat a : achats)
			{
				daoA.delete(a);	
			}	
		}
		else
		{
			throw new AchatException("Element inexistant - suppression des achats associés impossible");
		}
	}


}
