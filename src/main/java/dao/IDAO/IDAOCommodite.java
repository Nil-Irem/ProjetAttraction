package dao.IDAO;

import java.util.List;

import metier.Commodite;

public interface IDAOCommodite extends IDAO<Commodite, Integer> {
	public List<Commodite> filterCommodite(String mot);
	
}
