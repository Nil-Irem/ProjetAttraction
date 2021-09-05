package dao.IDAO;

import java.util.List;

import metier.Employe;

public interface IDAOEmploye extends IDAO<Employe, Integer>{

	public List<Employe> filterEmploye(String mot);
}
