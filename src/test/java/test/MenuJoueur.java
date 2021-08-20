package test;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import dao.IDAO.*;
import metier.*;
import util.Context;




public class MenuJoueur {

	static Joueur joueur =  Context.getInstance().getJoueur();
	static Parc parc = Context.getInstance().getParc();
	static IDAOAttraction DaoA = Context.getInstance().getDaoA();
	static IDAOBoutique DaoB = Context.getInstance().getDaoB();
	static IDAOCommodite DaoC = Context.getInstance().getDaoC();
	static IDAOCompte DaoCpt = Context.getInstance().getDaoCpt();
	static IDAOEmploye DaoE = Context.getInstance().getDaoE();
	static IDAORestaurant DaoR = Context.getInstance().getDaoR();
	static IDAOParc DaoP = Context.getInstance().getDaoP();
	static IDAOAchat DaoAc = Context.getInstance().getDaoAc();



	static double prixAmeliorationAttraction = 5.00;
	static double prixAmeliorationBoutique = 5.00;
	static double prixAmeliorationRestaurant = 5.00;
	static double prixEntree = 100.00;
	private static double prixTerrain=100;
	static boolean testSaisie=true;
	static NumberFormat Myformat = NumberFormat.getInstance();



	public static void menuJoueur() {
		testSaisie = true;
		int choix=0;

		while (testSaisie)
		{
			try {
				System.out.println("\nC'est parti pour jouer !");
				System.out.println("Choisir un menu");
				System.out.println("1- Nouvelle Partie");
				System.out.println("2- Charger Partie");
				System.out.println("3- Se déconnecter");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 3");
			}
		}
		
		switch(choix) 
		{
		case 1 : GestionJeu.creerPartie() ;break;
		case 2 : GestionJeu.chargerPartie();break;
		case 3 : Menu.menuPrincipal();break;
		default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 3");break;
		}
		parc = Context.getInstance().getParc();
		menuJoueur();
	}



	public static void menuPartie() {
		testSaisie = true;
		int choix=0;
		parc = Context.getInstance().getParc();

		while (testSaisie)
		{
			try {
				System.out.println("\nQue souhaitez-vous faire?");
				System.out.println("1- Sauvegarder la Partie");
				System.out.println("2- Supprimer la Partie");
				System.out.println("3- Jouer");
				System.out.println("4- Retour au menu précédent");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 4");
			}
		}

		switch(choix) 
		{
		case 1 : GestionJeu.saveGame();break;
		case 2 : GestionJeu.deleteGame();menuJoueur();break;
		case 3 : menuJouer();break;
		case 4 : menuJoueur();break;
		default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 4");break;
		}
		parc = Context.getInstance().getParc();
		menuPartie();
	}



