package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAO;

import metier.Attraction;

public class DAOAttraction implements IDAO<Attraction,Integer> {

	@Override
	public Attraction findById(Integer id) {
		Attraction a = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from attraction where id_attraction=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 a = new Attraction(rs.getInt("id_attraction"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_ameliorations"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"));	
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return a;
	}
	
	

	@Override
	public List<Attraction> findAll() {
		List<Attraction> attractions = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from attraction");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Attraction a = new Attraction(rs.getInt("id_attraction"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_ameliorations"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"));
				attractions.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return attractions;
	}
	
	
	@Override
	public Attraction insert(Attraction a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into attraction (nom,prix_acquisition,prix_fonctionnement,nb_ameliorations,affluence_max,taux_incident,taille) VALUES (?,?,?,?,?,?,?)");

			ps.setString(1, a.getNom());
			ps.setDouble(2, a.getPrixAcquisition());
			ps.setDouble(3, a.getPrixFonctionnement());
			ps.setInt(4, a.getNbAmelioration());
			ps.setInt(5, a.getAffluence());
			ps.setDouble(6, a.getTauxIncident());
			ps.setDouble(7, a.getTaille());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Y ajouter l'id ?
		return a;
	}
	
	
	

	@Override
	public Attraction update(Attraction a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE attraction set nom=?,prix_acquisition=?,prix_fonctionnement=?,nb_ameliorations=?,affluence_max=?,taux_incident=?,taille=? where id_attraction=?");

			ps.setString(1, a.getNom());
			ps.setDouble(2, a.getPrixAcquisition());
			ps.setDouble(3, a.getPrixFonctionnement());
			ps.setInt(4, a.getNbAmelioration());
			ps.setInt(5, a.getAffluence());
			ps.setDouble(6, a.getTauxIncident());
			ps.setDouble(7, a.getTaille());
			ps.setInt(8, a.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return a;
	}
	

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from attraction where id_attraction=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
}
