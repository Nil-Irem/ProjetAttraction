package SoprAjc.ParcAttractionBoot.restcontroller;

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

import SoprAjc.ParcAttractionBoot.exception.AchatException;
import SoprAjc.ParcAttractionBoot.model.Achat;
import SoprAjc.ParcAttractionBoot.model.Element;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.repositories.*;

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
	
	
	@PostMapping("/create")
	@JsonView(JsonViews.Common.class)
	public Achat create(@Valid @RequestBody Achat achat,BindingResult br){
		if (br.hasErrors()){
			throw new AchatException(br.getGlobalError().toString());
		}
		else if(achat.getId()!=null || achat.getParc().getId()==null)
		{
			throw new AchatException("Achat avec des données incorrectes - création impossible");			
		}
		return daoA.save(achat);
	}
	
	
	@PutMapping("/replace")
	@JsonView(JsonViews.Common.class)
	public Achat replace (@Valid @RequestBody Achat achat,BindingResult br){
		if (br.hasErrors()){
			throw new AchatException(br.getGlobalError().toString());
		}
		else if(achat.getId()==null || achat.getParc().getId()==null || !daoA.findById(achat.getId()).isPresent())
		{
			throw new AchatException("Achat demandé inexistant - remplacement impossible");			
		}
		return daoA.save(achat);
	}
	
	
	@PatchMapping("/save")
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
	
	
	@DeleteMapping("/delete")
	@JsonView(JsonViews.Common.class)
	public void delete(@Valid @RequestBody Achat achat,BindingResult br){
		if (br.hasErrors())
		{
			throw new AchatException(br.getGlobalError().toString());
		}
		else if (achat.getId()==null || achat.getParc().getId()==null)
		{
			throw new AchatException("Achat avec des données incorrectes - suppression impossible");
		}
		else if (!daoA.findById(achat.getId()).isPresent())
		{
			throw new AchatException("Achat demandé inexistant - suppression impossible");		
		}
		else
		{
			daoA.delete(achat);	
		}
	}
	
	
	@GetMapping("/byParc/{id}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByIdParc(@PathVariable Integer id){
		if(!daoP.findById(id).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		return daoA.findByIdParc(id);
	}
	
	
	@PostMapping("/byParc")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByParc(@Valid @RequestBody Parc parc){
		if(!daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		return daoA.findByParc(parc);
	}


	@PostMapping("/byElementAndParc/{type}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllAchatByTypeElementAndParc(@Valid @RequestBody Parc parc,@PathVariable String type){
		if(!daoP.findById(parc.getId()).isPresent())
		{
			throw new AchatException("Parc inexistant - liste des achats associés impossible");
		}
		if(!type.equalsIgnoreCase("attraction") &&
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
