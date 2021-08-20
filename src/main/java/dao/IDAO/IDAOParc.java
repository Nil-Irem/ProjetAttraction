package dao.IDAO;

import java.util.List;

import metier.Achat;
import metier.Element;
import metier.Joueur;
import metier.Parc;

public interface IDAOParc extends IDAO<Parc, Integer> {

	public List<Parc> filterParc (String mot);
	public List<Parc> findByIdJoueur(Joueur joueur);

	public boolean checkSameParcName(String nomParc, Joueur joueur);





}