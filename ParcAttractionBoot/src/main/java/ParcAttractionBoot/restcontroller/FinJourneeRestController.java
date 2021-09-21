package ParcAttractionBoot.restcontroller;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.text.NumberFormat;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ParcAttractionBoot.model.*;
import ParcAttractionBoot.repositories.AchatRepository;
import ParcAttractionBoot.repositories.ParcRepository;

@RestController
@RequestMapping("/api/finJournee")
@CrossOrigin(origins = "*")
public class FinJourneeRestController {

    @Autowired
    ParcRepository pRepo;


    @Autowired
    AchatRepository achatRepo;

    NumberFormat Myformat = NumberFormat.getInstance();

	double prixEntree = 100.00;


    @GetMapping("/byParc")
	@JsonView(JsonViews.Common.class)
    public List<String> finJournee(@Valid @RequestBody Parc parc){
		double prixFonctionnement=0;
		double attractivite=0;
		double argentGagne=0;
		double nbVisiteur=0;
		double salaire=0;
		int capaciteMax=0;
		int tempsJournee= generateRandomInt(5);
		int nb_i = 0; //nb d'incidents
		double impact_e = 2;
		double impactEA = 2;
		List<String> res = new ArrayList();


		for (Achat a : achatRepo.findByTypeElementAndParc("employe", parc)) {
			Employe e = (Employe) a.getElement();
			salaire += e.getSalaire();

			if (e.getSalaire()>50) 
			{
				impact_e = impact_e + e.getSalaire();
			}
			else
			{
				impactEA += e.getSalaire();
			}
		}


		for (Achat a : achatRepo.findByTypeElementAndParc("attraction", parc)) {
			Attraction at = (Attraction) a.getElement();
			int alea = generateRandomInt(11);
			double incident = 1;
			Optional<Integer> nvAm = achatRepo.Nvamelioration(parc,at);
			int nivAm=0 ;

			if (alea <at.getTauxIncident()/10) // il y a eu un incident
			{
				nb_i = nb_i +1;				
				incident = 1-(1/impact_e); //+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else //pas d'incident
			{
				incident = 1; 
			}


			if(nvAm.isEmpty()){
				//que fait-on?
				System.out.println("Niveau Amelioration is Empty");
			}
			else{
				nivAm = nvAm.get();
			}

		
			if (nivAm != 0)
			{
				attractivite += nivAm/at.getNbAmelioration();
			}

			capaciteMax += at.getAffluence()*incident;
			prixFonctionnement += at.getPrixFonctionnement();
		}


		for (Achat a : achatRepo.findByTypeElementAndParc("boutique", parc)) {
			Boutique b = (Boutique) a.getElement();
			int alea = generateRandomInt(11);
			double incidentb = 1;

			if (alea <b.getTauxIncident()/10) // il y a eu un incident
			{
				nb_i = nb_i +1;				
				incidentb = 1-(1/impact_e); //+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else //pas d'incident
			{
				incidentb = 1;
			}


			int nivAm=0;
			//if (Context.getInstance().getachatRepo().Nvamelioration(parc,b) != 0)
			if(nivAm !=0)
			{
				attractivite += nivAm/b.getNbAmelioration();
			}

			capaciteMax += b.getAffluence()*incidentb;
			prixFonctionnement += b.getPrixFonctionnement();
		}


		for (Achat a : achatRepo.findByTypeElementAndParc ("restaurant", parc)) {
			Restaurant r = (Restaurant) a.getElement();
			int alea = generateRandomInt(11);
			double incidentr = 1;

			if (alea <r.getTauxIncident()/10) // il y a eu un incident
			{
				nb_i = nb_i +1;				
				incidentr = 1-(1/impact_e); //+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else
			{
				incidentr = 1; //pas d'incident
			}



			int nivAm=0;
			if (nivAm != 0)
			{
				attractivite += nivAm/r.getNbAmelioration();
			}

			capaciteMax += r.getAffluence()*incidentr;
			prixFonctionnement += r.getPrixFonctionnement();
		}


		//System.out.println("il y a eu " + nb_i + " incidents dans votre parc aujourd'hui");


		attractivite += (achatRepo.findByParc(parc).size())/100; 
		attractivite -= 1/impactEA;

		if (attractivite > 1) {attractivite = 1;}
		else if (attractivite < 0) {attractivite = 0.1;}
		String meteo = "temps";


		switch (tempsJournee)
		{
		case 0 : meteo="beaucoup plu";
		//System.out.println("\nAujourd'hui il a beaucoup plu");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		case 1 : meteo="un peu plu";
		//System.out.println("\nAujourd'hui il a un peu plu");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		case 2 : meteo="fait nuageux";
		//System.out.println("\nAujourd'hui il a fait nuageux");
		nbVisiteur = capaciteMax*attractivite*0.9;
		break;
		case 3 : meteo = "fait beau";
		//System.out.println("\nAujourd'hui il a fait beau");
		nbVisiteur = capaciteMax*attractivite;
		break;
		case 4 : meteo = "fait très chaud";
		//System.out.println("\nAujourd'hui il a fait très chaud");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		}

		argentGagne = nbVisiteur*prixEntree;
		parc.setArgent(parc.getArgent()+argentGagne-salaire-prixFonctionnement);
		parc.setNbjour(parc.getNbjour()+1);
		//Context.getInstance().setParc(parc);

		//System.out.println("Vous avez reçu "+Math.round(nbVisiteur)+" visiteurs");
		//System.out.println("Vous avez gagné "+ Myformat.format(argentGagne)+"€ et dépensé "+Myformat.format(salaire+prixFonctionnement)+"€");
		//System.out.println("Vous avez maintenant "+Myformat.format(parc.getArgent())+"€");
        pRepo.save(parc);
		//gestionJeu.saveGame();
		//parc = Context.getInstance().getParc();
		res.add("" + nb_i);//nb incidents
		res.add(meteo);
		res.add(""+ Math.round(nbVisiteur));
		res.add(Myformat.format(argentGagne));
		res.add(Myformat.format(salaire+prixFonctionnement));
		res.add(Myformat.format(parc.getArgent()));

		return res;


    }


    //PAS SUR 
	private int generateRandomInt(int i) {
		Random r=new Random();
		int n =r.nextInt();
		return n;
	}





}
