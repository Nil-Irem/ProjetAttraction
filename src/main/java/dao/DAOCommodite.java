package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Commodite;

public class DAOCommodite implements IDAO<Commodite,Integer> {


	@Override
	public Commodite findById(Integer id) {
		Commodite c = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from commodite where id_commodite=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 c = new Commodite(rs.getInt("id_commodite"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("taille"));	
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return c;
	}
	
	

	@Override
	public List<Commodite> findAll() {
		List<Commodite> commodites = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from commodite");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Commodite c = new Commodite(rs.getInt("id_commodite"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("taille"));
				commodites.add(c);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return commodites;
	}
	


	@Override
	public Commodite insert(Commodite c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into commodite (nom,prix_acquisition,taille) VALUES (?,?,?)");

			ps.setString(1, c.getNom());
			ps.setDouble(2, c.getPrixAcquisition());
			ps.setDouble(3, c.getTaille());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Y ajouter l'id ?
		return c;
	}
	
	
	
	@Override
	public Commodite update(Commodite c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE commodite set nom=?,prix_acquisition=?,taille=? where id_commodite=?");

			ps.setString(1, c.getNom());
			ps.setDouble(2, c.getPrixAcquisition());
			ps.setDouble(3, c.getTaille());
			ps.setInt(4, c.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return c;
	}
	
	


	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from commodite where id_commodite=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}

}
