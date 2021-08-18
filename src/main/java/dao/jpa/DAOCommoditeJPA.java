package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOCommodite;
import metier.Commodite;
import util.Context;

public class DAOCommoditeJPA implements IDAOCommodite {

	@Override
	public Commodite findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Commodite c = em.find(Commodite.class,id);
		em.close();
		return c;
	}


	@Override
	public List<Commodite> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		
		List<Commodite> commodites = em.createQuery("from Commodite",Commodite.class).getResultList();
		em.close();
		return commodites;
	}


	@Override
	public Commodite insert(Commodite c) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return c;
	}
	

	@Override
	public Commodite update(Commodite nonManaged) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Commodite managed=em.merge(nonManaged);
		em.getTransaction().commit();
		em.close();
		return managed;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Commodite c = em.find(Commodite.class,id);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Commodite> filterCommodite(String mot) {
		List<Commodite> commodites = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Commodite c where c.nom like :lib",Commodite.class);
			query.setParameter("lib", "%"+mot+"%");
			commodites = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return commodites;
	}

}
