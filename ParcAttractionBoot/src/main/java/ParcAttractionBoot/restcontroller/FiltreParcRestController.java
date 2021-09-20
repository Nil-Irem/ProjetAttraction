package ParcAttractionBoot.restcontroller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.exception.FilterException;
import ParcAttractionBoot.model.Difficulte;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Parc;
import ParcAttractionBoot.repositories.ParcRepository;

@RestController
@RequestMapping("/api/filtre/parc")
@CrossOrigin(origins="*")
public class FiltreParcRestController {

	@Autowired
	private ParcRepository daoP;
	
	
	@PutMapping("/nom")
	@JsonView(JsonViews.Common.class)
	public List<Parc> filtreParcNom(@RequestParam String partNom){
		if (partNom == null) 
		{
			throw new FilterException("Nom null - Parc impossible à obtenir");
		}
		return daoP.findByNomParcContaining(partNom);
	}
	
	
	@GetMapping("/taille/{tailleMin}&{tailleMax}")
	@JsonView(JsonViews.Common.class)
	public List<Parc> filtreParcTaille(@PathVariable int tailleMin,@PathVariable int tailleMax){
		
		if (tailleMax <= 0 && tailleMin <= 0) {
			return daoP.findAll();
		}
		else if (tailleMax <= 0 && tailleMin > 0) {
			return daoP.findByTailleGreaterThan(tailleMin);
		}
		else if (tailleMax > 0 && tailleMin <= 0) {
			return daoP.findByTailleLessThan(tailleMax);
		}
		else {
			Set<Parc> ParcMin = new HashSet<Parc>(daoP.findByTailleGreaterThan(tailleMin));
			Set<Parc> ParcMax = new HashSet<Parc>(daoP.findByTailleLessThan(tailleMax));
			ParcMin.removeAll(ParcMax);
			ParcMax.addAll(ParcMin);
			
			return new ArrayList<Parc>(ParcMax);
		}
	}

	
	@GetMapping("/nbjour/{nbjourMin}&{nbjourMax}")
	@JsonView(JsonViews.Common.class)
	public List<Parc> filtreParcNbjour(@PathVariable int nbjourMin,@PathVariable int nbjourMax){
		
		if (nbjourMax <= 0 && nbjourMin <= 0) {
			return daoP.findAll();
		}
		else if (nbjourMax <= 0 && nbjourMin > 0) {
			return daoP.findByNbjourGreaterThan(nbjourMin);
		}
		else if (nbjourMax > 0 && nbjourMin <= 0) {
			return daoP.findByNbjourLessThan(nbjourMax);
		}
		else {
			Set<Parc> ParcMin = new HashSet<Parc>(daoP.findByNbjourGreaterThan(nbjourMin));
			Set<Parc> ParcMax = new HashSet<Parc>(daoP.findByNbjourLessThan(nbjourMax));
			ParcMin.removeAll(ParcMax);
			ParcMax.addAll(ParcMin);
			
			return new ArrayList<Parc>(ParcMax);
		}
	}
	
	
	@GetMapping("/argent/{argentMin}&{argentMax}")
	@JsonView(JsonViews.Common.class)
	public List<Parc> filtreParcArgent(@PathVariable int argentMin,@PathVariable int argentMax){
		
		if (argentMax <= 0 && argentMin <= 0) {
			return daoP.findAll();
		}
		else if (argentMax <= 0 && argentMin > 0) {
			return daoP.findByArgentGreaterThan(argentMin);
		}
		else if (argentMax > 0 && argentMin <= 0) {
			return daoP.findByArgentLessThan(argentMax);
		}
		else {
			Set<Parc> ParcMin = new HashSet<Parc>(daoP.findByArgentGreaterThan(argentMin));
			Set<Parc> ParcMax = new HashSet<Parc>(daoP.findByArgentLessThan(argentMax));
			ParcMin.removeAll(ParcMax);
			ParcMax.addAll(ParcMin);
			
			return new ArrayList<Parc>(ParcMax);
		}
	}
	
	
	@GetMapping("/difficulte/{typeDifficulte}")
	@JsonView(JsonViews.Common.class)
	public List<Parc> filtreParcPrixAcquisition(@PathVariable String typeDifficulte){
		Difficulte diff;
		
		try {
			diff = Difficulte.valueOf(typeDifficulte);
			return daoP.findByTypeDifficulte(diff);
		}
		catch (Exception e)
		{
			throw new FilterException("Difficulte inexistante - Parc impossible à obtenir");
		}	
	}

}
