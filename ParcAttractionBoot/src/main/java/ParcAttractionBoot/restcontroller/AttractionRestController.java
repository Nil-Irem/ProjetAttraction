package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.Attraction;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.AttractionRepository;

@RestController
@RequestMapping("/api/attraction")
@CrossOrigin(origins="*")
public class AttractionRestController {
	
	@Autowired
	private AttractionRepository daoA;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Attraction> getAll(){
		return daoA.findAll();
	}

}
