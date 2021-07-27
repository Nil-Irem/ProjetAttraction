package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.DAOLien;
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

		//boucle while verif nom parc 
		String nomParc = saisieString("Veuillez choisir un nom pour votre parc et le saisir");

		Parc p = new Parc (nomParc,tailleP,0,argentJ,diff);
		
		daoP.insert(p,joueur.getId());
		MenuJoueur.menuPartie(p);
	}




	public static void chargerPartie(Joueur joueur)
	{	
		List<Parc> parcs = new ArrayList();
		parcs = daoP.findByIdJoueur(joueur.getId());
		
		if(!parcs.isEmpty()) {
			for (Parc p : parcs) {
				p.setAttractions(DAOLien.findAllAttractionById(p.getId()));
				p.setBoutiques(DAOLien.findAllBoutiqueById(p.getId()));
				p.setCommodites(DAOLien.findAllCommoditeById(p.getId()));
				p.setEmployes(DAOLien.findAllEmployeById(p.getId()));
				p.setRestaurants(DAOLien.findAllRestaurantById(p.getId()));
			}
			
			System.out.println("Cher " + joueur.getLogin() + " Sur quel parc souhaites-tu t'amuser ?");
			System.out.println(parcs);

			int choix = saisieInt("Saisir l'id du parc à selectionner");
			
			for(Parc p1 : parcs)
			{
				if( p1.getId() == choix) {MenuJoueur.menuPartie(p1);}
			}
		}
		
		else
		{
			System.out.println("Désolé " +joueur.getLogin() + " tu n'as pas de partie à charger ! Retournes en creer une !");
			MenuJoueur.menuJoueur(joueur);
		}
	}



	public static void saveGame(Parc parc) {
			daoP.update(parc);
	}


	
	public static void deleteGame(int id_parc) {
			daoP.delete(id_parc);
	}

}
