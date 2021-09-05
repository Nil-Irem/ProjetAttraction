package test;

import java.util.Scanner;

import dao.jpa.DAOAttractionJPA;


public class MenuAdmin {
	
	

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


	public static void menuAdmin() {

		System.out.println("\nC'est parti pour jouer !");
		System.out.println("1- Menu Attraction");
		System.out.println("2- Menu Boutique");
		System.out.println("3- Menu Restaurant");
		System.out.println("4- Menu Employe");
		System.out.println("5- Menu Commodite");
		System.out.println("6- Supprimer un joueur");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : menuAttraction();break;
		case 2 : menuBoutique();break;
		case 3 : menuRestaurant();break;
		case 4 : menuEmploye();break;
		case 5 : menuCommodite();break;
		case 6 : supprimmerJoueur();break;
		}
		Menu.menuPrincipal();

	}


	private static void supprimmerJoueur() {


	}
	

	private static void menuAttraction() {
		System.out.println("\nBienvenue dans le Menu Attraction!");
		System.out.println("1- Ajouter une attraction");
		System.out.println("2- Modifier une attraction");
		System.out.println("3- Supprimer une attraction");
		System.out.println("4- Retour Menu Admin");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ajoutAttraction();break;
		case 2 : modifAttraction();break;
		case 3 : suppAttraction();break;
		case 4 : menuAdmin();break;

		}
		menuAdmin();


	}
	
	
	private static void suppAttraction() {
		// TODO Auto-generated method stub
		
	}
	private static void modifAttraction() {
		// TODO Auto-generated method stub
		
	}
	private static void ajoutAttraction() {
		
		
	}
	
	
	private static void menuBoutique() {
		System.out.println("\nBienvenue dans le Menu Boutique!");
		System.out.println("1- Ajouter une boutique");
		System.out.println("2- Modifier une boutique");
		System.out.println("3- Supprimer une boutique");
		System.out.println("4- Retour Menu Admin");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ajoutBoutique();break;
		case 2 : modifBoutique();break;
		case 3 : suppBoutique();break;
		case 4 : menuAdmin();break;

		}
		menuAdmin();

	}

	private static void suppBoutique() {
		// TODO Auto-generated method stub
		
	}
	private static void modifBoutique() {
		// TODO Auto-generated method stub
		
	}
	private static void ajoutBoutique() {
		// TODO Auto-generated method stub
		
	}

	

	
	private static void menuRestaurant() {
		System.out.println("\nBienvenue dans le Menu Restaurant!");
		System.out.println("1- Ajouter un restaurant");
		System.out.println("2- Modifier un restaurant");
		System.out.println("3- Supprimer un restaurant");
		System.out.println("4- Retour Menu Admin");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ajoutRestaurant();break;
		case 2 : modifRestaurant();break;
		case 3 : suppRestaurant();break;
		case 4 : menuAdmin();break;

		}
		menuAdmin();

	}

	private static void suppRestaurant() {
		// TODO Auto-generated method stub

	}


	private static void modifRestaurant() {
		// TODO Auto-generated method stub

	}


	private static void ajoutRestaurant() {
		// TODO Auto-generated method stub

	}

	private static void menuEmploye() {
		System.out.println("\nBienvenue dans le Menu Employe!");
		System.out.println("1- Ajouter un employe");
		System.out.println("2- Modifier un employe");
		System.out.println("3- Supprimer un employe");
		System.out.println("4- Retour Menu Admin");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ajoutEmploye();break;
		case 2 : modifEmploye();break;
		case 3 : suppEmploye();break;
		case 4 : menuAdmin();break;

		}
		menuAdmin();

	}


	private static void suppEmploye() {
		// aller chercher l'id de l'employe 

	}


	private static void modifEmploye() {
		// TODO Auto-generated method stub

	}


	private static void ajoutEmploye() {
		// TODO Auto-generated method stub

	}

	
	
	private static void menuCommodite() {
		System.out.println("\nBienvenue dans le Menu Commodites!");
		System.out.println("1- Ajouter Commodite");
		System.out.println("2- Supprimer Commodite");
		System.out.println("3- Retour Menu Admin");
		int choix = saisieInt("Choisir un menu");

		switch(choix) 
		{
		case 1 : ajoutCommodite();break;
		case 2 : suppCommodite();break;
		case 3 : menuAdmin();break;

		}
		menuAdmin();
	}


	private static void suppCommodite() {
		

	}


	private static void ajoutCommodite() {
		

	}

	

	




}
