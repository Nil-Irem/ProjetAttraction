package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAORestaurant;
import metier.Restaurant;
import util.Context;

public class DAORestaurantJPA implements IDAORestaurant {

	@Override
	public Restaurant findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Restaurant r = em.find(Restaurant.class,id);
		em.close();
		return r;
	}

	@Override
	public List<Restaurant> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Restaurant> restaurants = em.createQuery("from Restaurant",Restaurant.class).getResultList();
		em.close();
		return restaurants;
	}

	@Override
	public Restaurant insert(Restaurant r) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
		em.close();
		return r;
	}

	@Override
	public Restaurant update(Restaurant restau) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Restaurant r=em.merge(restau);
		em.getTransaction().commit();
		em.close();
		return r;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Restaurant r = em.find(Restaurant.class,id);
		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
		em.close();		
		
	}

	@Override
	public List<Restaurant> filterRestaurant(String mot) {
		List<Restaurant> restaurants = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Restaurant r where r.nom like :lib",Restaurant.class);
			query.setParameter("lib", "%"+mot+"%");
			restaurants = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return restaurants;
	}
	
	
}
