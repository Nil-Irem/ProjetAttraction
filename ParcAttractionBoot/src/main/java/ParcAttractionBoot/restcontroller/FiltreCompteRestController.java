package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.exception.FilterException;
import ParcAttractionBoot.model.Compte;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.CompteRepository;


@RestController
@RequestMapping("/api/filtre/compte")
@CrossOrigin(origins="*")
public class FiltreCompteRestController {

	@Autowired
	CompteRepository daoCpt;

	
	@PutMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compte> filtreCompte(@RequestParam String partLogin){
		if (partLogin == null) {
			throw new FilterException("Login null - compte impossible à obtenir");
		}
		return daoCpt.findByLoginContaining(partLogin);
	}
	
}
