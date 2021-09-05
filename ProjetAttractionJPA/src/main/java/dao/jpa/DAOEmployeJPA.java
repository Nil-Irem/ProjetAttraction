package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOEmploye;
import metier.Employe;
import util.Context;

public class DAOEmployeJPA implements IDAOEmploye {

	@Override
	public Employe findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Employe e = em.find(Employe.class,id);
		em.close();
		return e;
	}
	

	@Override
	public List<Employe> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Employe> employes = em.createQuery("from Employe",Employe.class).getResultList();
		em.close();
		return employes;
	}


	@Override
	public Employe insert(Employe e) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
		return e;
	}


	@Override
	public Employe update(Employe emp) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Employe e=em.merge(emp);
		em.getTransaction().commit();
		em.close();
		return e;
	}


	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Employe e = em.find(Employe.class,id);
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();	
	}
	

	@Override
	public List<Employe> filterEmploye(String mot) {
		List<Employe> employes = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Employe e where e.nom like :lib",Employe.class);
			query.setParameter("lib", "%"+mot+"%");
			employes = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return employes;
	}

}
