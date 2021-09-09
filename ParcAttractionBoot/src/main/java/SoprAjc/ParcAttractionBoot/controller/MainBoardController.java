package SoprAjc.ParcAttractionBoot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.repositories.AchatRepository;
import SoprAjc.ParcAttractionBoot.repositories.AttractionRepository;
import SoprAjc.ParcAttractionBoot.repositories.BoutiqueRepository;
import SoprAjc.ParcAttractionBoot.repositories.CommoditeRepository;
import SoprAjc.ParcAttractionBoot.repositories.EmployeRepository;
import SoprAjc.ParcAttractionBoot.repositories.RestaurantRepository;



@Controller
@RequestMapping("/mainBoard")
public class MainBoardController {


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
			
		ModelAndView modelAndView = new ModelAndView("jeu/mainBoard");
		modelAndView.addObject("attractions", daoA.findByTypeElementAndParc("attraction", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("boutiques", daoA.findByTypeElementAndParc("boutiques", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("restaurants", daoA.findByTypeElementAndParc("restaurant", (Parc) session.getAttribute("parc") ));
		modelAndView.addObject("commodites", daoA.findByTypeElementAndParc("commodites",(Parc) session.getAttribute("parc") ));
		modelAndView.addObject("employes", daoA.findByTypeElementAndParc("employe",(Parc) session.getAttribute("parc") ));
		
		return modelAndView; 
	}




	
}
