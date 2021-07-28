package test;

import java.util.Scanner;

import dao.DAOCompte;
import metier.Admin;
import metier.Compte;
import metier.Joueur;



public class Menu {
		
	static Compte connected=null;
	static DAOCompte daoC = new DAOCompte();

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
			System.out.println("\nBienvenue sur YoloCoaster Tycoon !");
			System.out.println("Choisir un menu");
			System.out.println("1- Se connecter");
			System.out.println("2- S'incrire");
			System.out.println("3- Fermer le jeu");
			int choix = saisieInt("");
			
			switch(choix) 
			{
			case 1 : seConnecter();break;
			case 2 : inscription();break;
			case 3 : System.exit(0);break;
			}
			menuPrincipal();
		}



		private static void inscription() {
			String login = saisieString("\nEntrez votre identifiant :");
			
			while (!DAOCompte.findByLogin(login))
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
			connected = DAOCompte.seConnecter(login,password);
		
			
			
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

		
		

	


	



	


	

	


		







		

