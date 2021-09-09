package SoprAjc.ParcAttractionBoot.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SoprAjc.ParcAttractionBoot.exception.CompteException;
import SoprAjc.ParcAttractionBoot.model.Compte;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.repositories.CompteRepository;

@RestController
@RequestMapping("/api/compte")
@CrossOrigin(origins="*")
public class CompteRestController {
	
	@Autowired
	private CompteRepository compteRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compte> getAll(){
		return compteRepo.findAll();
	}
	
	
	@GetMapping("{login}")
	@JsonView(JsonViews.Common.class)
	public Compte getByLogin(@PathVariable String login){
		if (compteRepo.findByLogin(login).isPresent()){
			return compteRepo.findByLogin(login).get();
		}
		throw new CompteException("Login inexistant - impossible d'obtenir");
	}
	
	
	@GetMapping("{id}")
	@JsonView(JsonViews.Common.class)
	public Compte getById(@PathVariable Integer id){
		if (compteRepo.findById(id).isPresent()){
			return compteRepo.findByLogin(login).get();
		}
		throw new CompteException("Login inexistant - impossible d'obtenir");
	}
	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Integer id) {
		Optional<Compte> opt=compteRepo.findById(id);
		if(opt.isPresent())
		{
			compteRepo.deleteById(id);
		} 
		else
		{
			throw new CompteException("Compte inexistant - suppression impossible");
		}
	}
	
	
	@PostMapping("/create")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Compte create(@Valid @RequestBody Compte compte, BindingResult br) {
		if(br.hasErrors()) {
			throw new CompteException(br.getGlobalError().toString());
		}
		else if (compte.getId()!=null || compteRepo.findByLogin(compte.getLogin()).isPresent())
		{
			throw new CompteException("Compte avec des données incorrectes - création impossible");
			
		}
		return compteRepo.save(compte);
	}
	
	
	@PutMapping("/{id}")
	public Compte modify(@Valid @RequestBody Compte compte, BindingResult br, Integer id) {
		if(br.hasErrors()) {
			throw new CompteException(br.getGlobalError().toString());
		}
		Optional <Compte> opt=compteRepo.findById(id);
		if(opt.isPresent()) {
			compte.setId(id);
			return compteRepo.save(compte);
		}
		throw new CompteException("Compte inexistant - modification impossible");
	}
	
	
	@PostMapping("/save")
	public Compte save(@Valid @RequestBody Compte compte, BindingResult br) {
		if(br.hasErrors()) {
			throw new CompteException(br.getGlobalError().toString());
		}
		if(compteRepo.findById(compte.getId()).isPresent()) {
			return compteRepo.save(compte);
		}
		throw new CompteException("Compte inexistant - modification impossible");
	}


}
