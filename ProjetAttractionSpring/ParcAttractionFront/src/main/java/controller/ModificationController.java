package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import attraction.model.*;
import attraction.repositories.*;


@Controller
@RequestMapping("/modification")
public class ModificationController {


	@Autowired
	AchatRepository daoA;
	
	@Autowired
	AttractionRepository daoAt;
	
	@Autowired
	BoutiqueRepository daoB;
	
	@Autowired
	CommoditeRepository daoC;
	
	@Autowired
	EmployeRepository daoE;
	
	@Autowired
	RestaurantRepository daoR;
	
	double prixEntree = 100.00;
	double prixTerrain=100;
	
	
	
	@GetMapping("")
	public ModelAndView modif(HttpSession session) {
			
		ModelAndView modelAndView = new ModelAndView("modification/Modification");
		modelAndView.addObject("attractions", daoA.findByTypeElementAndParc("attraction", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("boutiques", daoA.findByTypeElementAndParc("boutiques", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("restaurants", daoA.findByTypeElementAndParc("restaurant", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("commodites", daoA.findByTypeElementAndParc("commodites",(Parc) session.getAttribute("parc") ));
		modelAndView.addObject("employes", daoA.findByTypeElementAndParc("employe",(Parc) session.getAttribute("parc") ));
		
		return modelAndView; 
	}




	
}
