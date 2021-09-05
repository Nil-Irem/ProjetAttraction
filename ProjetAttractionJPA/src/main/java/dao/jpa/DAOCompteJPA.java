package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOCompte;
import metier.Compte;
import util.Context;

public class DAOCompteJPA implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Compte c = em.find(Compte.class,id);
		em.close();
		return c;
	}


	@Override
	public List<Compte> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Compte> comptes = em.createQuery("from Compte",Compte.class).getResultList();
		em.close();
		return comptes;
	}


	@Override
	public Compte insert(Compte c) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return c;
	}


	@Override
	public Compte update(Compte cIn) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Compte cOut=em.merge(cIn);
		em.getTransaction().commit();
		em.close();
		return cOut;
	}


	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Compte c = em.find(Compte.class,id);
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}


	@Override
	public Compte seConnecter(String login, String password) {
		Compte c = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Compte where login=:log and password=:pass",Compte.class);
			query.setParameter("log",login);
			query.setParameter("pass",password);
			c = (Compte) query.getSingleResult();
			em.close();
			}
		catch (Exception e) {e.printStackTrace();}
		return c;
	}


	@Override
	public boolean checkSameLogin(String login) {
		boolean b=true;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Compte where login = :lib",Compte.class);
			query.setParameter("lib", login);
			List<Compte> comptes = query.getResultList();
			em.close();
			if (comptes.isEmpty())
			{
				b = false;
			}
			else
			{
				b = true;
			}
		}
		catch(Exception e) {System.out.println("Il y a une erreur");e.printStackTrace();}
		return b;
	}


	@Override
	public List<Compte> filterCompte(String mot) {
		List<Compte> comptes = null;
		try {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query query= em.createQuery("from Compte c where c.login like :lib",Compte.class);
			query.setParameter("lib", "%"+mot+"%");
			comptes = query.getResultList();
			em.close();
		}
		catch(Exception e) {}
		return comptes;
	}
}
