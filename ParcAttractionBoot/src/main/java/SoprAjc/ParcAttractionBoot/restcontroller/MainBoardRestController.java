package SoprAjc.ParcAttractionBoot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SoprAjc.ParcAttractionBoot.exception.AchatException;
import SoprAjc.ParcAttractionBoot.model.Achat;
import SoprAjc.ParcAttractionBoot.model.JsonViews;
import SoprAjc.ParcAttractionBoot.repositories.AchatRepository;

@RestController
@RequestMapping("/api/modification")
@CrossOrigin(origins = "*")
public class MainBoardRestController {

	@Autowired
	AchatRepository daoA;

	
	@GetMapping("/{id_parc}")
	@JsonView(JsonViews.Common.class)
	public List<Achat> getAchats(@PathVariable Integer id) {
		
		List<Achat> achats = daoA.findByIdParc(id);

		if (!achats.isEmpty()) {
			return achats;
		} else {
			throw new AchatException();
		}
	}

}
