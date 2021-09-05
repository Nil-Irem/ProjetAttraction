package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOParc;

import metier.*;
import util.Context;

public class DAOParcJDBC implements IDAOParc {

	@Override
	public Parc findById(Integer id) {
		Parc p = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from parc where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Joueur joueur = (Joueur) Context.getInstance().getDaoCpt().findById(rs.getInt("id_joueur"));
				 p = new Parc(rs.getInt("id"),rs.getString("nom"),rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")),joueur);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return p;
	}
	

	
	@Override
	public List<Parc> findAll() {
		List<Parc> parcs = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from parc");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Joueur joueur = (Joueur) Context.getInstance().getDaoCpt().findById(rs.getInt("id_joueur"));
				Parc p = new Parc(rs.getInt("id"),rs.getString("nom"),rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")),joueur);
				parcs.add(p);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return parcs;
	}
	
	
	
	
	@Override
	public Parc insert(Parc p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into parc (nom,argent,nbjour,taille,typeDifficulte,id_joueur) VALUES (?,?,?,?,?,?)");

			ps.setString(1, p.getNomParc());
			ps.setDouble(2, p.getArgent());
			ps.setInt(3, p.getNbjour());
			ps.setDouble(4, p.getTaille());
			ps.setString(5, p.getTypeDifficulte().toString());
			ps.setInt(6, p.getJoueur().getId());
			ps.executeUpdate();

			ps.close();
			
			PreparedStatement ps1 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps1.executeQuery();
			
			rs.next();
			p.setId(rs.getInt(1));

			rs.close();
			ps1.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return p;
	}

	

	@Override
	public Parc update(Parc p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE parc set nom=?,argent=?,nbjour=?,taille=?,typeDifficulte=?,id_joueur=? where id=?");

			ps.setString(1, p.getNomParc());
			ps.setDouble(2, p.getArgent());
			ps.setInt(3, p.getNbjour());
			ps.setDouble(4, p.getTaille());
			ps.setString(5, p.getTypeDifficulte().toString());
			ps.setInt(6, p.getJoueur().getId());
			ps.setInt(7, p.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return p;
	}
	
	

	@Override
	public void delete(Integer id) {
		try {
			Parc p = findById(id);

			List<Achat> achats = Context.getInstance().getDaoAc().findByParc(p);
			for (Achat a : achats) 
			{
				Context.getInstance().getDaoAc().delete(a.getId());
			}
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from parc where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
						
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
	

	@Override
	public List<Parc> findByJoueur(Joueur joueur) {
		List<Parc> parcs = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from parc WHERE id_joueur = ?");
			ps.setInt(1, joueur.getId());
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Parc p = new Parc(rs.getInt("id"),rs.getString("nom"),rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")),joueur);				
				parcs.add(p);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return parcs;
	}



	@Override
	public boolean checkSameParcName(String nomParc, Joueur joueur) 
	{
		boolean b=true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
	
			PreparedStatement ps = conn.prepareStatement("SELECT * from parc where id_joueur = ? AND nom like ?");
			ps.setInt(1, joueur.getId());
			ps.setString(2, nomParc);
	
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {b= true;}
			else {b=false;}
			
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return b;
	}

	

	@Override
	public List<Parc> filterParc(String mot) {
		List<Parc> parcs = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from parc where nom like ?");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Joueur joueur = (Joueur) Context.getInstance().getDaoCpt().findById(rs.getInt("id_joueur"));
				Parc p = new Parc(rs.getInt("id"),rs.getString("nom"),rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")),joueur);
				parcs.add(p);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return parcs;
	}

}