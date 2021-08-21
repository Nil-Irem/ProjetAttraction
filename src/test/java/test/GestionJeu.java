package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.IDAO.IDAOParc;
import metier.Difficulte;
import metier.Joueur;
import metier.Parc;
import util.Context;


public class GestionJeu {

	static IDAOParc daoP = Context.getInstance().getDaoP();

	public static void creerPartie(){
		Joueur joueur =  Context.getInstance().getJoueur();
		Difficulte diff = Difficulte.Facile;
		boolean testDifficulte = true;
		System.out.println("Créons une nouvelle partie !");
		
		while (testDifficulte)
		{
			try {
				String choixDifficulte = saisieString("Veuillez choisir la difficulté parmi:\n "+ Arrays.toString(Difficulte.values()));
				diff = Difficulte.valueOf(choixDifficulte);
				testDifficulte = false;
			}
			catch (Exception e) {
				System.out.println("\nAttention, il faut bien écrire la difficulté");
			}
		}

		double argentJ = diff.getArgent();
		double tailleP=diff.getTailleParc();
		
		String nomParc= saisieString("Veuillez choisir un nom pour votre parc et le saisir");

		while(daoP.checkSameParcName(nomParc,joueur))
		{
			System.out.println("Vous avez déjà un parc avec ce nom");
			nomParc = saisieString("Veuillez choisir un autre nom");
		}

		Parc p = new Parc (joueur,nomParc,tailleP,0,argentJ,diff);
		Context.getInstance().setParc(daoP.insert(p));
		MenuJoueur.menuPartie();
	}




	public static void chargerPartie()
	{	
		Joueur joueur =  Context.getInstance().getJoueur();
		List<Parc> parcs = new ArrayList();
		parcs = daoP.findByJoueur(joueur);
		
		if(!parcs.isEmpty()) {
			
			System.out.println("Cher " + joueur.getLogin() + " sur quel parc souhaites-tu t'amuser ?");
			for (Parc p : parcs) {System.out.println(p);}

			boolean testSaisie = true;
			int choix=0;
			
			while (testSaisie)
			{
				try {
					choix = saisieInt("Saisir l'id du parc à selectionner");
					testSaisie = false;
				}
				catch (Exception e){
					System.out.println("\nAttention il faut rentrer un nombre entier");
				}
			}
			
			for(Parc p1 : parcs)
			{
				if( p1.getId() == choix) 
				{
					Context.getInstance().setParc(p1);
					MenuJoueur.menuPartie();
				}
			}
			System.out.println("Ce parc n'existe pas");
		}
		else
		{
			System.out.println("Désolé " +joueur.getLogin() + " tu n'as pas de partie à charger ! Retournes en creer une !");
			MenuJoueur.menuJoueur();
		}
	}



	public static void saveGame() {
		Context.getInstance().setParc(daoP.update(Context.getInstance().getParc()));
		System.out.println("Partie sauvegardée");
	}


	
	public static void deleteGame() {
		daoP.delete(Context.getInstance().getParc().getId());
		System.out.println("Partie supprimée");
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
