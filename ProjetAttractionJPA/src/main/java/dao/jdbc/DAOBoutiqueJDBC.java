package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAOBoutique;
import metier.Attraction;
import metier.Boutique;

public class DAOBoutiqueJDBC implements IDAOBoutique {

	@Override
	public Boutique findById(Integer id) {
		Boutique b = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from boutique where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 b = new Boutique(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_amelioration"),
						 rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"),rs.getDouble("revenuJourPersonne"));
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return b;
	}
	


	@Override
	public List<Boutique> findAll() {
		List<Boutique> boutiques = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from boutique");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Boutique b = new Boutique(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),
				rs.getInt("nb_amelioration"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"),rs.getDouble("revenuJourPersonne"));
				boutiques.add(b);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return boutiques;
	}
	

	
	@Override
	public Boutique insert(Boutique b) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			PreparedStatement ps = conn.prepareStatement("SELECT next_val FROM hibernate_sequences");
			ResultSet rs = ps.executeQuery();
			
			int id = rs.getInt("next_val")+1;
			b.setId(id);

			rs.close();
			ps.close();
			
			PreparedStatement ps2 = conn.prepareStatement("INSERT into boutique (nom,prix_acquisition,prix_fonctionnement,nb_amelioration,affluence_max,taux_incident,taille,revenuJourPersonne) VALUES (?,?,?,?,?,?,?,?)");

			ps2.setString(1, b.getNom());
			ps2.setDouble(2, b.getPrixAcquisition());
			ps2.setDouble(3, b.getPrixFonctionnement());
			ps2.setInt(4, b.getNbAmelioration());
			ps2.setInt(5, b.getAffluence());
			ps2.setDouble(6, b.getTauxIncident());
			ps2.setDouble(7, b.getTaille());
			ps2.setDouble(8, b.getrevenuJourPersonne());
			
			ps2.executeUpdate();
			ps2.close();
			
			PreparedStatement ps3 = conn.prepareStatement("UPDATE hibernate_sequences set next_val=? where sequence_name like default");

			ps3.setInt(1,id);
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return b;
	}
	
	

	@Override
	public Boutique update(Boutique b) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE boutique set nom=?,prix_acquisition=?,prix_fonctionnement=?,nb_amelioration=?,affluence_max=?,taux_incident=?,taille=?,revenuJourPersonne=? where id=?");

			ps.setString(1, b.getNom());
			ps.setDouble(2, b.getPrixAcquisition());
			ps.setDouble(3, b.getPrixFonctionnement());
			ps.setInt(4, b.getNbAmelioration());
			ps.setInt(5, b.getAffluence());
			ps.setDouble(6, b.getTauxIncident());
			ps.setDouble(7, b.getTaille());
			ps.setDouble(8, b.getrevenuJourPersonne());
			ps.setInt(9, b.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return b;
	}
	
	

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from boutique where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}


	
	@Override
	public List<Boutique> filterBoutique(String mot) {
		List<Boutique> boutiques = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from boutique where nom=?");
			ps.setString(1,"%"+mot+"%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Boutique b = new Boutique(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_amelioration"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"),rs.getDouble("revenuJourPersonne"));
				boutiques.add(b);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return boutiques;
	}

}
