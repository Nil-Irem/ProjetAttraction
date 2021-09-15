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
import ParcAttractionBoot.model.Employe;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.EmployeRepository;

@RestController
@RequestMapping("/api/filtre/employe")
@CrossOrigin(origins="*")
public class FiltreEmployeRestController {

	@Autowired
	EmployeRepository daoE;
	
	
	@PutMapping("/metier")
	@JsonView(JsonViews.Common.class)
	public List<Employe> filtreEmployeMetier(@RequestParam String partMetier){
		if (partMetier == null) {
			throw new FilterException("Nom null - employe impossible à obtenir");
		}
		return daoE.findByMetierContaining(partMetier);
	}
	
	
	@GetMapping("/salaire/{salaireMin}&{salaireMax}")
	@JsonView(JsonViews.Common.class)
	public List<Employe> filtreAttractionSalaire(@PathVariable(required=false) Double salaireMin,@PathVariable(required=false) Double salaireMax){
		
		if (salaireMax==null && salaireMin==null) {
			return daoE.findAll();
		}
		else if (salaireMin==null) {
			return daoE.findBySalaireLessThan(salaireMax);
		}
		else if (salaireMax==null) {
			return daoE.findBySalaireGreaterThan(salaireMin);
		}
		else {
			return daoE.findBySalaireBetween(salaireMin,salaireMax);
		}
	}
}
