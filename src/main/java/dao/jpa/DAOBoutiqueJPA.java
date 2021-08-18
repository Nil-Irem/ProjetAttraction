package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOBoutique;
import metier.Boutique;
import util.Context;

public class DAOBoutiqueJPA implements IDAOBoutique {

	@Override
	public Boutique findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Boutique b = em.find(Boutique.class,id);
		em.close();
		return b;
	}


	@Override
	public List<Boutique> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Boutique> boutiques = em.createQuery("from Boutique",Boutique.class).getResultList();
		em.close();
		return boutiques;
	}


	@Override
	public Boutique insert(Boutique b) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
		em.close();
		return b;
	}


	@Override
	public Boutique update(Boutique bIn) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Boutique bOut=em.merge(bIn);
		em.getTransaction().commit();
		em.close();
		return bOut;
	}


	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Boutique b = em.find(Boutique.class,id);
		em.getTransaction().begin();
		em.remove(b);
		em.getTransaction().commit();
		em.close();			
	}


	@Override
	public List<Boutique> filterBoutique(String mot) {
		List<Boutique> boutiques = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Boutique b where b.nom like :lib",Boutique.class);
			query.setParameter("lib", "%"+mot+"%");
			boutiques = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return boutiques;
	}

}
