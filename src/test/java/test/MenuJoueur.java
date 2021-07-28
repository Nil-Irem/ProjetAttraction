package test;

import java.util.Random;
import java.util.Scanner;

import dao.DAOAttraction;
import dao.DAOBoutique;
import dao.DAOCommodite;
import dao.DAOCompte;
import dao.DAOEmploye;
import dao.DAOParc;
import dao.DAORestaurant;
import metier.Attraction;
import metier.Boutique;
import metier.Commodite;
import metier.Compte;
import metier.Employe;
import metier.Joueur;
import metier.Parc;
import metier.Restaurant;




public class MenuJoueur {

	static Joueur joueur = null;
	static Parc parc = null;
	static DAOAttraction DaoA = new DAOAttraction();
	static DAOBoutique DaoB = new DAOBoutique();
	static DAOCommodite DaoC = new DAOCommodite();
	static DAOCompte DaoCpt = new DAOCompte();
	static DAOEmploye DaoE = new DAOEmploye();
	static DAORestaurant DaoR = new DAORestaurant();
	static DAOParc DaoP = new DAOParc();

	static double prixAmeliorationAttraction = 5.00;
	static double prixAmeliorationBoutique = 5.00;
	static double prixAmeliorationRestaurant = 5.00;
	static double prixEntree = 45.00;
	private static double prixTerrain=100;


	public static int generateRandomInt(int max){
		Random random = new Random();
		return random.nextInt(max);
	}


