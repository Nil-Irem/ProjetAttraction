package ParcAttractionBoot.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.Achat;
import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Parc;

@RestController
@RequestMapping("/api/possessions")
@CrossOrigin(origins = "*")
public class PossessionRestController {

	@Autowired
	private AchatRestController achat;


	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAllByParc(@Valid @RequestBody Parc parc){
		return achat.getAllAchatByParc(parc);
	}

	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Achat get(@PathVariable Integer id){
		return achat.get(id);
	}

}
