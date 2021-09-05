package attraction.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import attraction.model.Difficulte;
import attraction.model.Joueur;
import attraction.model.Parc;
import attraction.repositories.ParcRepository;


@Service
public class GestionJeu {

	@Autowired
	 ParcRepository parcRepo;
	@Autowired
	 Menu menu;
	@Autowired
	 MenuJoueur menuJoueur;


	public  void creerPartie(){
		Joueur joueur = menu.getJoueur();
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

		while(!parcRepo.findByNomParcAndJoueur(nomParc, joueur).isEmpty())
		{
			System.out.println("Vous avez déjà un parc avec ce nom");
			nomParc = saisieString("Veuillez choisir un autre nom");
		}

		Parc p = new Parc (joueur,nomParc,tailleP,0,argentJ,diff);
		menu.setParc(p);
		parcRepo.save(p);
		menuJoueur.menuPartie();
	}




	public  void chargerPartie()
	{	
		Joueur joueur = menu.getJoueur();
		List<Parc> parcs = new ArrayList();
		parcs = parcRepo.findByJoueur(joueur);
		
		if(!parcs.isEmpty()) {
			
			System.out.println("Cher " + joueur.getLogin() + " Sur quel parc souhaites-tu t'amuser ?");
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
					menu.setParc(p1);
					menuJoueur.menuPartie();
				}
			}
			System.out.println("Ce parc n'existe pas");
		}
		else
		{
			System.out.println("Désolé " +joueur.getLogin() + " tu n'as pas de partie à charger ! Retournes en creer une !");
			menuJoueur.menuJoueur2();
		}
	}



	public  void saveGame() {
		Parc parc = menu.getParc();
		parcRepo.save(parc);
		System.out.println("Partie sauvegardée");
	}


	
	public  void deleteGame() {
		Parc parc = menu.getParc();
		parcRepo.delete(parc);
		System.out.println("Partie supprimée");
	}




	public  int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}

	public  double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}


	public  String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}

}
