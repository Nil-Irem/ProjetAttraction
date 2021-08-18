package test;

import java.util.Scanner;

import dao.jpa.DAOCompteJPA;
import metier.Admin;
import metier.Compte;
import metier.Joueur;
import util.Context;



public class Menu {
		
	static Compte connected=null;
	static DAOCompteJPA daoC = new DAOCompteJPA();

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

		
	public static void menuPrincipal() {
		
		boolean testSaisie = true;
		int choix=0;
		
		while (testSaisie)
		{
			try {
				System.out.println("\nBienvenue sur YoloCoaster Tycoon !");
				System.out.println("Choisir un menu");
				System.out.println("1- Se connecter");
				System.out.println("2- S'incrire");
				System.out.println("3- Fermer le jeu");
				choix = saisieInt("");
				testSaisie = false;
			}
			catch (Exception e) {
				System.out.println("\nAttention, il faut entrer un chiffre entre 1 et 3");
			}
		}
			
			switch(choix) 
			{
			case 1 : seConnecter();break;
			case 2 : inscription();break;
			case 3 : System.exit(0); Context.getInstance().closeEmf();break;
			default : System.out.println("\nAttention, il faut entrer un chiffre entre 1 et 3");break;
			}
			menuPrincipal();
		}



		private static void inscription() {
			String login = saisieString("\nEntrez votre identifiant :");
			
			while (!daoC.findByLogin(login))
			{
				login = saisieString("\nL'identifiant "+login+ " est deja utilisé essayez autre chose :");	
			}
			String password = saisieString("Entrez votre mot de passe :");
			
			connected = new Joueur(login,password);
			connected = daoC.insert(connected);
			MenuJoueur.menuJoueur(connected);
		}
		
		
		
		
		private static void seConnecter() {
			String login = saisieString("\nEntrez votre identifiant :");
			String password = saisieString("Entrez votre mot de passe :");
			connected = daoC.seConnecter(login,password);
		
			
			
			if(connected instanceof Joueur) 
			{
				MenuJoueur.menuJoueur(connected);
			}
			else if (connected instanceof Admin) 
			{
				MenuAdmin.menuAdmin();
			}
			else 
			{
				System.out.println("\nIdentifiants invalides !");
				
				int choix = saisieInt("Veux-tu rééssayer ?\n1-OUI \n2-Non, retour menu principal");  
				
				switch(choix) 
				{
				case 1 : seConnecter();break;
				case 2 : menuPrincipal();break;
				case 3 : seConnecter();break;
				}
				
				
			}
		}
}

		
		

		

