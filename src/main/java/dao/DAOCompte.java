package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import metier.Admin;
import metier.Joueur;
import metier.Compte;

public class DAOCompte implements IDAO<Compte,Integer> {

	@Override
	public Compte findById(Integer id) {
		return null;
	}
	


	@Override
	public List<Compte> findAll() {
		return null;
	}


	public Compte insert (Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into compte (login,password,type_compte) VALUES (?,?,?)");

			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, "joueur");
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		
		//Y ajouter l'id ?
		return c;
	}



	@Override
	public Compte update(Compte c) {
		return null;
	}

	@Override
	public void delete(Integer id) {
		
	}
	
	
	
	
	public static Compte seConnecter(String login,String password) 
	{
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
 
			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(rs.getString("type_compte").equalsIgnoreCase("admin")) 
				{
					 c = new Admin(rs.getString("login"),rs.getString("password"));
				}
				else if (rs.getString("type_compte").equalsIgnoreCase("joueur")) 
				{
					 c = new Joueur(rs.getString("login"),rs.getString("password"));
				}
			}
			
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return c;
	}
	
	
	
	public static boolean findByLogin(String login) 
	{
		boolean b=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login like ?");
			ps.setString(1, login);

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
