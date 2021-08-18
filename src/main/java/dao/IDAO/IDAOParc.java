package dao.IDAO;

import java.util.List;

import metier.Achat;
import metier.Parc;
import metier.Joueur;

public interface IDAOParc extends IDAO<Parc, Integer> {

	public List<Parc> filterParc (String mot);

	public List<Parc> findByIdJoueur(Joueur joueur);

	public boolean checkSameParcName(String nomParc, Joueur joueur);

	public Achat findWithBoutiques (int id);
	public Achat findWithAttractions (int id);
	public Achat findWithRestaurants (int id);
	public Achat findWithEmployes (int id);
	public Achat findWithCommodites (int id);
}