package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAOAttraction;

import metier.Attraction;

public class DAOAttractionJDBC implements IDAOAttraction {

	@Override
	public Attraction findById(Integer id) {
		Attraction a = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from attraction where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 a = new Attraction(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_amelioration"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"));	
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
				Attraction a = new Attraction(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_amelioration"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"));
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
			PreparedStatement ps = conn.prepareStatement("SELECT next_val FROM hibernate_sequences");
			ResultSet rs = ps.executeQuery();
			
			int id = rs.getInt("next_val")+1;
			a.setId(id);

			rs.close();
			ps.close();
			
			PreparedStatement ps2 = conn.prepareStatement("INSERT into attraction (id,nom,prix_acquisition,prix_fonctionnement,nb_amelioration,affluence_max,taux_incident,taille) VALUES (?,?,?,?,?,?,?,?)");

			ps2.setInt(1,id);
			ps2.setString(2,a.getNom());
			ps2.setDouble(3,a.getPrixAcquisition());
			ps2.setDouble(4,a.getPrixFonctionnement());
			ps2.setInt(5,a.getNbAmelioration());
			ps2.setInt(6,a.getAffluence());
			ps2.setDouble(7,a.getTauxIncident());
			ps2.setDouble(8,a.getTaille());
			ps2.executeUpdate();

			ps2.close();
			
			PreparedStatement ps3 = conn.prepareStatement("UPDATE hibernate_sequences set next_val=? where sequence_name like default");

			ps3.setInt(1,id);
			
			ps3.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return a;
	}
	
	
	

	@Override
	public Attraction update(Attraction a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE attraction set nom=?,prix_acquisition=?,prix_fonctionnement=?,nb_amelioration=?,affluence_max=?,taux_incident=?,taille=? where id=?");

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

			PreparedStatement ps = conn.prepareStatement("DELETE from attraction where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}



	@Override
	public List<Attraction> filterAttraction(String mot) {
		List<Attraction> attractions = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from attraction where nom=?");
			ps.setString(1,"%"+mot+"%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Attraction a = new Attraction(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_amelioration"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"));
				attractions.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return attractions;
	}
	
}
