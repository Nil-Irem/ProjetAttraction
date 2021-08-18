package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO.IDAO;

import metier.Attraction;
import metier.Boutique;
import metier.Commodite;
import metier.Employe;
import metier.Parc;
import metier.Restaurant;


public abstract class DAOLien implements IDAO<Parc,Integer> {
	
	static DAOAttraction DaoA = new DAOAttraction();
	static DAOBoutique DaoB = new DAOBoutique();
	static DAOCommodite DaoC = new DAOCommodite();
	static DAOCompte DaoCpt = new DAOCompte();
	static DAOEmploye DaoE = new DAOEmploye();
	static DAORestaurant DaoR = new DAORestaurant();	
	
	
	public static List<Attraction> findAllAttractionById(Integer id_parc) {
		List<Attraction> attractions = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from lien_parc_attraction where id_parc=?");
			ps.setInt(1, id_parc);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Attraction a = DaoA.findById(rs.getInt("id_attraction"));
				//a.setNiveauAmelioration(rs.getInt("amelioration_attraction")); // Marche plus avec Achat
				attractions.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return attractions;
	}
	
	
	
	public static List<Boutique> findAllBoutiqueById(Integer id_parc) {
		List<Boutique> boutiques = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from lien_parc_boutique where id_parc=?");
			ps.setInt(1, id_parc);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Boutique b = DaoB.findById(rs.getInt("id_boutique"));
				//b.setNiveauAmelioration(rs.getInt("amelioration_boutique"));
				boutiques.add(b);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return boutiques;
	}
	
	
	
	public static List<Commodite> findAllCommoditeById(Integer id_parc) {
		List<Commodite> commodites = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from lien_parc_commodite where id_parc=?");
			ps.setInt(1, id_parc);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				commodites.add(DaoC.findById(rs.getInt("id_commodite")));
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return commodites;
	}
	
	
	
	public static List<Employe> findAllEmployeById(Integer id_parc) {
		List<Employe> employes = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from lien_parc_employe where id_parc=?");
			ps.setInt(1, id_parc);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				employes.add(DaoE.findById(rs.getInt("id_employe")));
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return employes;
	}
	
	
	
	public static List<Restaurant> findAllRestaurantById(Integer id_parc) {
		List<Restaurant> restaurants = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from lien_parc_restaurant where id_parc=?");
			ps.setInt(1, id_parc);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Restaurant r = DaoR.findById(rs.getInt("id_restaurant"));
				//r.setNiveauAmelioration(rs.getInt("amelioration_restaurant"));// Marche plus avec Achat
				restaurants.add(r);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return restaurants;
	}
	
	
	
	
	
	public static void insertAttraction(int id_parc,int id_attraction,int amelioration_attraction) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into lien_parc_attraction (id_parc,id_attraction,amelioration_attraction) VALUES (?,?,?)");

			ps.setInt(1, id_parc);
			ps.setInt(2, id_attraction);
			ps.setInt(3, amelioration_attraction);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void insertBoutique(int id_parc,int id_boutique,int amelioration_boutique) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into lien_parc_boutique (id_parc,id_boutique,amelioration_boutique) VALUES (?,?,?)");

			ps.setInt(1, id_parc);
			ps.setInt(2, id_boutique);
			ps.setInt(3, amelioration_boutique);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void insertCommodite(int id_parc,int id_commodite) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into lien_parc_commodite (id_parc,id_commodite) VALUES (?,?)");

			ps.setInt(1, id_parc);
			ps.setInt(2, id_commodite);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void insertEmploye(int id_parc,int id_employe) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into lien_parc_employe (id_parc,id_employe) VALUES (?,?)");

			ps.setInt(1, id_parc);
			ps.setInt(2, id_employe);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void insertRestaurant(int id_parc,int id_restaurant,int amelioration_restaurant) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into lien_parc_restaurant (id_parc,id_restaurant,amelioration_restaurant) VALUES (?,?,?)");

			ps.setInt(1, id_parc);
			ps.setInt(2, id_restaurant);
			ps.setInt(3, amelioration_restaurant);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void deleteAttraction(int id_parc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("delete from lien_parc_attraction where id_parc=?");

			ps.setInt(1, id_parc);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void deleteBoutique(int id_parc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("delete from lien_parc_boutique where id_parc=?");
			
			ps.setInt(1, id_parc);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void deleteCommodite(int id_parc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("delete from lien_parc_commodite where id_parc=?");
			
			ps.setInt(1, id_parc);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void deleteEmploye(int id_parc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("delete from lien_parc_employe where id_parc=?");
			
			ps.setInt(1, id_parc);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	
	
	
	public static void deleteRestaurant(int id_parc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("delete from lien_parc_restaurant where id_parc=?");
			
			ps.setInt(1, id_parc);
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
		
	
	
	
}