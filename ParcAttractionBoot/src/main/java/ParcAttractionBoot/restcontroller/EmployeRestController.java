package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.Employe;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.EmployeRepository;

@RestController
@RequestMapping("/api/employe")
@CrossOrigin(origins="*")
public class EmployeRestController {
	
	@Autowired
	private EmployeRepository daoE;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Employe> getAll(){
		return daoE.findAll();
	}

	//list attraction
	//attraction by id
	//ajouter attraction avec att ou id
	//supprimer avec att ou id --> supprimer achat
	//modifier
	
}
