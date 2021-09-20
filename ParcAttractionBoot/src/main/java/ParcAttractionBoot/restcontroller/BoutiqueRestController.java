package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.Boutique;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.BoutiqueRepository;

@RestController
@RequestMapping("/api/boutique")
@CrossOrigin(origins="*")
public class BoutiqueRestController {
	
	@Autowired
	private BoutiqueRepository daoB;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> getAll(){
		return daoB.findAll();
	}

}
