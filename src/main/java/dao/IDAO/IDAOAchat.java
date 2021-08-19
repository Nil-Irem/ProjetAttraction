package dao.IDAO;

import java.util.List;
import metier.Achat;
import metier.Element;
import metier.Parc;


public interface IDAOAchat extends IDAO<Achat,Integer> {

	public List<Achat> findByType(String type,Parc parc);
	public Achat findByIdParc (Integer id_parc);
	public int Nvamelioration (Parc parc, Element element);
	public List<Achat> findByParc(Parc p);
	
	
}
