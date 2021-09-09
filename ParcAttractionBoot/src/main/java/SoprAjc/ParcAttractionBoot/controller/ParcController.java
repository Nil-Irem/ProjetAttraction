package SoprAjc.ParcAttractionBoot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import SoprAjc.ParcAttractionBoot.model.Achat;
import SoprAjc.ParcAttractionBoot.model.Difficulte;
import SoprAjc.ParcAttractionBoot.model.Joueur;
import SoprAjc.ParcAttractionBoot.model.Parc;
import SoprAjc.ParcAttractionBoot.repositories.AchatRepository;
import SoprAjc.ParcAttractionBoot.repositories.ParcRepository;

@Controller
@RequestMapping("/parcs")
public class ParcController {


	@Autowired
	ParcRepository daoP;
	@Autowired
	AchatRepository daoA;
	

	@GetMapping("")
	public ModelAndView listParcs(HttpSession session) {
		ModelAndView mAv = new ModelAndView("jeu/menuJoueur");
		mAv.addObject("parc",new Parc());
		mAv.addObject("parcs",daoP.findByJoueur((Joueur) session.getAttribute("joueur")));
		mAv.addObject("typesDifficultes",Difficulte.values());
		return mAv;
	}
	

	@PostMapping("/addparc")
	public ModelAndView addParc(@Valid @ModelAttribute Parc parc,BindingResult br,HttpSession session) {
		if (br.hasErrors())
		{
			System.out.println(br.getAllErrors());
		}
		else if (session.getAttribute("joueur")==null)
		{
			System.out.println("Pas de joueur associé");
		}
		else
		{
			Difficulte diff = parc.getTypeDifficulte();
			parc.setArgent(diff.getArgent());
			parc.setTaille(diff.getTailleParc());
			daoP.save(parc);
		}
		return new ModelAndView("redirect:/parcs");
	}
	


	@GetMapping("play")
	public ModelAndView play (@RequestParam Integer id,HttpSession session) {
		session.setAttribute("parc", daoP.findById(id).get());
		return new ModelAndView("jeu/mainBoard");
	}
	


	@GetMapping("delete")
	public ModelAndView deleteParc (@RequestParam Integer id) {
		deleteAchat(id);
		daoP.deleteById(id);
		return new ModelAndView("redirect:/parcs");
	}
	
	
	
	private void deleteAchat(int id) {
		List<Achat> achats = daoA.findByIdParc(id);
		for (Achat a : achats) {
			daoA.delete(a);
		}
	}
	
}
