package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAO.*;
import dao.jpa.*;
import metier.Compte;
import metier.Joueur;
import metier.Parc;

public class Context {

	private Joueur joueur = null;
	private Parc parc = null;
	private Compte connected=null;
	private IDAOAttraction DaoA = new DAOAttractionJPA();
	private IDAOBoutique DaoB = new DAOBoutiqueJPA();
	private IDAOCommodite DaoC = new DAOCommoditeJPA();
	private IDAOCompte DaoCpt = new DAOCompteJPA();
	private IDAOEmploye DaoE = new DAOEmployeJPA();
	private IDAORestaurant DaoR = new DAORestaurantJPA();
	private IDAOParc DaoP = new DAOParcJPA();
	private IDAOAchat DaoAc = new DAOAchatJPA();
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetattraction");

	private static Context _instance=null;



	private Context() {}


	public static Context getInstance() 
	{
		if(_instance==null) {
			_instance=new Context();
		}
		return _instance;
	}


	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public Parc getParc() {
		return parc;
	}


	public void setParc(Parc parc) {
		this.parc = parc;
	}


	public Compte getConnected() {
		return connected;
	}


	public void setConnected(Compte connected) {
		this.connected = connected;
	}


	public IDAOAttraction getDaoA() {
		return DaoA;
	}


	public IDAOBoutique getDaoB() {
		return DaoB;
	}


	public IDAOCommodite getDaoC() {
		return DaoC;
	}


	public IDAOCompte getDaoCpt() {
		return DaoCpt;
	}


	public IDAOEmploye getDaoE() {
		return DaoE;
	}


	public IDAORestaurant getDaoR() {
		return DaoR;
	}


	public IDAOParc getDaoP() {
		return DaoP;
	}


	public IDAOAchat getDaoAc() {
		return DaoAc;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}


	public void closeEmf() 
	{
		emf.close();
	}

	
	
}