	public static void menuJouer() {
		testSaisie = true;
		int choix=0;

		while (testSaisie)
		{
			try {
				System.out.println("\nC'est le moment de jouer !");
				System.out.println("Choisir un menu");
				System.out.println("1- Modifier le parc");
				System.out.println("2- Finir la journée");
				System.out.println("3- Retour au menu précédent");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 3");
			}
		}

		switch(choix) 
		{
		case 1 : menuModification();break;
		case 2 : finJournee();break;
		case 3 : menuPartie();break;
		default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 3");break;
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
		int nb_i = 0; //nb d'incidents
		double impact_e = 2;
		double impactEA = 2;
		
		
		for (Achat a : DaoAc.findByType("employe", parc)) {
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


		for (Achat a : DaoAc.findByType("attraction", parc)) {
			Attraction at = (Attraction) a.getElement();
			int alea = generateRandomInt(11);
			double incident = 1;

			if (alea <at.getTauxIncident()/10) // il y a eu un incident
			{
				nb_i = nb_i +1;				
				incident = 1-(1/impact_e); //+ l'impact est grd, - 1/impact est grand, + incident est proche de 1
			}
			else //pas d'incident
			{
				incident = 1; 
			}
			
			if (DaoAc.Nvamelioration(parc,at) != 0)
			{
				attractivite += DaoAc.Nvamelioration(parc,at)/at.getNbAmelioration();
			}

			capaciteMax += at.getAffluence()*incident;
			prixFonctionnement += at.getPrixFonctionnement();
		}


		for (Achat a : DaoAc.findByType("boutique", parc)) {
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

		
			if (Context.getInstance().getDaoAc().Nvamelioration(parc,b) != 0)
			{
				attractivite += Context.getInstance().getDaoAc().Nvamelioration(parc,b)/b.getNbAmelioration();
			}

			capaciteMax += b.getAffluence()*incidentb;
			prixFonctionnement += b.getPrixFonctionnement();
		}


		for (Achat a : DaoAc.findByType ("restaurant", parc)) {
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
				
				
			
		
			if (Context.getInstance().getDaoAc().Nvamelioration(parc,r) != 0)
			{
				attractivite += Context.getInstance().getDaoAc().Nvamelioration(parc,r)/r.getNbAmelioration();
			}

			capaciteMax += r.getAffluence()*incidentr;
			prixFonctionnement += r.getPrixFonctionnement();
		}


		System.out.println("il y a eu " + nb_i + " incidents dans votre parc aujourd'hui");


		attractivite += (Context.getInstance().getDaoAc().findByParc(parc).size())/100; 
		attractivite -= 1/impactEA;

		if (attractivite > 1) {attractivite = 1;}
		else if (attractivite < 0) {attractivite = 0.1;}


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
		parc.setNbjour(parc.getNbjour()+1);
		Context.getInstance().setParc(parc);

		System.out.println("Vous avez reçu "+Math.round(nbVisiteur)+" visiteurs");
		System.out.println("Vous avez gagné "+ Myformat.format(argentGagne)+"€ et dépensé "+Myformat.format(salaire+prixFonctionnement)+"€");
		System.out.println("Vous avez maintenant "+Myformat.format(parc.getArgent())+"€");
		GestionJeu.saveGame();
		parc = Context.getInstance().getParc();
	}


	public static void menuModification() {
		testSaisie = true;
		int choix=0;

		while (testSaisie)
		{
			try {
				System.out.println("\n Quelles modifications voulez vous apporter au Parc ?");
				System.out.println("1- Ameliorer les structures deja en place");
				System.out.println("2- Acheter de nouvelles structures ou embaucher des employes");
				System.out.println("3- Acheter du terrain");
				System.out.println("4- Voir nos possesions");
				System.out.println("5- Fin des modifications");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 5");
			}
		}

		switch(choix) 
		{
		case 1 : menuAmelioration();break;
		case 2 : menuAchat();break;
		case 3 : menuAchatTerrain();break;
		case 4 : menuPossession();break;
		case 5 : menuJouer();break;
		default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 5");break;
		}
		menuModification();
	}



	private static void menuAchatTerrain() {
		testSaisie = true;
		int choix=0;

		while (testSaisie)
		{
			try {
				System.out.println("Le prix du m² est de "+prixTerrain+"€");
				System.out.println("Vous avez actuellement "+parc.getArgent()+"€");
				choix = saisieInt("Combien de m² voulez vous acheter ? (Donnez un nombre entier)");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}

		if (choix*prixTerrain > parc.getArgent()) {
			System.out.println("Vous n'avez pas assez d'argent pour acheter autant de terrain");
			menuModification();
		}
		else
		{
			parc.setTaille(parc.getTaille()+choix);
			parc.setArgent(parc.getArgent()-choix*prixTerrain);
			Context.getInstance().setParc(parc);
			System.out.println("\nFélicitation vous avez maintenant "+parc.getArgent()+"€ et "+parc.getTaille()+" m² de terrain");
		}

	}


	private static void menuAchat() {
		testSaisie = true;
		int choix=0;

		while (testSaisie)
		{
			try {
				System.out.println("\nQue voulez vous acheter ?");
				System.out.println("1- Acheter une Attraction");
				System.out.println("2- Acheter une Boutique");
				System.out.println("3- Acheter un Restaurant");
				System.out.println("4- Acheter une Commodité");
				System.out.println("5- Embaucher un Employe");
				System.out.println("6- Fin des achats");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 6");
			}
		}

		switch(choix) 
		{
		case 1 : achatAttraction();break;
		case 2 : achatBoutique();break;
		case 3 : achatRestaurant();break;
		case 4 : achatCommodite();break;
		case 5 : achatEmploye();break;
		case 6 : menuModification();break;
		default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 6");break;
		}
		GestionJeu.saveGame();
		parc = Context.getInstance().getParc();
		menuAchat();	
	}



	private static void achatRestaurant() {
		List<Restaurant> allRestaurants = DaoR.findAll();
		List<Achat> allAchatRestaurants = DaoAc.findByType("restaurant",parc);
		Restaurant r1 = null;
	
		//allRestaurants.remove(allAchatRestaurants);
		for (int i=0; i<allAchatRestaurants.size();i++) 
		{
			r1 = (Restaurant) allAchatRestaurants.get(i).getElement();
			
			for (int j=0; j<allRestaurants.size();j++)
			{	
				if (r1.getId()==allRestaurants.get(j).getId())
				{			
					allRestaurants.remove(j);
				}
			}
		}
		
		if (allRestaurants.isEmpty())
		{
			System.out.println("Vous avez acheté tous les restaurants disponibles");
			menuAchat();			
		}
		else
		{
			System.out.println("Voici tous les restaurants disponibles :");
			for (Restaurant r : allRestaurants){System.out.println(r);}
		}


		testSaisie = true;
		int choix=0;
		Restaurant newResto=null;

		while (testSaisie)
		{
			try {
				choix = saisieInt("Choississez votre nouveau restaurant (donner son numero) :");
				newResto = DaoR.findById(choix);
				if (newResto == null) {System.out.println("\nAttention ce restaurant n'existe pas, réessayez");}
				else {testSaisie = false;}
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}

		if (newResto.getPrixAcquisition() <= parc.getArgent() && newResto.getTaille() <= parc.getTaille())
		{
			Achat restoBuy = new Achat(newResto,0,"restaurant",parc);
			DaoAc.insert(restoBuy);
			
			parc.setArgent(parc.getArgent()-newResto.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newResto.getTaille());
			Context.getInstance().setParc(parc);		
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter ce restaurant !");
		}	
	}






	private static void achatCommodite() {
		System.out.println("Voici toutes les commodites disponibles :");
		for (Commodite c : DaoC.findAll()){System.out.println(c);}

		testSaisie = true;
		int choix=0;
		Commodite newCom = null;

		while (testSaisie)
		{
			try {
				choix = saisieInt("Choississez votre nouvelle commodite (donner son numero) :");
				newCom = DaoC.findById(choix);
				if (newCom == null) {System.out.println("\nAttention cette commodite n'existe pas, réessayez");}
				else {testSaisie = false;}
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}

		if (newCom.getPrixAcquisition() <= parc.getArgent() && newCom.getTaille() <= parc.getTaille())
		{	
			boolean exist=false;
			Achat newAchat = null; 
			for (Achat a : DaoAc.findByType("commodite", parc))
			{
				if(a.getElement().getId() == newCom.getId()) 
				{
					exist=true;
					newAchat = a;
					break;
				}
			}
			
			if (exist)
			{
				newAchat.setNbSameElement(newAchat.getNbSameElement()+1);
				DaoAc.update(newAchat);
			}
			else
			{
				newAchat = new Achat(newCom,1,0,"commodite",parc);
				DaoAc.insert(newAchat);
			}
			
			parc.setTaille(parc.getTaille()-newCom.getTaille());
			Context.getInstance().setParc(parc);
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cette commodite !");
		}
	}





	private static void achatBoutique() {
		List<Boutique> allBoutiques = DaoB.findAll();
		List<Achat> allAchatBoutiques = DaoAc.findByType("boutique",parc);
		Boutique b1 = null;
	
		//allBoutiques.remove(allAchatBoutiques);
		for (int i=0; i<allAchatBoutiques.size();i++) 
		{
			b1 = (Boutique) allAchatBoutiques.get(i).getElement();
			
			for (int j=0; j<allBoutiques.size();j++)
			{	
				if (b1.getId()==allBoutiques.get(j).getId())
				{			
					allBoutiques.remove(j);
				}
			}
		}
		
		if (allBoutiques.isEmpty())
		{
			System.out.println("Vous avez acheté toutes les boutiques disponibles");
			menuAchat();			
		}
		else
		{
			System.out.println("Voici toutes les boutiques disponibles :");
			
			for (Boutique b : allBoutiques)
			{
				System.out.println(b);
			}
		}


		testSaisie = true;
		int choix=0;
		Boutique newBou = null;

		while (testSaisie)
		{
			try {
				choix = saisieInt("Choississez votre nouvelle boutique (donner son numero) :");
				newBou = DaoB.findById(choix);
				if (newBou == null) {System.out.println("\nAttention cette boutique n'existe pas, réessayez");}
				else {testSaisie = false;}
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}

		if (newBou.getPrixAcquisition() <= parc.getArgent() && newBou.getTaille() <= parc.getTaille())
		{
			Achat newAchat = new Achat(newBou,0,"boutique",parc);
			DaoAc.insert(newAchat);
			
			parc.setArgent(parc.getArgent()-newBou.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newBou.getTaille());
			Context.getInstance().setParc(parc);	
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cette boutique !");
		}
	}




	private static void achatEmploye() {
		System.out.println("Voici tous les employes disponibles :");
		for (Employe e : DaoE.findAll())
		{
			System.out.println(e);
		}

		testSaisie = true;
		int choix=0;
		Employe newEmp = null;

		while (testSaisie)
		{
			try {
				choix = saisieInt("Choississez votre nouvel employe (donner son numero) :");
				newEmp = DaoE.findById(choix);
				if (newEmp == null) {System.out.println("\nAttention cet employe n'existe pas, réessayez");}
				else {testSaisie = false;}
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}

		if (newEmp.getSalaire() <= parc.getArgent())
		{		
			boolean exist=false;
			Achat newAchat = null; 
			for (Achat a : DaoAc.findByType("employe", parc))
			{
				if(a.getElement().getId() == newEmp.getId()) 
				{
					exist=true;
					newAchat = a;
					break;
				}
			}
			
			if (exist)
			{
				newAchat.setNbSameElement(newAchat.getNbSameElement()+1);
				DaoAc.update(newAchat);

			}
			else
			{
				newAchat = new Achat(newEmp,1,0,"employe",parc);
				DaoAc.insert(newAchat);
			}
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent pour embaucher cet employe !");
		}	

	}




	private static void achatAttraction() {
		List<Attraction> allAttractions = DaoA.findAll();
		List<Achat> allAchatAttractions = DaoAc.findByType("attraction",parc);
		Attraction a1 = null;

		for (int i=0; i<allAchatAttractions.size();i++) 
		{
			a1 = (Attraction) allAchatAttractions.get(i).getElement();
			for (int j=0; j<allAttractions.size();j++)
			{
				if (a1.getId()==allAttractions.get(j).getId())
				{			
					allAttractions.remove(j);
				}
			}
		}
		
		if (allAttractions.isEmpty())
		{
			System.out.println("Vous avez acheté toutes les attractions disponibles");
			menuAchat();			
		}
		else
		{
			System.out.println("Voici toutes les attractions disponibles :");
			for (Attraction a : allAttractions){System.out.println(a);}
		}



		testSaisie = true;
		int choix=0;
		Attraction newattrac = null;

		while (testSaisie)
		{
			try {
				choix = saisieInt("Choississez votre nouvelle attraction (donner son numero) :");
				newattrac = DaoA.findById(choix);

				if (newattrac == null)
				{
					System.out.println("\nAttention cette attraction n'existe pas, réessayez");
				}

				else {testSaisie = false;}
			}
			catch (Exception e){
				System.out.println("\nAttention il faut rentrer un nombre entier");
			}
		}
		
		//parc = Context.getInstance().getParc();

		if (newattrac.getPrixAcquisition() <= parc.getArgent() && newattrac.getTaille() <= parc.getTaille())
		{
			Achat newAchat = new Achat(newattrac,0,"attraction",parc);
			Context.getInstance().getDaoAc().insert(newAchat);
			
			parc.setArgent(parc.getArgent()-newattrac.getPrixAcquisition());
			parc.setTaille(parc.getTaille()-newattrac.getTaille());
			Context.getInstance().setParc(parc);
			System.out.println("Attraction achetée !");
		}
		else
		{
			System.out.println("Vous n'avez pas assez d'argent ou de place pour acheter cet attraction !");
		}
	}




	private static void menuAmelioration() {
		 //&& parc.getAchat().getBoutiques().isEmpty() && parc.getAchat().getRestaurants().isEmpty())
		if (DaoAc.findByParc(parc).isEmpty())
		{
			System.out.println("Tu n'as pas de batiments ! \n Va en construire ! ");
			menuModification();
		}
		else {
			testSaisie = true;
			int choix=0;

			while (testSaisie)
			{
				try {
					System.out.println("\nAmeliorez vos bâtiments !");
					System.out.println("Choisir un menu");
					System.out.println("1- Améliorer une boutique");
					System.out.println("2- Améliorer un restaurant");
					System.out.println("3- Améliorer une attraction");
					System.out.println("4- Retour menu Modification");
					choix = saisieInt("");
					testSaisie = false;
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 4");
				}
			}

			switch(choix) 
			{
			case 1 : ameliorerBoutique();break;
			case 2 : ameliorerRestaurant();break;
			case 3 : ameliorerAttraction();break;
			case 4 : menuModification();break;
			default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 4");break;
			}
		}
		menuAmelioration();
	}



	private static void ameliorerAttraction() 
	{
		if (DaoAc.findByType("attraction",parc).isEmpty())
		{
			System.out.println("Vous n'avez pas d'attractions à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowAttraction();

			testSaisie = true;
			int choix=0;
			Attraction at=null;
			int idAchat=0;

			while (testSaisie)
			{
				try {
					choix = saisieInt("Choississez l'attraction à modifier (donner son numero) :");
					for (Achat a : DaoAc.findByType("attraction",parc)) 
					{
						if (a.getElement().getId() == choix) 
						{
							at = (Attraction) a.getElement();
							idAchat = a.getId();
							break;
						}
					}
					if(at == null) {System.out.println("\nAttention vous n'avez pas cette attraction, réessayez");}
					else {testSaisie = false;}
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un nombre entier");
				}
				
			}
			
			if (prixAmeliorationAttraction > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer cette attraction !");
			}
			else if (DaoAc.Nvamelioration(parc,at)>= at.getNbAmelioration())
			{
				System.out.println("Vous avez atteint le maximum d'améliorations de cette attraction !");
			}
			else
			{
				Achat a = DaoAc.findById(idAchat);
				a.setNiveauAmelioration(a.getNiveauAmelioration()+1);
				DaoAc.update(a);
				
				parc.setArgent(parc.getArgent()-prixAmeliorationAttraction);
				Context.getInstance().setParc(parc);
			}	
		}
	}




	private static void ameliorerRestaurant() {
		if (DaoAc.findByType("restaurant",parc).isEmpty())
		{
			System.out.println("Vous n'avez pas de restaurants à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowRestaurant();

			testSaisie = true;
			int choix=0;
			Restaurant restoToUp=null;
			int idAchat=0;

			while (testSaisie)
			{
				try {
					choix = saisieInt("Choississez le restaurant à modifier (donner son numero) :");
					for (Achat a : DaoAc.findByType("restaurant",parc)) 
					{
						if (a.getElement().getId() == choix) 
						{
							restoToUp = (Restaurant) a.getElement();
							idAchat = a.getId();
							break;
						}
					}
					if(restoToUp==null) {System.out.println("\nAttention vous n'avez pas ce restaurant, réessayez");}
					else {testSaisie = false;}
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un nombre entier");
				}
			}
			
			Achat a = DaoAc.findById(idAchat);			
			int actualAm = a.getNiveauAmelioration();
			

			if (prixAmeliorationRestaurant > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer ce restaurant !");
			}
			else if (actualAm>=restoToUp.getNbAmelioration())
			{
				System.out.println("Vous avez atteint le maximum d'améliorations pour ce restaurant !");
			}
			else
			{	
				a.setNiveauAmelioration(actualAm+1);
				DaoAc.update(a);
				
				parc.setArgent(parc.getArgent()-prixAmeliorationAttraction);
				Context.getInstance().setParc(parc);
			}
		}


	}

	private static void ameliorerBoutique() {
		if (DaoAc.findByType("boutique",parc).isEmpty())
		{
			System.out.println("Vous n'avez pas de magasin à améliorer");
			menuAmelioration();
		}
		else
		{
			ShowBoutique();

			testSaisie = true;
			int choix=0;
			Boutique b = null;
			int idAchat = 0;

			while (testSaisie)
			{
				try {
					choix = saisieInt("Choississez la boutique à modifier (donner son numero) :");
					for (Achat a : DaoAc.findByType("boutique",parc))
					{
						if (a.getElement().getId() == choix) 
						{
							b = (Boutique) a.getElement();
							idAchat = a.getId();
							break;
						}						
					}

					if(b == null)
					{System.out.println("\nAttention vous n'avez pas ce magasin, réessayez");}
					else {testSaisie = false;}
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un nombre entier");
				}
			}
			
			
			if (prixAmeliorationBoutique > parc.getArgent()) 
			{
				System.out.println("Vous n'avez pas assez d'argent pour améliorer cette boutique !");
			}
			else if (DaoAc.Nvamelioration(parc,b)>=b.getNbAmelioration())
			{
				System.out.println("Vous avez atteint le maximum d'améliorations de cette boutique !");
			}
			else
			{
				Achat a = DaoAc.findById(idAchat);
				a.setNiveauAmelioration(a.getNiveauAmelioration()+1);
				DaoAc.update(a);
				
				parc.setArgent(parc.getArgent()-prixAmeliorationBoutique);
				Context.getInstance().setParc(parc);
			}
		}
	}




	private static void menuPossession() {
		List<Achat> allAchats = DaoAc.findByParc(parc);

		if (allAchats.isEmpty())
		{
			System.out.println("Tu n'as pas de batiments ! \n Va en construire ! ");
			menuModification();
		}
		else 
		{
			testSaisie = true;
			int choix=0;

			while (testSaisie)
			{
				try {
					System.out.println("\nMenu des possessions !");
					System.out.println("Choisir un menu");
					System.out.println("1- Liste de mes Attracttions");
					System.out.println("2- Liste de mes Restaurants");
					System.out.println("3- Liste de mes Comodités");
					System.out.println("4- Liste de mes Boutiques");
					System.out.println("5- Liste des employés");
					System.out.println("6- Retour menu précédent");
					choix = saisieInt("");
					testSaisie = false;
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 6");
				}
			}

			switch (choix)
			{
			case 1 : ShowAttraction();break;
			case 2 : ShowRestaurant();break;
			case 3 : ShowCommodites();break;
			case 4 : ShowBoutique();break;
			case 5 : ShowEmploye();break;
			case 6 : menuModification();break;
			default : System.out.println("\nAttention il faut rentrer un chiffre entre 1 et 6");break;
			}
		}
	}



	private static void ShowEmploye() {
		List<Achat> allAchatEmp = DaoAc.findByType("employe",parc);

		if (allAchatEmp.isEmpty())
		{
			System.out.println("Vous ne possèdez pas d'employé");
		}
		else 
		{
			System.out.println("Voici les employes de votre parc :");
			for (Achat a : allAchatEmp)
			{
				Employe emp = (Employe) a.getElement();
				
				System.out.println(a.getNbSameElement()+" "+emp.getMetier());
			}
		}
	}




	private static void ShowAttraction() {
		List<Achat> allAchatAtt = DaoAc.findByType("attraction",parc);

		if (allAchatAtt.isEmpty())
		{
			System.out.println("Vous ne possèdez pas d'attraction");
		}
		else
		{
			System.out.println("Voici toutes les attractions présentes dans votre parc :");
			for (Achat a : allAchatAtt)
			{
				Attraction at = (Attraction)a.getElement();
				System.out.println(at);
			}
		}
	}



	private static void ShowRestaurant() {
		List<Achat> allAchatRestaurants = DaoAc.findByType("restaurant",parc);
		
		if (allAchatRestaurants.isEmpty())
		{
			System.out.println("Vous ne possèdez pas de restaurant");
		}
		else 
		{
			System.out.println("Voici touts les restaurants présents dans votre parc :");
			for (Achat a : allAchatRestaurants)
			{
				Restaurant r = (Restaurant)a.getElement();
				System.out.println(r);
			}
		}
	}



	private static void ShowCommodites() {
		List<Achat> allAchatCom = DaoAc.findByType("commodite",parc);

		if (allAchatCom.isEmpty())
		{
			System.out.println("Vous ne possèdez pas de commodites");
		}
		else 
		{
			System.out.println("Voici toutes les commodites présentes dans votre parc :");
			for (Achat a : allAchatCom)
			{
				Commodite c = (Commodite)a.getElement();
				System.out.println(c);
			}
		}
	}


	private static void ShowBoutique() {
		List<Achat> allAchatBoutique = DaoAc.findByType("boutique",parc);
		
		if (allAchatBoutique.isEmpty())
		{
			System.out.println("Vous ne possèdez pas de boutique");
		}
		else {
			System.out.println("Voici toutes les boutiques présentes dans votre parc :");

			for (Achat a : allAchatBoutique)
			{
				Boutique b = (Boutique)a.getElement();
				System.out.println(b);
			}
		}
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



	public static int generateRandomInt(int max){
		Random random = new Random();
		return random.nextInt(max);
	}
}