	public static int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();

	}
	public static double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}
	public static String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}



	public static void menuJoueur(Compte connected) {
		joueur = (Joueur) connected;		
		System.out.println("\nC'est parti pour jouer !");
		System.out.println("1- Nouvelle Partie");
		System.out.println("2- Charger Partie");
		System.out.println("3- Se déconnecter");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : GestionJeu.creerPartie(joueur) ;break;
		case 2 : GestionJeu.chargerPartie(joueur);break;
		case 3 : Menu.menuPrincipal();break;
		}
		menuJoueur(connected);
	}



	public static void menuPartie(Parc parcjoueur) {
		parc = parcjoueur;
		System.out.println("\nQue souhaitez-vous faire?");
		System.out.println("1- Sauvegarder la Partie");
		System.out.println("2- Supprimer la Partie");
		System.out.println("3- Jouer");
		System.out.println("4- Retour au menu précédent");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : GestionJeu.saveGame(parc);break;
		case 2 : GestionJeu.deleteGame(parcjoueur.getId());menuJoueur(joueur);break;
		case 3 : menuJouer();break;
		case 4 : menuJoueur(joueur);break;
		}
		menuPartie(parc);
	}



	public static void menuJouer() {
		System.out.println("\nC'est le moment de jouer !");
		System.out.println("1- Modifier le parc");
		System.out.println("2- Finir la journée");
		System.out.println("3- Retour au menu précédent");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : menuModification();break;
		case 2 : finJournee();break;
		case 3 : menuPartie(parc);break;

		}
		menuJouer();
	}





	public static void finJournee() {
		double prixFonctionnement=0;
		double attractivite=0;
		double argentGagne=0;
		double nbVisiteur=0;
		double salaire=0;
		int capaciteMax=0;
		int tempsJournee= generateRandomInt(5);
		int nb_i = 0;//nb d'incidents
		double impact_e = 0;

		for (Employe e : parc.getEmployes()) {
			salaire += e.getSalaire();
			if (e.getSalaire()>50) {
				impact_e = impact_e + e.getSalaire();
			}
		}
		
		
		for (Attraction a : parc.getAttractions()) {
			int alea = generateRandomInt(11);
			double incident = 1;
			
			if (alea <a.getTauxIncident()/10)
			{
				// il y a eu un incident
				nb_i = nb_i +1;
				//on récupère les employés au salaire > 50 et selon leur salaire, l'incident sera + ou - impactant
				
				incident = 1-1/impact_e;//+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else
			{
				incident = 1; //pas d'incident
			}
			
			capaciteMax += a.getAffluence()*incident;
			prixFonctionnement += a.getPrixFonctionnement();
		}
		

		
		

		

		for (Boutique b : parc.getBoutiques()) {
			
			int alea = generateRandomInt(11);
			double incidentb = 1;
			
			if (alea <b.getTauxIncident()/10)
			{
				// il y a eu un incident
				nb_i = nb_i +1;
				//on récupère les employés au salaire > 50 et selon leur salaire, l'incident sera + ou - impactant
				
				incidentb = 1-1/impact_e;//+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else
			{
				incidentb = 1; //pas d'incident
			}
			
			capaciteMax += b.getAffluence()*incidentb;
			prixFonctionnement += b.getPrixFonctionnement();
		}
		
		//

		for (Restaurant r : parc.getRestaurants()) {
			
			int alea = generateRandomInt(11);
			double incidentr = 1;
			
			if (alea <r.getTauxIncident()/10)
			{
				// il y a eu un incident
				nb_i = nb_i +1;
				//on récupère les employés au salaire > 50 et selon leur salaire, l'incident sera + ou - impactant
				
				incidentr = 1-1/impact_e;//+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else
			{
				incidentr = 1; //pas d'incident
			}
			
			
			capaciteMax += r.getAffluence()*incidentr;
			prixFonctionnement += r.getPrixFonctionnement();
		}
		
		System.out.println("il y a eu " + nb_i + " incidents dans votre parc aujourd'hui");


		attractivite = (parc.getCommodites().size()+parc.getEmployes().size()+parc.getAttractions().size()+parc.getBoutiques().size()+parc.getRestaurants().size())/100;
		if (attractivite > 1) {attractivite = 1;}
		else if (attractivite == 0) {attractivite = 0.1;}


		switch (tempsJournee)
		{
		case 0 : System.out.println("\nAujourd'hui il a beaucoup plu");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		case 1 : System.out.println("\nAujourd'hui il a un peu plu");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		case 2 : System.out.println("\nAujourd'hui il a fait nuageux");
		nbVisiteur = capaciteMax*attractivite*0.9;
		break;
		case 3 : System.out.println("\nAujourd'hui il a fait beau");
		nbVisiteur = capaciteMax*attractivite;
		break;
		case 4 : System.out.println("\nAujourd'hui il a fait très chaud");
		nbVisiteur = capaciteMax*attractivite*0.7;
		break;
		}

		argentGagne = nbVisiteur*prixEntree;
		parc.setArgent(parc.getArgent()+argentGagne-salaire-prixFonctionnement);

		System.out.println("Vous avez reçu "+Math.round(nbVisiteur)+" visiteurs");
		System.out.println("Vous avez gagner "+argentGagne+"€ et dépensé "+(salaire+prixFonctionnement)+"€");
		System.out.println("Vous avez maintenant "+parc.getArgent()+"€");
	}

	
	public static void menuModification() {
		System.out.println("\n Quelles modifications voulez vous apporter au Parc ?");
		System.out.println("1- Ameliorer les structures deja en place");
		System.out.println("2- Acheter de nouvelles structures");
		System.out.println("3- Acheter du terrain");
		System.out.println("4- Voir nos possesions");
		System.out.println("5- Fin des modifications");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : menuAmelioration();break;
		case 2 : menuAchat();break;
		case 3 : menuAchatTerrain();break;
		case 4 : menuPossesion();break;
		case 5 : menuJouer();break;
		}
		menuModification();
	}



	private static void menuAchatTerrain() {
		System.out.println("Le prix du m² est de "+prixTerrain+"€");
		System.out.println("Vous avez actuellement "+parc.getArgent()+"€");
		int choix = saisieInt("Combien de m² voulez vous acheter ? (Donnez un nombre entier)");
		
		if (choix*prixTerrain > parc.getArgent()) {
			System.out.println("Vous n'avez pas assez d'argent pour acheter autant de terrain");
			menuModification();
		}
		else
		{
			parc.setTaille(parc.getTaille()+choix);
			parc.setArgent(parc.getArgent()-choix*prixTerrain);
			System.out.println("\nFélicitation vous avez maintenant "+parc.getArgent()+"€ et "+parc.getTaille()+" m² de terrain");
		}
		
	}


	private static void menuAchat() {
		System.out.println("\nQue voulez vous acheter ?");
		System.out.println("1- Acheter une Attraction");
		System.out.println("2- Acheter une Boutique");
		System.out.println("3- Acheter un Restaurant");
		System.out.println("4- Acheter une Commodité");
		System.out.println("5- Embaucher un Employe");
		System.out.println("6- Fin des achats");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : achatAttraction();break;
		case 2 : achatBoutique();break;
		case 3 : achatRestaurant();break;
		case 4 : achatCommodite();break;
		case 5 : achatEmploye();break;
		case 6 : menuModification();break;
		}
		menuAchat();
	}



	private static void achatRestaurant() {
		System.out.println("Voici tous les restaurants disponibles :");
		for (Restaurant r : DaoR.findAll()){System.out.println(r);}

		int choix = saisieInt("Choississez votre nouveau restaurant (donner son numero) :");
		Restaurant newResto = DaoR.findById(choix);

		if (newResto.getPrixAcquisition() <= parc.getArgent() && newResto.getTaille() <= parc.getTaille())
		{
			parc.setArgent(parc.getArgent()-newResto.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newResto.getTaille());
			parc.newRestaurant(newResto);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter ce restaurant !");
		}	
	}


	private static void achatCommodite() {
		System.out.println("Voici toutes les commodites disponibles :");
		for (Commodite c : DaoC.findAll()){System.out.println(c);}

		int choix = saisieInt("Choississez votre nouvelle commodite (donner son numero) :");
		Commodite newCom = DaoC.findById(choix);

		if (newCom.getPrixAcquisition() <= parc.getArgent() && newCom.getTaille() <= parc.getTaille())
		{
			parc.setArgent(parc.getArgent()-newCom.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newCom.getTaille());
			parc.newCommodite(newCom);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cette commodite !");
		}
	}



	private static void achatBoutique() {
		System.out.println("Voici tous les magasins disponibles :");
		for (Boutique b : DaoB.findAll())
		{
			System.out.println(b);
		}

		int choix = saisieInt("Choississez votre nouveau magasin (donner son numero) :");
		Boutique newBou = DaoB.findById(choix);

		if (newBou.getPrixAcquisition() <= parc.getArgent() && newBou.getTaille() <= parc.getTaille())
		{
			parc.setArgent(parc.getArgent()-newBou.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newBou.getTaille());
			parc.newBoutique(newBou);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cet magasin !");
		}
	}




	private static void achatEmploye() {
		System.out.println("Voici tous les employes disponibles :");
		for (Employe e : DaoE.findAll())
		{
			System.out.println(e);
		}

		int choix = saisieInt("Choississez votre nouvel employe (donner son numero) :");
		Employe newEmp = DaoE.findById(choix);

		if (newEmp.getSalaire() <= parc.getArgent())
		{
			parc.newEmploye(newEmp);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent pour embaucher cet employe !");
		}		
	}


	private static void achatAttraction() {

		System.out.println("Voici toutes les attractions disponibles :");

		for (Attraction a : DaoA.findAll())
		{
			System.out.println(a);
		}
		int choix = saisieInt("Choississez votre nouvelle attraction (donner son numero) :");
		Attraction newattrac = DaoA.findById(choix);

		if (newattrac.getPrixAcquisition() <= parc.getArgent() && newattrac.getTaille() <= parc.getTaille())
		{
			parc.setArgent(parc.getArgent()-newattrac.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newattrac.getTaille());
			parc.newAttraction(newattrac);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cet attraction !");
		}	
	}




	private static void menuAmelioration() {
		
		if (parc.getAttractions().isEmpty() && parc.getBoutiques().isEmpty() && parc.getRestaurants().isEmpty())
		{
			System.out.println("Tu n'as pas de batiments ! \n Vas en construire ! ");
			
			menuModification();
		}
		else {
			
		
			System.out.println("\nAmeliorez vos bâtiments !");
			System.out.println("1- Améliorez une boutique");
			System.out.println("2- Améliorez un restaurant");
			System.out.println("3- Améliorez une attraction");
			System.out.println("4- Retour menu Modifications");
			int choix = saisieInt("Choisir un menu");
	
			switch(choix) 
			{
			case 1 : ameliorerBoutique();break;
			case 2 : ameliorerRestaurant();break;
			case 3 : ameliorerAttraction();break;
			case 4 : menuModification();break;
			}
		}
		menuAmelioration();
	}



	private static void ameliorerAttraction() 
	{
		if (parc.getAttractions().isEmpty())
		{
			System.out.println("Vous n'avez pas d'attractions à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowAttraction();
			int choix = saisieInt("Choississez l'attraction à modifier (donner son numero) :");
			int i = 0;

			for (Attraction a : parc.getAttractions())
			{
				if (a.getId() == choix) {break;}
				else {i++;}
			}

			if (prixAmeliorationAttraction > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer cette attraction !");
			}
			else if (parc.getAttractions().get(i).getNiveauAmelioration()>=parc.getAttractions().get(i).getNbAmelioration() )
			{
				System.out.println("Vous avez atteint le maximum d'améliorations de cette attraction !");
			}
			else
			{
				parc.setArgent(parc.getArgent()-prixAmeliorationAttraction);
				parc.getAttractions().get(i).setNiveauAmelioration(parc.getAttractions().get(i).getNiveauAmelioration()+1);
			}
		}
	}


	private static void ameliorerRestaurant() {
		if (parc.getRestaurants().isEmpty())
		{
			System.out.println("Vous n'avez pas de restaurants à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowRestaurant();
			int choix = saisieInt("Choississez le restaurant à modifier (donner son numero) :");
			int i = 0;

			for (Restaurant r : parc.getRestaurants())
			{
				if (r.getId() == choix) {break;}
				else {i++;}
			}

			if (prixAmeliorationRestaurant > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer ce restaurant !");
			}
			else if (parc.getRestaurants().get(i).getNiveauAmelioration()>=parc.getRestaurants().get(i).getNbAmelioration() )
			{
				System.out.println("Vous avez atteint le maximum d'améliorations de ce restaurant !");
			}

			else
			{
				parc.setArgent(parc.getArgent()-prixAmeliorationRestaurant);
				parc.getRestaurants().get(i).setNiveauAmelioration(parc.getRestaurants().get(i).getNiveauAmelioration()+1);
			}
		}

	}

	private static void ameliorerBoutique() {
		if (parc.getBoutiques().isEmpty())
		{
			System.out.println("Vous n'avez pas de magasin à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowBoutique();
			int choix = saisieInt("Choississez la boutique à modifier (donner son numero) :");
			int i = 0;

			for (Boutique bou : parc.getBoutiques())
			{
				if (bou.getId() == choix) {break;}
				else {i++;}
			}

			if (prixAmeliorationBoutique > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer cette boutique !");
			}
			else if (parc.getBoutiques().get(i).getNiveauAmelioration()>=parc.getBoutiques().get(i).getNbAmelioration() )
			{
				System.out.println("Vous avez atteint le maximum d'améliorations de cette boutique !");
			}
			else
			{
				parc.setArgent(parc.getArgent()-prixAmeliorationBoutique);
				parc.getBoutiques().get(i).setNiveauAmelioration(parc.getBoutiques().get(i).getNiveauAmelioration()+1);
			}
		}
	}


	private static void menuPossesion() {

		System.out.println("\nMenu des attractions !");
		System.out.println("1- Liste de mes Attracttions");
		System.out.println("2-  Liste de mes Restaurants");
		System.out.println("3-  Liste de mes Comodités");
		System.out.println("4-  Liste de mes Boutiques");
		System.out.println("5- Liste des employés");
		System.out.println("6- Retour Menu Modification");

		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ShowAttraction();break;
		case 2 : ShowRestaurant();break;
		case 3 : ShowCommodites();break;
		case 4 : ShowBoutique();break;
		case 5 : ShowEmploye();break;
		case 6 : menuModification();break;
		}
		menuPossesion();

	}


	private static void ShowEmploye() {

		if (parc.getEmployes().isEmpty())
		{
			System.out.println("Vous ne possèdez pas d'employé");
		}
		else 
		{
			System.out.println("Voici touts les employés présents dans votre parc :");

			for (Employe emp : parc.getEmployes())
			{
				System.out.println(emp);
			}
		}
	}




	private static void ShowAttraction() {


		if (parc.getAttractions().isEmpty())
		{
			System.out.println("Vous ne possèdez pas d'attraction");
		}
		else
		{
			System.out.println("Voici toutes les attractions présentes dans votre parc :");

			for (Attraction a : parc.getAttractions())
			{
				System.out.println(a);
			}
		}
	}

	
	private static void ShowRestaurant() {

		if (parc.getRestaurants().isEmpty())
		{
			System.out.println("Vous ne possèdez pas de restaurant");
		}
		else 
		{
			System.out.println("Voici touts les restaurants présents dans votre parc :");

			for (Restaurant r : parc.getRestaurants())
			{
				System.out.println(r);
			}
		}
	}

	
	private static void ShowCommodites() {
		
		if (parc.getCommodites().isEmpty())
		{
			System.out.println("Vous ne possèdez pas de commodites");
		}
		else 
		{
			System.out.println("Voici toutes les commodites présentes dans votre parc :");
			for (Commodite c : parc.getCommodites())
			{
				System.out.println(c);
			}
		}
	}

	
	private static void ShowBoutique() {

		if (parc.getBoutiques().isEmpty())
		{
			System.out.println("Vous ne possèdez pas de boutique");
		}
		else {
			System.out.println("Voici toutes les boutiques présentes dans votre parc :");

			for (Boutique b : parc.getBoutiques())
			{
				System.out.println(b);
			}
		}
	}


}
