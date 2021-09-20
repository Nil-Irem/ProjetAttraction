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
import ParcAttractionBoot.model.Boutique;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.BoutiqueRepository;

@RestController
@RequestMapping("/api/filtre/boutique")
@CrossOrigin(origins="*")
public class FiltreBoutiqueRestController {

	@Autowired
	BoutiqueRepository daoB;
	
	@PutMapping("/nom")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueNom(@RequestParam String partNom){
		if (partNom == null) {
			throw new FilterException("Nom null - boutique impossible à obtenir");
		}
		return daoB.findByNomContaining(partNom);
	}

	
	
	@GetMapping("/taille/{tailleMin}&{tailleMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueTaille(@PathVariable(required=false) Double tailleMin,@PathVariable(required=false) Double tailleMax){
		
		if (tailleMax==null && tailleMin==null) {
			return daoB.findAll();
		}
		else if (tailleMin==null)
		{
			return daoB.findByTailleLessThan(tailleMax);
		}
		else if (tailleMax==null)
		{
			return daoB.findByTailleGreaterThan(tailleMin);
		}
		else {
			return daoB.findByTailleBetween(tailleMin,tailleMax);
		}
	}
	
	
	@GetMapping("/prixacquisition/{prixAcquisitionMin}&{prixAcquisitionMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiquePrixAcquisition(@PathVariable(required=false) Double prixAcquisitionMin,@PathVariable(required=false) Double prixAcquisitionMax){
		
		if (prixAcquisitionMax==null && prixAcquisitionMin==null) {
			return daoB.findAll();
		}
		else if (prixAcquisitionMin==null)
		{
			return daoB.findByPrixAcquisitionLessThan(prixAcquisitionMax);
		}
		else if (prixAcquisitionMax==null)
		{
			return daoB.findByPrixAcquisitionGreaterThan(prixAcquisitionMin);
		}
		else {
			return daoB.findByPrixAcquisitionBetween(prixAcquisitionMin,prixAcquisitionMax);
		}
	}

	
	
	@GetMapping("/prixfonctionnement/{prixFonctionnementMin}&{prixFonctionnementMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiquePrixFonctionnement(@PathVariable(required=false) Double prixFonctionnementMin,@PathVariable(required=false) Double prixFonctionnementMax){
		
		if (prixFonctionnementMax==null && prixFonctionnementMin==null) {
			return daoB.findAll();
		}
		else if (prixFonctionnementMin==null)
		{
			return daoB.findByPrixFonctionnementLessThan(prixFonctionnementMax);
		}
		else if (prixFonctionnementMax==null)
		{
			return daoB.findByPrixFonctionnementGreaterThan(prixFonctionnementMin);
		}
		else {
			return daoB.findByPrixFonctionnementBetween(prixFonctionnementMin,prixFonctionnementMax);
		}
	}
	
	
	@GetMapping("/nbamelioration/{nbAmelioraMin}&{nbAmelioraMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueNbAmelioration(@PathVariable(required=false) Integer nbAmelioraMin,@PathVariable(required=false) Integer nbAmelioraMax){
		
		if (nbAmelioraMax==null && nbAmelioraMin==null) {
			return daoB.findAll();
		}
		else if (nbAmelioraMin==null)
		{
			return daoB.findByNbAmeliorationLessThan(nbAmelioraMax);
		}
		else if (nbAmelioraMax==null)
		{
			return daoB.findByNbAmeliorationGreaterThan(nbAmelioraMin);
		}
		else {
			return daoB.findByPrixFonctionnementBetween(nbAmelioraMin,nbAmelioraMax);
		}
	}
	
	
	@GetMapping("/tauxincident/{tauxIncidentMin}&{tauxIncidentMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueTauxIncident(@PathVariable(required=false) Double tauxIncidentMin,@PathVariable(required=false) Double tauxIncidentMax){
		
		if (tauxIncidentMax==null && tauxIncidentMin==null) {
			return daoB.findAll();
		}
		else if (tauxIncidentMin==null)
		{
			return daoB.findByTauxIncidentLessThan(tauxIncidentMax);
		}
		else if (tauxIncidentMax==null)
		{
			return daoB.findByTauxIncidentGreaterThan(tauxIncidentMin);
		}
		else {
			return daoB.findByTauxIncidentBetween(tauxIncidentMin,tauxIncidentMax);
		}
	}
	
	
	@GetMapping("/affluence/{affluenceMin}&{affluenceMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueAffluence(@PathVariable(required=false) Integer affluenceMin,@PathVariable(required=false) Integer affluenceMax){
		
		if (affluenceMax==null && affluenceMin==null) {
			return daoB.findAll();
		}
		else if (affluenceMin==null)
		{
			return daoB.findByAffluenceLessThan(affluenceMax);
		}
		else if (affluenceMax==null)
		{
			return daoB.findByAffluenceGreaterThan(affluenceMin);
		}
		else {
			return daoB.findByAffluenceBetween(affluenceMin,affluenceMax);
		}
	}
	
	
	@GetMapping("/revenujourpersonne/{revenuJourPersonneMin}&{revenuJourPersonneMax}")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> filtreBoutiqueRevenuJourPersonne(@PathVariable(required=false) Double revenuJourPersonneMin,@PathVariable(required=false) Double revenuJourPersonneMax){
		
		if (revenuJourPersonneMax==null && revenuJourPersonneMin==null) {
			return daoB.findAll();
		}
		else if (revenuJourPersonneMin==null)
		{
			return daoB.findByRevenuJourPersonneLessThan(revenuJourPersonneMax);
		}
		else if (revenuJourPersonneMax==null)
		{
			return daoB.findByRevenuJourPersonneGreaterThan(revenuJourPersonneMin);
		}
		else
		{
			return daoB.findByRevenuJourPersonneBetween(revenuJourPersonneMin,revenuJourPersonneMax);
		}
	}
}
