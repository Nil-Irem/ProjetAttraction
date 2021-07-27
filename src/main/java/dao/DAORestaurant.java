package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Restaurant;

public class DAORestaurant implements IDAO<Restaurant,Integer> {

	@Override
	public Restaurant findById(Integer id) {
		Restaurant r = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from restaurant where id_restaurant=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 r = new Restaurant(rs.getInt("id_restaurant"), rs.getString("nom"), rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_ameliorations"), rs.getDouble("taux_incident"), rs.getDouble("taille"), rs.getInt("affluence_max"),rs.getDouble("revenuJourPersonne"));	
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return r;

		
	
	}

	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> restaurants = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from restaurant");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Restaurant r = new Restaurant(rs.getInt("id_restaurant"), rs.getString("nom"), rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_ameliorations"), rs.getDouble("taux_incident"), rs.getDouble("taille"), rs.getInt("affluence_max"),rs.getDouble("revenuJourPersonne"));	
				restaurants.add(r);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return restaurants;
	}



	@Override
	public Restaurant insert(Restaurant r) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into restaurant (nom,prix_acquisition,prix_fonctionnement,nb_ameliorations,affluence_max, revenuJourPersonne, taux_incident,taille) VALUES (?,?,?,?,?,?,?,?)");
			
			ps.setString(1, r.getNom());
			ps.setDouble(2, r.getPrixAcquisition());
			ps.setDouble(3, r.getPrixFonctionnement());
			ps.setInt(4, r.getNbAmelioration());
			ps.setInt(5, r.getAffluence());
			ps.setDouble(6, r.getrevenuJourPersonne());
			ps.setDouble(7, r.getTauxIncident());
			ps.setDouble(8, r.getTaille());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return r;
	}

	@Override
	public Restaurant update(Restaurant r) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE restaurant set nom=?,prix_acquisition=?,prix_fonctionnement=?,nb_ameliorations=?,affluence_max=?,revenuJourPersonne=?,taux_incident=?,taille=? where id_restaurant=?");

			ps.setString(1, r.getNom());
			ps.setDouble(2, r.getPrixAcquisition());
			ps.setDouble(3, r.getPrixFonctionnement());
			ps.setInt(4, r.getNbAmelioration());
			ps.setInt(5, r.getAffluence());
			ps.setDouble(6, r.getrevenuJourPersonne());
			ps.setDouble(7, r.getTauxIncident());
			ps.setDouble(8, r.getTaille());
			ps.setInt(9, r.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return r;


	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from restaurant where id_restaurant=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}

}
