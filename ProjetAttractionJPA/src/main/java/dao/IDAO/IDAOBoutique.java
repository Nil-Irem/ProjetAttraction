package dao.IDAO;

import java.util.List;


import metier.Boutique;


public interface IDAOBoutique extends IDAO<Boutique,Integer> {

	public List<Boutique> filterBoutique(String mot);
}
