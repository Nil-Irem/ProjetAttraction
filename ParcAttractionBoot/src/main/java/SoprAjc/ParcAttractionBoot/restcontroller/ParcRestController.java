package SoprAjc.ParcAttractionBoot.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

import SoprAjc.ParcAttractionBoot.exception.ParcException;
import SoprAjc.ParcAttractionBoot.model.Joueur;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.repositories.ParcRepository;

@RestController
@RequestMapping("/api/parc")
public class ParcRestController {
	
	@Autowired
	private ParcRepository parcRepo;
	
	@GetMapping("/{joueur}")
	@JsonView(JsonViews.Common.class)
	public List<Parc> getParcs(@PathVariable Joueur joueur)
	{
		return (List<Parc>) parcRepo.findByJoueur(joueur);
	}
	
	@GetMapping("/{id_parc}")
	@JsonView(JsonViews.Common.class)
	public Parc getParc(@PathVariable Integer id)
	{	Optional <Parc> p = parcRepo.findById(id);
		
		if(p.isPresent()){
			return p.get();
		}else{
			throw new ParcException("Parc inexistant - impossible à obtenir");
		}	
	}
	
	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Parc create(@RequestBody Parc parc, BindingResult br)
	{
		if(br.hasErrors()){
			throw new ParcException();
		}
		return parcRepo.save(parc);
	}
	
	
	@PutMapping("/{id}") 
	public Parc replace(@RequestBody Parc parc, BindingResult br, @PathVariable Integer id) {
		
		if(br.hasErrors()) {
			throw new ParcException();
		}
		Optional<Parc> opt = parcRepo.findById(id);
		if(opt.isPresent()) {
			parc.setId(id);
			return parcRepo.save(parc);
		}
		throw new ParcException("Parc inexistant - modification impossible");
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteParc(@PathVariable Integer id) {
		Optional<Parc> opt = parcRepo.findById(id);
		
		if(opt.isPresent()) {
			parcRepo.deleteById(id);
		}else {
		
		throw new ParcException("Parc inexistant - suppression impossible");
		}
		
	}
}
