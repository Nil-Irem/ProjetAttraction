package attraction.menu;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import attraction.model.Admin;
import attraction.model.Compte;
import attraction.model.Joueur;
import attraction.model.Parc;
import attraction.repositories.CompteRepository;


@Service
public class Menu {
	
	@Autowired
	CompteRepository cptRepo;
	@Autowired
	MenuJoueur menuJoueur;
	@Autowired
	MenuAdmin menuAdmin;

	public Compte connected=null;
	public Admin admin=null;
	public Joueur joueur=null;
	public Parc parc=null;
	
	

		
	public void menuPrincipal() {
		
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
			case 3 : System.exit(0);break;
			default : System.out.println("\nAttention, il faut entrer un chiffre entre 1 et 3");break;
			}
			menuPrincipal();
		}



		private void inscription() {
			String login = saisieString("\nEntrez votre identifiant :");
			
			while (!cptRepo.findByLogin(login).isEmpty())
			{
				login = saisieString("\nL'identifiant "+login+ " est déjà utilisé essayez autre chose :");
			}
			String password = saisieString("Entrez votre mot de passe :");
			
			joueur = new Joueur(login,password);
			joueur = cptRepo.save(joueur);
			
			menuJoueur.menuJoueur2();
		}
		
		
		
		
		private void seConnecter() {
			String login = saisieString("\nEntrez votre identifiant :");
			String password = saisieString("Entrez votre mot de passe :");

			if (!cptRepo.findByLoginAndPassword(login,password).isEmpty())
			{
				connected = cptRepo.findByLoginAndPassword(login,password).get();
				if(connected instanceof Joueur) 
				{
					joueur = (Joueur) connected;
					
					menuJoueur.menuJoueur2();
				}
				else if (connected instanceof Admin) 
				{
					admin = (Admin) connected;
					menuAdmin.menuAdmin();
				}
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
		
		
		
	public int saisieInt(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}
	
	public double saisieDouble(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextDouble();
	}
	
	
	public String saisieString(String msg) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}
	
	
	public Joueur getJoueur()
	{
		return joueur;
	}
	
	
	public void setJoueur(Joueur newjoueur)
	{
		joueur = newjoueur;
	}
	
	
	
	public Parc getParc()
	{
		return parc;
	}
	
	
	public void setParc(Parc newparc)
	{
		parc = newparc;
	}
	
	
	
	public Admin getAdmin()
	{
		return admin;
	}
	
	
	public void setAdmin(Admin newadmin)
	{
		admin = newadmin;
	}
}

		
		

		

