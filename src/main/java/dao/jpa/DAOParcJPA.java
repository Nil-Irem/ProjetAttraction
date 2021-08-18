package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOParc;
import metier.Achat;
import metier.Joueur;
import metier.Parc;
import util.Context;

public class DAOParcJPA implements IDAOParc {

	@Override
	public Parc findById(Integer id) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Parc p = em.find(Parc.class,id);
		em.close();
		return p;
	}

	@Override
	public List<Parc> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		//Query query= em.createQuery("from Recette",Recette.class);
		//List<Recette> recettes = query.getResultList();
		List<Parc> parcs = em.createQuery("from Parc",Parc.class).getResultList();
		em.close();
		return parcs;
	}

	@Override
	public Parc insert (Parc p) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		return p;
	}

	@Override
	public Parc update(Parc nonManaged) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Parc managed=em.merge(nonManaged);
		em.getTransaction().commit();
		em.close();
		return managed;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Parc p = em.find(Parc.class,id);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Parc> filterParc(String mot) {
		
		List<Parc> parcs = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Parc p where p.nom like :lib",Parc.class);
			query.setParameter("lib", "%"+mot+"%");
			parcs = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return parcs;
	}

	public List<Parc> findByIdJoueur(Joueur joueur) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query query= em.createQuery("from Parc p where p.joueur = :lib",Parc.class);
		query.setParameter("lib", joueur);
		List<Parc> parcs = query.getResultList();
		em.close();
		return parcs;
	}

	public boolean checkSameParcName(String nomParc, Joueur joueur) {
		boolean b=false;
		
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Parc where p.joueur = :lib and nom = :name",Parc.class);
			query.setParameter("lib", joueur);
			query.setParameter("name", nomParc);
			List<Parc> parcs = query.getResultList();
			em.close();
			if (parcs.isEmpty())
			{
				b = true;
			}
			else
			{
				b = false;
			}
			
		}
		catch(Exception e) {}
		return b;
	}


	public Achat findWithBoutiques (int id)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat a left join fetch a.boutiques b where a.id =:id", Achat.class);
		myQuery.setParameter("id", id);
		em.close();
		return (Achat) myQuery.getSingleResult() ;
	
	}
	
	public Achat findWithAttractions (int id)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat a left join fetch a.attractions attrac where a.id =:id", Achat.class);
		myQuery.setParameter("id", id);
		em.close();
		return (Achat) myQuery.getSingleResult() ;
	
	}
	
	public Achat findWithRestaurants (int id)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat a left join fetch a.restaurants r where a.id =:id", Achat.class);
		myQuery.setParameter("id", id);
		em.close();
		return (Achat) myQuery.getSingleResult() ;
	
	}
	
	public Achat findWithEmployes (int id)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat a left join fetch a.employes e where a.id =:id", Achat.class);
		myQuery.setParameter("id", id);
		em.close();
		return (Achat) myQuery.getSingleResult() ;
	
	}
	
	public Achat findWithCommodites (int id)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat a left join fetch a.commodites c where a.id =:id", Achat.class);
		myQuery.setParameter("id", id);
		em.close();
		return (Achat) myQuery.getSingleResult() ;
	
	}
	
}


