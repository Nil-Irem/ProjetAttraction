package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOParc;
import metier.Achat;
import metier.Element;
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
		List<Achat> achats = Context.getInstance().getDaoAc().findByParc(p);
		em.getTransaction().begin();
		em.remove(p);
		for (Achat a : achats)
		{
			 Context.getInstance().getDaoAc().delete(a.getId());
		}
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public List<Parc> findByJoueur(Joueur joueur) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query query= em.createQuery("from Parc p where p.joueur = :lib",Parc.class);
		query.setParameter("lib", joueur);
		List<Parc> parcs = query.getResultList();
		em.close();
		return parcs;
	}


	@Override
	public boolean checkSameParcName(String nomParc, Joueur joueur) {
		boolean b=true;
		
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Parc where joueur = :lib and nom = :name",Parc.class);
			query.setParameter("lib", joueur);
			query.setParameter("name", nomParc);
			List<Parc> parcs = query.getResultList();
			em.close();
			if (parcs.isEmpty())
			{
				b = false;
			}
			else
			{
				b = true;
			}
		}
		catch(Exception e) {}
		return b;
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
}