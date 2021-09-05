
/*package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import attraction.model.Boutique;
import attraction.model.Commodite;
import attraction.model.Employe;
import attraction.model.Restaurant;
import attraction.repositories.AchatRepository;
import attraction.repositories.AttractionRepository;
import attraction.repositories.BoutiqueRepository;
import attraction.repositories.CommoditeRepository;
import attraction.repositories.CompteRepository;
import attraction.repositories.EmployeRepository;
import attraction.repositories.ParcRepository;
import attraction.repositories.RestaurantRepository;


public class Injection {


	@Autowired
	AchatRepository achatRepo;

	@Autowired
	AttractionRepository aRepo;

	@Autowired
	BoutiqueRepository bRepo;

	@Autowired
	CommoditeRepository cRepo;

	@Autowired
	CompteRepository compteRepo;

	@Autowired
	EmployeRepository eRepo;

	@Autowired
	ParcRepository parcRepo;

	@Autowired
	RestaurantRepository rRepo;



	public void injectionSQL(){


		Restaurant r1= new Restaurant("Le Yololo", 450000, 1500, 15, 600, 300, 45, 250);
		Restaurant r2= new Restaurant("Tacotycoon", 300000, 1250, 12, 400, 200, 45, 200);
		Restaurant r3= new Restaurant("YoloDouceur", 150000, 1000, 8, 300, 150, 30, 100);
		Restaurant r4= new Restaurant("Glacoon", 100000, 800, 4, 300, 90, 20, 70);

		rRepo.save(r1);
		rRepo.save(r2);
		rRepo.save(r3);
		rRepo.save(r4);



		Boutique b1=new Boutique("PAPoon",300000,1500,6,400,300,10,250);
		Boutique b2 = new Boutique("Boutique Dream", 300000, 1000, 6, 400, 200, 10, 250);

		bRepo.save(b1);
		bRepo.save(b2);


		Employe e1 = new Employe("Jardinier",45);
		Employe e2 = new Employe("Agent d'entretien",40);
		Employe e3 = new Employe("Securite",90);
		Employe e4 = new Employe("Personnel d'Attraction",80);
		Employe e5 = new Employe("Mascotte",35);

		eRepo.save(e1);
		eRepo.save(e2);
		eRepo.save(e3);
		eRepo.save(e4);
		eRepo.save(e5);





		Commodite c1=new Commodite("Toilettes F",100000,75);
		Commodite c2=new Commodite("Toilettes H",100000,75);
		Commodite c3=new Commodite("Zone de Detente",75000,100);

		cRepo.save(c1);
		cRepo.save(c2);
		cRepo.save(c3);

	}

}
*/