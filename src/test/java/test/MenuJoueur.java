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
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : GestionJeu.creerPartie(joueur) ;break;
		case 2 : GestionJeu.chargerPartie(joueur);break;
		}
		menuJoueur(connected);
	}



	public static void menuPartie(Parc parcjoueur) {
		parc = parcjoueur;
		System.out.println("\nQue souhaitez-vous faire?");
		System.out.println("1- Sauvegarder la Partie");
		System.out.println("2- Supprimer la Partie");
		System.out.println("3- Jouer");
		System.out.println("4- Retour menu joueur");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : GestionJeu.saveGame(parc);break;
		case 2 : GestionJeu.deleteGame(joueur.getId());break;
		case 3 : menuJouer();break;
		case 4 : menuJoueur(joueur);break;
		}
		menuPartie(parc);
	}



	public static void menuJouer() {
		System.out.println("\nC'est le moment de jouer !");
		System.out.println("1- Modifier le parc");
		System.out.println("2- Finir la journée");
		System.out.println("3- Retour au menu Partie");
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
		
		for (Employe e : parc.getEmployes()) {
			salaire += e.getSalaire();
		}
		
		for (Attraction a : parc.getAttractions()) {
			capaciteMax += a.getAffluence();
			prixFonctionnement += a.getPrixFonctionnement();
		}
		
		for (Boutique b : parc.getBoutiques()) {
			capaciteMax += b.getAffluence();
			prixFonctionnement += b.getPrixFonctionnement();
		}
		
		for (Restaurant r : parc.getRestaurants()) {
			capaciteMax += r.getAffluence();
			prixFonctionnement += r.getPrixFonctionnement();
		}
		
		
		attractivite = (parc.getCommodites().size()+parc.getEmployes().size())/100;
		if (attractivite > 1) {attractivite = 1;}
		
		

		switch (tempsJournee)
		{
		case 0 : System.out.println("\nAujourd'hui il a beaucoup plu");
			nbVisiteur = capaciteMax*attractivite*0.1;
			break;
		case 1 : System.out.println("\nAujourd'hui il a un peu plu");
			nbVisiteur = capaciteMax*attractivite*0.5;
			break;
		case 2 : System.out.println("\nAujourd'hui il a fait nuageux");
			nbVisiteur = capaciteMax*attractivite*0.8;
			break;
		case 3 : System.out.println("\nAujourd'hui il a fait beau");
			nbVisiteur = capaciteMax*attractivite;
			break;
		case 4 : System.out.println("\nAujourd'hui il a fait tr�s chaud");
			nbVisiteur = capaciteMax*attractivite*0.5;
			break;
		}

		argentGagne = nbVisiteur*prixEntree;
		parc.setArgent(parc.getArgent()+argentGagne-salaire-prixFonctionnement);
		
		System.out.println("Vous avez re�u "+Math.round(nbVisiteur)+" visiteurs");
		System.out.println("Vous avez gagner "+argentGagne+"� et d�pens� "+(salaire+prixFonctionnement)+"�");
		System.out.println("Vous avez maintenant "+parc.getArgent()+"�");
	}

	public static void menuModification() {

		System.out.println("\n Quelles modifications voulez vous apporter au Parc ?");
		System.out.println("1- Ameliorer les structures deja en place");
		System.out.println("2- Achater de nouvelles strucutures");
		System.out.println("3- Voir nos possesions");
		System.out.println("4- Fin des modifications");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : menuAmelioration();break;
		case 2 : menuAchat();break;
		case 3 : menuPossesion();break;
		case 4 : menuJouer();break;
		}
		menuModification();
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
		saisieInt("Voici tous les restaurants disponibles :");
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
		saisieInt("Voici toutes les commodites disponibles :");
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
		saisieInt("Voici tous les magasins disponibles :");
		for (Boutique b : DaoB.findAll()){System.out.println(b);}

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
		saisieInt("Voici tous les employes disponibles :");
		for (Employe e : DaoE.findAll()){System.out.println(e);}

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
		saisieInt("Voici toutes les attractions disponibles :");
		for (Attraction a : DaoA.findAll()){System.out.println(a);}

		int choix = saisieInt("Choississez votre nouvel attraction (donner son numero) :");
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
		menuAmelioration();
	}



	private static void ameliorerAttraction() 
	{
		ShowAttraction();
		int choix = saisieInt("Choississez l'attraction à modifier (donner son numero) :");
		int i = 0;

		for (Attraction a : parc.getAttractions())
		{
			int idA = a.getId();
			if (idA == choix)
			{
				break;
			}


			else
			{
				i++;
			}
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


	private static void ameliorerRestaurant() {
		ShowRestaurant();
		int choix = saisieInt("Choississez le restaurant à modifier (donner son numero) :");
		int i = 0;

		for (Restaurant r : parc.getRestaurants())
		{
			int idR = r.getId();
			if (idR == choix)
			{
				break;
			}


			else
			{
				i++;
			}
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
	
	private static void ameliorerBoutique() {

		ShowBoutique();
		int choix = saisieInt("Choississez la boutique à modifier (donner son numero) :");
		int i = 0;

		for (Boutique bou : parc.getBoutiques())
		{
			int idBou = bou.getId();
			if (idBou == choix)
			{
				break;
			}


			else
			{
				i++;
			}
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
		System.out.println("Voici touts les employés présents dans votre parc :");

		for (Employe emp : parc.getEmployes())
		{
			System.out.println(emp);
		}
		
	}
	
	
	private static void ShowAttraction() {
		System.out.println("Voici toutes les attractions présentes dans votre parc :");

		for (Attraction a : parc.getAttractions())
		{
			System.out.println(a);
		}
		
	}

private static void ShowRestaurant() {
		System.out.println("Voici toutes les attractions présentes dans votre parc :");

		for (Restaurant r : parc.getRestaurants())
		{
			System.out.println(r);
		}
		
	}

	private static void ShowCommodites() {
		System.out.println("Voici toutes les attractions présentes dans votre parc :");

		for (Commodite c : parc.getCommodites())
		{
			System.out.println(c);
		}
		
	}

	private static void ShowBoutique() {
		System.out.println("Voici toutes les attractions présentes dans votre parc :");

		for (Boutique b : parc.getBoutiques())
		{
			System.out.println(b);
		}
		
	}


}
