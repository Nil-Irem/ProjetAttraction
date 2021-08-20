package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOAchat;
import metier.Achat;
import metier.Element;
import metier.Parc;
import util.Context;

public class DAOAchatJPA implements IDAOAchat {

	@Override
	public Achat findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Achat a = em.find(Achat.class,id);
		em.close();
		return a;
		
	}

	@Override
	public List<Achat> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		List<Achat> achats = em.createQuery("from Achat",Achat.class).getResultList();
		em.close();
		return achats;
	}

	@Override
	public Achat insert(Achat a)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		return a;
	}


	@Override
	public Achat update(Achat ac) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		Achat a =em.merge(ac);
		em.getTransaction().commit();
		em.close();
		return a;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Achat a = em.find(Achat.class,id);
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@Override
	public List<Achat> findByType(String type,Parc parc)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat where typeElement=:typeEl and parc=:parc", Achat.class);
		myQuery.setParameter("typeEl", type);
		myQuery.setParameter("parc", parc);
		List<Achat> a = myQuery.getResultList();
		em.close();
		return  a;	
	}
	
	
	@Override
	public int Nvamelioration (Parc parc, Element element) 
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query NvA = em.createQuery("from Achat where parc=:parc and element=:element", Achat.class);
		NvA.setParameter("parc", parc);
		NvA.setParameter("element", element);
		Achat a = (Achat) NvA.getSingleResult();
		em.close();
		return a.getNiveauAmelioration();
	}

	

	@Override
	public List<Achat> findByParc (Parc p)
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("from Achat where parc=:parc", Achat.class);
		myQuery.setParameter("parc", p);
		List<Achat> a = myQuery.getResultList();
		em.close();
		return  a;		
	}
	
	
}
