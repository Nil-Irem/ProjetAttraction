package ParcAttractionBoot.restcontroller;

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

import ParcAttractionBoot.exception.ParcException;
import ParcAttractionBoot.model.Achat;
import ParcAttractionBoot.model.Joueur;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Parc;
import ParcAttractionBoot.repositories.AchatRepository;
import ParcAttractionBoot.repositories.CompteRepository;
import ParcAttractionBoot.repositories.ParcRepository;

@RestController
@RequestMapping("/api/parc")
@CrossOrigin(origins="*")
public class ParcRestController {

	@Autowired
	private AchatRepository daoA;
	@Autowired
	private ParcRepository daoP;
	@Autowired
	private CompteRepository daoCpt;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Parc> getAll()
	{
		return daoP.findAll();
	}
	
	
	@PostMapping("/byJoueur")
	@JsonView(JsonViews.Common.class)
	public List<Parc> getParcByJoueur(@Valid @RequestBody Joueur joueur)
	{
		if (joueur.getId()==null || !daoCpt.findById(joueur.getId()).isPresent()) {
			throw new ParcException("Joueur avec des données incorrectes - parc impossible à obtenir");	
		}
		return daoP.findByJoueur(joueur);
	}
	
	
	@GetMapping("/byJoueur/{nom}")
	@JsonView(JsonViews.Common.class)
	public Parc getParcByJoueurAndNom(@Valid @RequestBody Joueur joueur,@PathVariable String nom)
	{
		if (joueur.getId()==null || !daoCpt.findById(joueur.getId()).isPresent()) {
			throw new ParcException("Joueur avec des données incorrectes - parc impossible à obtenir");	
		}
		
		Optional <Parc> p = daoP.findByNomParcAndJoueur(nom,joueur);
		
		if(p.isPresent())
		{
			return p.get();
		}
		else
		{
			return null;
		}
	}
	
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Parc getParcById(@PathVariable Integer id)
	{	
		Optional <Parc> p = daoP.findById(id);
		
		if(p.isPresent())
		{
			return p.get();
		}
		else
		{
			throw new ParcException("Parc inexistant - impossible à obtenir");
		}	
	}
	
	
	@PostMapping("/create")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Parc create(@RequestBody Parc parc, BindingResult br)
	{
		if(br.hasErrors())
		{
			throw new ParcException(br.getGlobalError().toString());
		}
		else if(parc.getId()!=0
				|| parc.getJoueur().getId()==null
				|| !daoCpt.findById(parc.getJoueur().getId()).isPresent()
				|| daoP.findById(parc.getId()).isPresent())
		{
			throw new ParcException("Parc avec des données incorrectes - impossible à créer");
		}
		
		try {
			parc.setArgent(parc.getTypeDifficulte().getArgent());
			parc.setTaille(parc.getTypeDifficulte().getTailleParc());
			return daoP.save(parc);
		}
		catch(Exception e) {
			throw new ParcException("exception");
		}
	}
	
	
	@PutMapping("/replace") 
	public Parc replace(@RequestBody Parc parc, BindingResult br) {		
		if(br.hasErrors()) {
			throw new ParcException(br.getGlobalError().toString());
		}
		else if(parc.getId()==null || !daoP.findById(parc.getId()).isPresent())
		{
			throw new ParcException("Parc avec des données incorrectes - modification impossible");		
		}

		return daoP.save(parc);
	}
	
	
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteParc(@PathVariable Integer id) {
		Optional<Parc> opt = daoP.findById(id);
		
		if(opt.isPresent()) 
		{
			List<Achat> achats = daoA.findByParc(opt.get());
			for (Achat a : achats) {
				daoA.delete(a);
			}
			daoP.deleteById(id);
		}
		else 
		{
			throw new ParcException("Parc inexistant - suppression impossible");
		}	
	}

	@PostMapping("/nomIsPresent/{nom}")
	@JsonView(JsonViews.Common.class)
	public boolean nomIsPresent(@RequestBody Joueur joueur,@PathVariable String nom){
		return getParcByJoueurAndNom(joueur, nom)==null?false:true;
	}
	
}
