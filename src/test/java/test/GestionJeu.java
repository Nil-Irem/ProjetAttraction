package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DAOCompte;
import dao.DAOParc;
import metier.Difficulte;
import metier.Joueur;
import metier.Parc;


public class GestionJeu {

	static List<Parc> SauvegardePartie = new ArrayList();
	static DAOParc daoP = new DAOParc();

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



	public static void creerPartie(Joueur joueur){
		String choixDifficulte = saisieString("Créons une nouvelle partie !\n Veuillez choisir la difficulté parmi:\n "+ Arrays.toString(Difficulte.values()));
		Difficulte diff = Difficulte.valueOf(choixDifficulte);
		double argentJ = diff.getArgent();
		double tailleP=diff.getTailleParc();

		String nomParc = saisieString("Veuillez choisir un nom pour votre parc et le saisir");

		Parc p = new Parc (nomParc,tailleP,0,argentJ,diff);
		
		daoP.insert(p);
		int idJ = joueur.getId();		
		daoP.update(p, idJ);
	}




	public static Parc chargerPartie(Joueur joueur)
	{	
		/*
			IN: Joueur joueur ==> Le compte Connected
			OUT: Parc ==> Le parc avec lequel le joueur veut joué 
						  (car il peut avoir plusieurs parc)
			WORK: -Afficher la liste des parcs du compte joueur (Appel BDD)
				  -Selection du parc via son id 
				  -Revoie de l'objet Parc vers 	(MenuJoueur) menuPartie(Parc parcJoueur)  
		 */

		List<Parc> parcs = new ArrayList();
		Parc p = null;
		
		
		System.out.println("Cher " + joueur.getLogin() + " Sur quel parc souhaites-tu t'amuser ?");
		
		parcs = daoP.findByIdJoueur(joueur.getId());
		System.out.println(parcs);

		int choix = saisieInt("Saisir l'id du parc � selectionner");
		
		for(Parc p1 : parcs)
		{
			if( p1.getId() == choix)
			{
				p=p1;
			}
		}
		return p;

	}



	public static void saveGame(Parc parc) {

		{
			daoP.update(parc);
		}
	

	}


	public static void deleteGame(int id_parc) {
	
		{
			daoP.delete(id_parc);
		}

	}

}
