package SoprAjc.ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SoprAjc.ParcAttractionBoot.model.Compte;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.repositories.CompteRepository;


@RestController
@RequestMapping("/api/filtre")
@CrossOrigin(origins="*")
public class FilterRestController {

	@Autowired
	CompteRepository daoC;

	@PutMapping("/compte")
	@JsonView(JsonViews.Common.class)
	public List<Compte> get(@PathVariable String partLogin){
		return daoC.findByLoginContaining(partLogin);
	}
}
