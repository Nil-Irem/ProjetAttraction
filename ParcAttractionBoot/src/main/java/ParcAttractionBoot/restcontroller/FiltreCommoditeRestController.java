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
import ParcAttractionBoot.model.Commodite;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.CommoditeRepository;

@RestController
@RequestMapping("/api/filtre/commodite")
@CrossOrigin(origins="*")
public class FiltreCommoditeRestController {

	@Autowired
	CommoditeRepository daoC;
	
	
	@PutMapping("/nom")
	@JsonView(JsonViews.Common.class)
	public List<Commodite> filtreCommoditeNom(@RequestParam String partNom){
		if (partNom == null) {
			throw new FilterException("Nom null - commdite impossible à obtenir");
		}
		return daoC.findByNomContaining(partNom);
	}

	
	@GetMapping("/prixacquisition/{prixAcquisitionMin}&{prixAcquisitionMax}")
	@JsonView(JsonViews.Common.class)
	public List<Commodite> filtreCommoditePrixAcquisition(@PathVariable(required=false) Double prixAcquisitionMin,@PathVariable(required=false) Double prixAcquisitionMax){
		
		if (prixAcquisitionMax==null && prixAcquisitionMin==null) {
			return daoC.findAll();
		}
		else if (prixAcquisitionMax==null) {
			return daoC.findByPrixAcquisitionGreaterThan(prixAcquisitionMin);
		}
		else if (prixAcquisitionMin==null) {
			return daoC.findByPrixAcquisitionLessThan(prixAcquisitionMax);
		}
		else {
			return daoC.findByPrixAcquisitionBetween(prixAcquisitionMin,prixAcquisitionMax);
		}
	}
	
	
	@GetMapping("/taille/{tailleMin}&{tailleMax}")
	@JsonView(JsonViews.Common.class)
	public List<Commodite> filtreCommoditeTaille(@PathVariable(required=false) Double tailleMin,@PathVariable(required=false) Double tailleMax){
		
		if (tailleMax==null && tailleMin==null) {
			return daoC.findAll();
		}
		else if (tailleMax==null) {
			return daoC.findByTailleGreaterThan(tailleMin);
		}
		else if (tailleMin==null) {
			return daoC.findByTailleLessThan(tailleMax);
		}
		else {
			return daoC.findByTailleBetween(tailleMin,tailleMax);
		}
	}
}
