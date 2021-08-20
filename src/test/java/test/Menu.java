package test;

import java.util.Scanner;

import dao.IDAO.IDAOCompte;
import metier.Admin;
import metier.Compte;
import metier.Joueur;
import util.Context;



public class Menu {
		
	static Compte connected=Context.getInstance().getConnected();
	static IDAOCompte daoCpt = Context.getInstance().getDaoCpt();

		
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
			
			while (daoCpt.findByLogin(login))
			{
				login = saisieString("\nL'identifiant "+login+ " est déjà utilisé essayez autre chose :");
			}
			String password = saisieString("Entrez votre mot de passe :");
			
			connected = new Joueur(login,password);
			Context.getInstance().setConnected(daoCpt.insert(connected));
			Context.getInstance().setJoueur((Joueur) Context.getInstance().getConnected());
			
			MenuJoueur.menuJoueur();
		}
		
		
		
		
		private static void seConnecter() {
			String login = saisieString("\nEntrez votre identifiant :");
			String password = saisieString("Entrez votre mot de passe :");		
			Context.getInstance().setConnected(daoCpt.seConnecter(login,password));
			
			connected = Context.getInstance().getConnected();
			
			if(connected instanceof Joueur) 
			{
				Context.getInstance().setJoueur((Joueur) connected);
				MenuJoueur.menuJoueur();
			}
			else if (connected instanceof Admin) 
			{
				MenuAdmin.menuAdmin();
			}
			else 
			{
				System.out.println("\nIdentifiants invalides !");
				boolean testSaisie = true;
				int choix=0;
		
				while (testSaisie)
				{
					try {
						choix = saisieInt("Voulez-vous rééssayer ?\n1-OUI \n2-Non, retour menu principal");
						switch(choix) 
						{
							case 1 : seConnecter();break;
							case 2 : menuPrincipal();break;
							default : System.out.println("\nAttention, il faut entrer un chiffre entre 1 et 2");break;
						}
						testSaisie = false;
					}
					catch(Exception e) {
						System.out.println("\nAttention, il faut entrer un chiffre entre 1 et 2");
					}
				}
				seConnecter();
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
}

		
		

		

