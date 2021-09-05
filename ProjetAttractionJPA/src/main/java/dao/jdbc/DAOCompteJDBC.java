package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAOCompte;

import metier.Admin;
import metier.Commodite;
import metier.Compte;
import metier.Joueur;
import util.Context;

public class DAOCompteJDBC implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where id_compte=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if (rs.getString("type_compte").equals("joueur"))
				{
					c = new Joueur(id,rs.getString("login"),rs.getString("password"));
				}
				else if (rs.getString("type_compte").equals("admin"))
				{
					 c = new Admin(id,rs.getString("login"),rs.getString("password"));
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return c;
	}
	


	@Override
	public List<Compte> findAll() {
		List<Compte> comptes = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if (rs.getString("type_compte").equals("joueur"))
				{
					Compte c = new Joueur(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
					comptes.add(c);
				}
				else if (rs.getString("type_compte").equals("admin"))
				{
					Compte c = new Admin(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
					comptes.add(c);
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return comptes;
	}
	


	@Override
	public Compte insert (Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into compte (login,password,type_compte) VALUES (?,?,?)");

			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			if (c instanceof Joueur)
			{
				ps.setString(3, "joueur");
			}
			else if (c instanceof Admin)
			{
				ps.setString(3, "admin");
			}
			ps.executeUpdate();
			
			
			if (c instanceof Joueur) {
				Joueur j = (Joueur) c;
				PreparedStatement ps2 = conn.prepareStatement("SELECT Last_insert_id()");
				ResultSet rs = ps2.executeQuery();

				while(rs.next()) 
				{
					j.setId(rs.getInt(1));
				}
				ps2.close();
				rs.close();
				return j;
			}
			else if (c instanceof Admin) {
				Admin a = (Admin) c;
				PreparedStatement ps2 = conn.prepareStatement("SELECT Last_insert_id()");
				ResultSet rs = ps2.executeQuery();

				while(rs.next()) 
				{
					a.setId(rs.getInt(1));
				}
				ps2.close();
				rs.close();
				return a;
			}
			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return c;
	}



	@Override
	public Compte update(Compte c) {
		return null;
	}

	
	
	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from compte where id_compte=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT id from parc where id_joueur=?");
			ps2.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				Context.getInstance().getDaoP().delete(rs.getInt("id"));;
			}
			rs.close();
			ps2.close();	
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
	
	

	@Override
	public Compte seConnecter(String login,String password) 
	{
		Compte c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
 
			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login like ? and password like ?");
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				if(rs.getString("type_compte").equals("admin")) 
				{
					 c = new Admin(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
				}
				else if (rs.getString("type_compte").equals("joueur")) 
				{
					 c = new Joueur(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
				}
			}
			rs.close();
			ps.close();			
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return c;
	}

	
	
	@Override
	public boolean checkSameLogin(String login) 
	{
		boolean b=true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login like ?");
			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {b= true;}
			else {b=false;}
			
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return b;
	}



	@Override
	public List<Compte> filterCompte(String mot) {
		List<Compte> comptes = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from compte where login like ?");
			ps.setString(1, "%"+mot+"%");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				if (rs.getString("type_compte").equals("joueur"))
				{
					Compte c = new Joueur(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
					comptes.add(c);
				}
				else if (rs.getString("type_compte").equals("admin"))
				{
					Compte c = new Admin(rs.getInt("id_compte"),rs.getString("login"),rs.getString("password"));
					comptes.add(c);
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return comptes;
	}
}
