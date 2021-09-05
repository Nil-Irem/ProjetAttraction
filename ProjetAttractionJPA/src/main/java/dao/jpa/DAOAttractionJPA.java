package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOAttraction;
import metier.Attraction;
import util.Context;

public class DAOAttractionJPA implements IDAOAttraction{

	@Override
	public Attraction findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Attraction a = em.find(Attraction.class,id);
		em.close();
		return a;
	}
	

	@Override
	public List<Attraction> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Attraction> attractions = em.createQuery("from Attraction",Attraction.class).getResultList();
		em.close();
		return attractions;
	}


	@Override
	public Attraction insert(Attraction a) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		return a;
	}


	@Override
	public Attraction update(Attraction at) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Attraction a=em.merge(at);
		em.getTransaction().commit();
		em.close();
		return a;
	}


	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Attraction a = em.find(Attraction.class,id);
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		em.close();	
	}


	@Override
	public List<Attraction> filterAttraction(String mot) {
		List<Attraction> attractions = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Attraction a where a.nom like :lib",Attraction.class);
			query.setParameter("lib", "%"+mot+"%");
			attractions = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return attractions;
	}

}
