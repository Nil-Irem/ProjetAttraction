package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Attraction;
import metier.Boutique;
import metier.Commodite;
import metier.Difficulte;
import metier.Employe;
import metier.Parc;
import metier.Restaurant;

public class DAOParc implements IDAO<Parc,Integer> {

	@Override
	public Parc findById(Integer id) {
		Parc p = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from Parc where id_Parc=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				List <Attraction> attractions = DAOLien.findAllAttractionById(rs.getInt("id_parc"));
				List <Boutique> boutiques = DAOLien.findAllBoutiqueById(rs.getInt("id_parc"));
				List <Commodite> commodites = DAOLien.findAllCommoditeById(rs.getInt("id_parc"));
				List <Employe> employes = DAOLien.findAllEmployeById(rs.getInt("id_parc"));
				List <Restaurant> restaurants = DAOLien.findAllRestaurantById(rs.getInt("id_parc"));
				 p = new Parc(rs.getInt("id_parc"),rs.getString("nom"),boutiques,attractions,restaurants,employes,commodites,
				 	rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")));
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
				List <Attraction> attractions = DAOLien.findAllAttractionById(rs.getInt("id_parc"));
				List <Boutique> boutiques = DAOLien.findAllBoutiqueById(rs.getInt("id_parc"));
				List <Commodite> commodites = DAOLien.findAllCommoditeById(rs.getInt("id_parc"));
				List <Employe> employes = DAOLien.findAllEmployeById(rs.getInt("id_parc"));
				List <Restaurant> restaurants = DAOLien.findAllRestaurantById(rs.getInt("id_parc"));
				Parc p = new Parc(rs.getInt("id_parc"),rs.getString("nom"),boutiques,attractions,restaurants,employes,commodites,
				 	rs.getDouble("taille"),rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")));
				
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
		System.out.println("Attention une fonction inutile a été appelé (insert Parc)");
		return p;
		}
		



	public Parc insert(Parc p,int id_joueur) {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into parc (nom,argent,nbjour,taille,typeDifficulte,id_joueur) VALUES (?,?,?,?,?,?)");

			ps.setString(1, p.getNomParc());
			ps.setDouble(2, p.getArgent());
			ps.setInt(3, p.getNbjour());
			ps.setDouble(4, p.getTaille());
			ps.setString(5, p.getTypeDifficulte().toString());
			ps.setInt(6, id_joueur);
			ps.executeUpdate();
			
			PreparedStatement ps1 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = ps1.executeQuery();
			
			rs.next();
			p.setId(rs.getInt(1));
			
			ps1.close();
			rs.close();
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return p;
	}
	

	
	public Parc update(Parc p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE Parc set nom=?,argent=?,nbjour=?,taille=?,typeDifficulte=? where id_parc=?");

			ps.setString(1, p.getNomParc());
			ps.setDouble(2, p.getArgent());
			ps.setInt(3, p.getNbjour());
			ps.setDouble(4, p.getTaille());
			ps.setString(5, p.getTypeDifficulte().toString());
			ps.setInt(6, p.getId());
			ps.executeUpdate();
			
			DAOLien.deleteAttraction(p.getId());
			DAOLien.deleteBoutique(p.getId());
			DAOLien.deleteCommodite(p.getId());
			DAOLien.deleteEmploye(p.getId());
			DAOLien.deleteRestaurant(p.getId());
			
			for (Attraction a : p.getAttractions()){DAOLien.insertAttraction(p.getId(),a.getId());}
			for (Boutique b : p.getBoutiques()){DAOLien.insertBoutique(p.getId(),b.getId());}
			for (Commodite c : p.getCommodites()){DAOLien.insertCommodite(p.getId(),c.getId());}
			for (Employe e : p.getEmployes()){DAOLien.insertEmploye(p.getId(),e.getId());}
			for (Restaurant r : p.getRestaurants()){DAOLien.insertRestaurant(p.getId(),r.getId());}

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return p;
	}
	
	
	

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from parc where id_parc=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			DAOLien.deleteAttraction(id);
			DAOLien.deleteBoutique(id);
			DAOLien.deleteCommodite(id);
			DAOLien.deleteEmploye(id);
			DAOLien.deleteRestaurant(id);			
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
	

	public List<Parc> findByIdJoueur(int id) {
		List<Parc> parcs = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from parc WHERE id_joueur = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				//String nomParc,double taille, int nbjour,double argent,Difficulte typeDifficulte
				
				Parc p = new Parc(rs.getInt("id_parc"),rs.getString("nom"),rs.getDouble("taille"),
							rs.getInt("nbjour"),rs.getDouble("argent"),Difficulte.valueOf(rs.getString("typeDifficulte")));
				
				parcs.add(p);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return parcs;
	}



	public boolean checkSameParcName(String nomParc, int id_joueur) 
	{	/*
			BUT: Verifier qu'un parc du même nom n'est pas déja présent
			IN ==> Le nom du parc a verifier
			OUT==> boolean: TRUE: Nom de parc existant
							FALSE: Nom de parc dispo
		*/
		
		boolean b=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
	
			PreparedStatement ps = conn.prepareStatement("SELECT * from parc where id_joueur = ? AND nom like ?");
			ps.setInt(1, id_joueur);
			ps.setString(2, nomParc);
	
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next())
			{	
				b= false;
			}
			else
			{
				b=true;
			}
			
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return b;
	}

}