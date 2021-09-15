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
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Restaurant;
import ParcAttractionBoot.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api/filtre/restaurant")
@CrossOrigin(origins="*")
public class FiltreRestaurantRestController {
	
	@Autowired
	RestaurantRepository daoR;

	
	@PutMapping("/nom")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantNom(@RequestParam String partNom){
		if (partNom == null) {
			throw new FilterException("Nom null - restaurant impossible à obtenir");
		}
		return daoR.findByNomContaining(partNom);
	}
	
	
	@GetMapping("/taille/{tailleMin}&{tailleMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantTaille(@PathVariable(required=false) Double tailleMin,@PathVariable(required=false) Double tailleMax){
		
		if (tailleMax==null && tailleMin==null) {
			return daoR.findAll();
		}
		else if (tailleMin==null)
		{
			return daoR.findByTailleLessThan(tailleMax);
		}
		else if (tailleMax==null)
		{
			return daoR.findByTailleGreaterThan(tailleMin);
		}
		else {
			return daoR.findByTailleBetween(tailleMin,tailleMax);
		}
	}
	
	
	@GetMapping("/prixacquisition/{prixAcquisitionMin}&{prixAcquisitionMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantPrixAcquisition(@PathVariable(required=false) Double prixAcquisitionMin,@PathVariable(required=false) Double prixAcquisitionMax){
		
		if (prixAcquisitionMax==null && prixAcquisitionMin==null) {
			return daoR.findAll();
		}
		else if (prixAcquisitionMin==null)
		{
			return daoR.findByPrixAcquisitionLessThan(prixAcquisitionMax);
		}
		else if (prixAcquisitionMax==null)
		{
			return daoR.findByPrixAcquisitionGreaterThan(prixAcquisitionMin);
		}
		else {
			return daoR.findByPrixAcquisitionBetween(prixAcquisitionMin,prixAcquisitionMax);
		}
	}

	
	
	@GetMapping("/prixfonctionnement/{prixFonctionnementMin}&{prixFonctionnementMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantPrixFonctionnement(@PathVariable(required=false) Double prixFonctionnementMin,@PathVariable(required=false) Double prixFonctionnementMax){
		
		if (prixFonctionnementMax==null && prixFonctionnementMin==null) {
			return daoR.findAll();
		}
		else if (prixFonctionnementMin==null)
		{
			return daoR.findByPrixFonctionnementLessThan(prixFonctionnementMax);
		}
		else if (prixFonctionnementMax==null)
		{
			return daoR.findByPrixFonctionnementGreaterThan(prixFonctionnementMin);
		}
		else {
			return daoR.findByPrixFonctionnementBetween(prixFonctionnementMin,prixFonctionnementMax);
		}
	}
	
	
	@GetMapping("/nbamelioration/{nbAmelioraMin}&{nbAmelioraMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantNbAmelioration(@PathVariable(required=false) Integer nbAmelioraMin,@PathVariable(required=false) Integer nbAmelioraMax){
		
		if (nbAmelioraMax==null && nbAmelioraMin==null) {
			return daoR.findAll();
		}
		else if (nbAmelioraMin==null)
		{
			return daoR.findByNbAmeliorationLessThan(nbAmelioraMax);
		}
		else if (nbAmelioraMax==null)
		{
			return daoR.findByNbAmeliorationGreaterThan(nbAmelioraMin);
		}
		else {
			return daoR.findByPrixFonctionnementBetween(nbAmelioraMin,nbAmelioraMax);
		}
	}
	
	
	@GetMapping("/tauxincident/{tauxIncidentMin}&{tauxIncidentMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantTauxIncident(@PathVariable(required=false) Double tauxIncidentMin,@PathVariable(required=false) Double tauxIncidentMax){
		
		if (tauxIncidentMax==null && tauxIncidentMin==null) {
			return daoR.findAll();
		}
		else if (tauxIncidentMin==null)
		{
			return daoR.findByTauxIncidentLessThan(tauxIncidentMax);
		}
		else if (tauxIncidentMax==null)
		{
			return daoR.findByTauxIncidentGreaterThan(tauxIncidentMin);
		}
		else {
			return daoR.findByTauxIncidentBetween(tauxIncidentMin,tauxIncidentMax);
		}
	}
	
	
	@GetMapping("/affluence/{affluenceMin}&{affluenceMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantAffluence(@PathVariable(required=false) Integer affluenceMin,@PathVariable(required=false) Integer affluenceMax){
		
		if (affluenceMax==null && affluenceMin==null) {
			return daoR.findAll();
		}
		else if (affluenceMin==null)
		{
			return daoR.findByAffluenceLessThan(affluenceMax);
		}
		else if (affluenceMax==null)
		{
			return daoR.findByAffluenceGreaterThan(affluenceMin);
		}
		else {
			return daoR.findByAffluenceBetween(affluenceMin,affluenceMax);
		}
	}
	
	
	@GetMapping("/revenujourpersonne/{revenuJourPersonneMin}&{revenuJourPersonneMax}")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> filtreRestaurantRevenuJourPersonne(@PathVariable(required=false) Double revenuJourPersonneMin,@PathVariable(required=false) Double revenuJourPersonneMax){
		
		if (revenuJourPersonneMax==null && revenuJourPersonneMin==null) {
			return daoR.findAll();
		}
		else if (revenuJourPersonneMin==null)
		{
			return daoR.findByRevenuJourPersonneLessThan(revenuJourPersonneMax);
		}
		else if (revenuJourPersonneMax==null)
		{
			return daoR.findByRevenuJourPersonneGreaterThan(revenuJourPersonneMin);
		}
		else
		{
			return daoR.findByRevenuJourPersonneBetween(revenuJourPersonneMin,revenuJourPersonneMax);
		}
	}
}
