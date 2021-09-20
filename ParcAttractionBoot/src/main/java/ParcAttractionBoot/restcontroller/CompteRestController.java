package ParcAttractionBoot.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import ParcAttractionBoot.exception.CompteException;
import ParcAttractionBoot.model.Achat;
import ParcAttractionBoot.model.Admin;
import ParcAttractionBoot.model.Compte;
import ParcAttractionBoot.model.Connexion;
import ParcAttractionBoot.model.Joueur;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Parc;
import ParcAttractionBoot.repositories.AchatRepository;
import ParcAttractionBoot.repositories.CompteRepository;
import ParcAttractionBoot.repositories.ParcRepository;

@RestController
@RequestMapping("/api/compte")
@CrossOrigin(origins="*")
public class CompteRestController {
	
	@Autowired
	private CompteRepository compteRepo;
	@Autowired
	private ParcRepository parcRepo;
	@Autowired
	private AchatRepository achatRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compte> getAll(){
		List<Compte> comptes = compteRepo.findAll();
		for(Compte c :comptes) {
			if(c instanceof Joueur) {
				c.setIsJoueur(true);
			}
			else if(c instanceof Admin) {
				c.setIsJoueur(false);
			}
		}
		return comptes;
	}
	
	
	@GetMapping("/login={login}")
	@JsonView(JsonViews.Common.class)
	public Compte getByLogin(@PathVariable String login){
		if (compteRepo.findByLogin(login).isPresent()){
			return compteRepo.findByLogin(login).get();
		}
		throw new CompteException("Login inexistant - impossible d'obtenir");
	}
	
	
	@GetMapping("/id={id}")
	@JsonView(JsonViews.Common.class)
	public Compte getById(@PathVariable Integer id){
		if (compteRepo.findById(id).isPresent()){
			return compteRepo.findById(id).get();
		}
		throw new CompteException("Compte inexistant - impossible d'obtenir");
	}
	
	
	@PostMapping("/create")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Compte create(@RequestBody Connexion connexion, BindingResult br) {
		if(br.hasErrors()) {
			throw new CompteException(br.getGlobalError().toString());
		}
		else if (compteRepo.findById(connexion.getId()).isPresent() || compteRepo.findByLogin(connexion.getLogin()).isPresent())
		{
			throw new CompteException("Compte avec des données incorrectes - création impossible");
		}

		connexion.setPassword(encoder.encode(connexion.getPassword()));
		if (connexion.isJoueur()) {
			return compteRepo.save(new Joueur(connexion.getLogin(),connexion.getPassword()));
		}
		else {
			return compteRepo.save(new Admin(connexion.getLogin(),connexion.getPassword()));
		}
	}

	
	
	@PutMapping("/replace")
	public Compte modify(@Valid @RequestBody Compte compte, BindingResult br) {
		if(br.hasErrors()) {
			throw new CompteException(br.getGlobalError().toString());
		}
		else if(compte.getId()!=null || !compteRepo.findById(compte.getId()).isPresent())
		{
			throw new CompteException("Compte avec des données incorrectes - modification impossible");			
		}
		
		return compteRepo.save(compte);
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
	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Integer id) {
		Optional<Compte> opt=compteRepo.findById(id);
		if(opt.isPresent())
		{
			if (opt.get() instanceof Joueur) {
				List<Parc> parcs = parcRepo.findByJoueur((Joueur) opt.get());
				for (Parc p : parcs) {
					List<Achat> achats = achatRepo.findByParc(p);
					for (Achat a : achats) {
						achatRepo.delete(a);
					}
					parcRepo.delete(p);
				}
				compteRepo.deleteById(id);				
			}
			else if (opt.get() instanceof Admin) {
				throw new CompteException("Compte admin - suppression impossible");
			}
		} 
		else
		{
			throw new CompteException("Compte inexistant - suppression impossible");
		}
	}
	
	
	@GetMapping("/loginIsPresent/{login}")
	@JsonView(JsonViews.Common.class)
	public boolean LoginIsPresent(@PathVariable String login){
		return compteRepo.findByLogin(login).isPresent();
	}
	
	
	@PostMapping("/connexion")
	@JsonView(JsonViews.Common.class)
	public Compte Connexion(@RequestBody Connexion connect){
		Optional<Compte> opt=compteRepo.findByLogin(connect.getLogin());
		if (opt.isPresent() && encoder.matches(connect.getPassword(),opt.get().getPassword())) {
			Compte compte = opt.get();
			if (compte instanceof Joueur) {
				compte.setIsJoueur(true);
			}
			else if (compte instanceof Admin) {
				compte.setIsJoueur(false);
			}
			return compte;
		}
		return null;
	}
	

}
