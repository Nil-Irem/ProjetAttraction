package dao.IDAO;

import java.util.List;

import metier.Attraction;

public interface IDAOAttraction extends IDAO<Attraction,Integer> {

	public List<Attraction> filterAttraction(String mot);
	
}
