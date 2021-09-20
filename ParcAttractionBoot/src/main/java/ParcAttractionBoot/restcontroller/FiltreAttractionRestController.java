package ParcAttractionBoot.restcontroller;

import java.util.List;

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
import ParcAttractionBoot.model.Attraction;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.AttractionRepository;

@RestController
@RequestMapping("/api/filtre/attraction")
@CrossOrigin(origins="*")
public class FiltreAttractionRestController {


	@Autowired
	AttractionRepository daoA;
	
	
	@PutMapping("/nom")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionNom(@RequestParam String partNom){
		if (partNom == null) {
			throw new FilterException("Nom null - attraction impossible à obtenir");
		}
		return daoA.findByNomContaining(partNom);
	}
	
	
	@GetMapping("/taille/{tailleMin}&{tailleMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionTaille(@PathVariable(required=false) Double tailleMin,@PathVariable(required=false) Double tailleMax){
		if (tailleMin==null && tailleMax==null) 
		{
			return daoA.findAll();
		}
		else if (tailleMin==null)
		{
			return daoA.findByTailleLessThan(tailleMax);
		}
		else if (tailleMax==null)
		{
			return daoA.findByTailleGreaterThan(tailleMin);
		}
		else {
			return daoA.findByTailleBetween(tailleMin,tailleMax);
		}
	}
	
	
	@GetMapping("/prixacquisition/{prixAcquisitionMin}&{prixAcquisitionMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionPrixAcquisition(@PathVariable(required=false) Double prixAcquisitionMin,@PathVariable(required=false) Double prixAcquisitionMax){
		if (prixAcquisitionMin==null && prixAcquisitionMax==null) 
		{
			return daoA.findAll();
		}
		else if (prixAcquisitionMin==null)
		{
			return daoA.findByPrixAcquisitionLessThan(prixAcquisitionMax);
		}
		else if (prixAcquisitionMax==null)
		{
			return daoA.findByPrixAcquisitionGreaterThan(prixAcquisitionMin);
		}
		else
		{
			return daoA.findByPrixAcquisitionBetween(prixAcquisitionMin,prixAcquisitionMax);
		}
	}
	
	
	@GetMapping("/prixfonctionnement/{prixFonctionnementMin}&{prixFonctionnementMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionPrixFonctionnement(@PathVariable(required=false) Double prixFonctionnementMin,@PathVariable(required=false) Double prixFonctionnementMax){
		if (prixFonctionnementMax==null && prixFonctionnementMin==null) {
			return daoA.findAll();
		}
		else if (prixFonctionnementMin==null)
		{
			return daoA.findByPrixFonctionnementLessThan(prixFonctionnementMax);
		}
		else if (prixFonctionnementMax==null)
		{
			return daoA.findByPrixFonctionnementGreaterThan(prixFonctionnementMin);
		}
		else {
			return daoA.findByPrixFonctionnementBetween(prixFonctionnementMin,prixFonctionnementMax);
		}
	}
	
	
	@GetMapping("/nbamelioration/{nbAmelioraMin}&{nbAmelioraMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionNbAmelioration(@PathVariable(required=false) Integer nbAmelioraMin,@PathVariable(required=false) Integer nbAmelioraMax){
		
		if (nbAmelioraMax==null && nbAmelioraMin==null)
		{
			return daoA.findAll();
		}
		else if (nbAmelioraMin==null)
		{
			return daoA.findByNbAmeliorationLessThan(nbAmelioraMax);
		}
		else if (nbAmelioraMax==null)
		{
			return daoA.findByNbAmeliorationGreaterThan(nbAmelioraMin);
		}
		else
		{
			return daoA.findByPrixFonctionnementBetween(nbAmelioraMin,nbAmelioraMax);
		}
	}
	
	
	@GetMapping("/tauxincident/{tauxIncidentMin}&{tauxIncidentMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionTauxIncident(@PathVariable(required=false) Double tauxIncidentMin,@PathVariable(required=false) Double tauxIncidentMax){
		
		if (tauxIncidentMax==null && tauxIncidentMin==null) {
			return daoA.findAll();
		}
		else if (tauxIncidentMin==null)
		{
			return daoA.findByTauxIncidentLessThan(tauxIncidentMax);
		}
		else if (tauxIncidentMax==null)
		{
			return daoA.findByTauxIncidentGreaterThan(tauxIncidentMin);
		}
		else
		{
			return daoA.findByTauxIncidentBetween(tauxIncidentMin,tauxIncidentMax);
		}
	}
	
	
	@GetMapping("/affluence/{affluenceMin}&{affluenceMax}")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> filtreAttractionPrixFonctionnementMax(@PathVariable(required=false) Integer affluenceMin,@PathVariable(required=false) Integer affluenceMax){
		
		if (affluenceMax==null && affluenceMin==null)
		{
			return daoA.findAll();
		}
		else if (affluenceMin==null)
		{
			return daoA.findByAffluenceLessThan(affluenceMax);
		}
		else if (affluenceMax==null)
		{
			return daoA.findByAffluenceGreaterThan(affluenceMin);
		}
		else
		{
			return daoA.findByAffluenceBetween(affluenceMin,affluenceMax);
		}
	}
	
}
