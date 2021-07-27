package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Boutique;

public class DAOBoutique implements IDAO<Boutique,Integer> {

	@Override
	public Boutique findById(Integer id) {
		Boutique b = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from boutique where id_boutique=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 b = new Boutique(rs.getInt("id_boutique"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),rs.getInt("nb_ameliorations"),
				 	rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"),rs.getDouble("rev_jr_pers"));	
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
				Boutique b = new Boutique(rs.getInt("id_boutique"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("prix_fonctionnement"),
				rs.getInt("nb_ameliorations"),rs.getDouble("taux_incident"),rs.getDouble("taille"),rs.getInt("affluence_max"),rs.getDouble("rev_jr_pers"));
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
			
			PreparedStatement ps = conn.prepareStatement("INSERT into boutique (nom,prix_acquisition,prix_fonctionnement,nb_ameliorations,affluence_max,taux_incident,taille,rev_jr_pers) VALUES (?,?,?,?,?,?,?,?)");

			ps.setString(1, b.getNom());
			ps.setDouble(2, b.getPrixAcquisition());
			ps.setDouble(3, b.getPrixFonctionnement());
			ps.setInt(4, b.getNbAmelioration());
			ps.setInt(5, b.getAffluence());
			ps.setDouble(6, b.getTauxIncident());
			ps.setDouble(7, b.getTaille());
			ps.setDouble(8, b.getrevenuJourPersonne());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Y ajouter l'id ?
		return b;
	}
	
	

	@Override
	public Boutique update(Boutique b) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE boutique set nom=?,prix_acquisition=?,prix_fonctionnement=?,nb_ameliorations=?,affluence_max=?,taux_incident=?,taille=?,rev_jr_pers=? where id_boutique=?");

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

			PreparedStatement ps = conn.prepareStatement("DELETE from boutique where id_boutique=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}

}
