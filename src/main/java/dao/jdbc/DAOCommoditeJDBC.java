package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAOCommodite;
import metier.Attraction;
import metier.Commodite;

public class DAOCommoditeJDBC implements IDAOCommodite {

	@Override
	public Commodite findById(Integer id) {
		Commodite c = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from commodite where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 c = new Commodite(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("taille"));	
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
				Commodite c = new Commodite(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("taille"));
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
			PreparedStatement ps = conn.prepareStatement("SELECT next_val FROM hibernate_sequences");
			ResultSet rs = ps.executeQuery();
			
			int id = rs.getInt("next_val")+1;
			c.setId(id);

			rs.close();
			ps.close();
			
			PreparedStatement ps2 = conn.prepareStatement("INSERT into commodite (nom,prix_acquisition,taille) VALUES (?,?,?)");

			ps2.setString(1, c.getNom());
			ps2.setDouble(2, c.getPrixAcquisition());
			ps2.setDouble(3, c.getTaille());
			ps2.executeUpdate();

			ps2.close();
			
			PreparedStatement ps3 = conn.prepareStatement("UPDATE hibernate_sequences set next_val=? where sequence_name like default");

			ps3.setInt(1,id);
			
			ps3.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		return c;
	}
	
	
	
	@Override
	public Commodite update(Commodite c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE commodite set nom=?,prix_acquisition=?,taille=? where id=?");

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

			PreparedStatement ps = conn.prepareStatement("DELETE from commodite where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}



	@Override
	public List<Commodite> filterCommodite(String mot) {
		List<Commodite> commodites = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from commodite where nom=?");
			ps.setString(1,"%"+mot+"%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Commodite a = new Commodite(rs.getInt("id"),rs.getString("nom"),rs.getDouble("prix_acquisition"),rs.getDouble("taille"));
				commodites.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return commodites;
	}

}
