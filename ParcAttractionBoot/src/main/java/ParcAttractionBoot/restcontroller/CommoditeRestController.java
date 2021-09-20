package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.Commodite;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.repositories.CommoditeRepository;

@RestController
@RequestMapping("/api/commodite")
@CrossOrigin(origins="*")
public class CommoditeRestController {
	
	
	@Autowired
	private CommoditeRepository daoC;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Commodite> getAll(){
		return daoC.findAll();
	}

}
