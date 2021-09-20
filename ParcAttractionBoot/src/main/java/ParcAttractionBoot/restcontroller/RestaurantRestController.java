package ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ParcAttractionBoot.model.JsonViews;
import ParcAttractionBoot.model.Restaurant;
import ParcAttractionBoot.repositories.RestaurantRepository;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin(origins="*")
public class RestaurantRestController {
	
	@Autowired
	private RestaurantRepository daoR;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Restaurant> getAll(){
		return daoR.findAll();
	}

}
