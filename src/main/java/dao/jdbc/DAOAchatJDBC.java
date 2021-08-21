package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAO.IDAOAchat;
import metier.Achat;
import metier.Admin;
import metier.Attraction;
import metier.Boutique;
import metier.Commodite;
import metier.Element;
import metier.Employe;
import metier.Joueur;
import metier.Parc;
import metier.Restaurant;
import util.Context;

public class DAOAchatJDBC implements IDAOAchat{

	@Override
	public Achat findById(Integer id) {
		Achat a = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from achat where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Element e = null;
				if (rs.getString("type_element").equals("attraction"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("boutique"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("commodite"))
				{
					e = Context.getInstance().getDaoC().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("employe"))
				{
					e = Context.getInstance().getDaoE().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("restaurant"))
				{
					e = Context.getInstance().getDaoR().findById(rs.getInt("id_element"));
				}
				
				Parc p = Context.getInstance().getDaoP().findById(rs.getInt("id_parc"));
				a = new Achat(rs.getInt("id"),e,rs.getInt("nbSameElement"),rs.getInt("niveauAmelioration"),rs.getString("type_element"),p);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return a;
	}

	
	
	@Override
	public List<Achat> findAll() {
		List<Achat> achats = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from achat");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Element e = null;
				if (rs.getString("type_element").equals("attraction"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("boutique"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("commodite"))
				{
					e = Context.getInstance().getDaoC().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("employe"))
				{
					e = Context.getInstance().getDaoE().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("restaurant"))
				{
					e = Context.getInstance().getDaoR().findById(rs.getInt("id_element"));
				}
				
				Parc p = Context.getInstance().getDaoP().findById(rs.getInt("id_parc"));
				Achat a = new Achat(rs.getInt("id"),e,rs.getInt("nbSameElement"),rs.getInt("niveauAmelioration"),rs.getString("type_element"),p);
				achats.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return achats;
	}

	
	
	@Override
	public Achat insert(Achat a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into achat (nbSameElement,niveauAmelioration,type_element,id_element,id_parc) VALUES (?,?,?,?,?)");

			ps.setInt(1, a.getNbSameElement());
			ps.setInt(2, a.getNiveauAmelioration());
			ps.setInt(4, a.getElement().getId());
			ps.setInt(5, a.getParc().getId());
			
			if (a.getElement() instanceof Attraction)
			{
				ps.setString(3, "attraction");
			}
			else if (a.getElement() instanceof Boutique)
			{
				ps.setString(3, "boutique");
			}
			else if (a.getElement() instanceof Commodite)
			{
				ps.setString(3, "commodite");
			}
			else if (a.getElement() instanceof Employe)
			{
				ps.setString(3, "employe");
			}
			else if (a.getElement() instanceof Restaurant)
			{
				ps.setString(3, "restaurant");
			}
			
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT Last_insert_id()");
			ResultSet rs = ps2.executeQuery();

			while(rs.next()) 
			{
				a.setId(rs.getInt(1));
			}
			ps2.close();
			rs.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return a;
	}


	
	
	@Override
	public Achat update(Achat a) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("UPDATE achat set nbSameElement=?,niveauAmelioration=?,type_element=?,id_element=?,id_parc=? where id=?");

			ps.setInt(1, a.getNbSameElement());
			ps.setInt(2, a.getNiveauAmelioration());
			ps.setInt(4, a.getElement().getId());
			ps.setInt(5, a.getParc().getId());
			ps.setInt(6, a.getId());
			
			if (a.getElement() instanceof Attraction)
			{
				ps.setString(3, "attraction");
			}
			else if (a.getElement() instanceof Boutique)
			{
				ps.setString(3, "boutique");
			}
			else if (a.getElement() instanceof Commodite)
			{
				ps.setString(3, "commodite");
			}
			else if (a.getElement() instanceof Employe)
			{
				ps.setString(3, "employe");
			}
			else if (a.getElement() instanceof Restaurant)
			{
				ps.setString(3, "restaurant");
			}
			
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

			PreparedStatement ps = conn.prepareStatement("DELETE from achat where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}	
		
	}

	
	
	@Override
	public List<Achat> findByTypeAndParc(String type, Parc parc) {
		List<Achat> achats = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from achat where type_element like ? and id_parc=?");
			ps.setString(1,type);
			ps.setInt(2,parc.getId());
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Element e = null;
				if (type.equals("attraction"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (type.equals("boutique"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (type.equals("commodite"))
				{
					e = Context.getInstance().getDaoC().findById(rs.getInt("id_element"));
				}
				else if (type.equals("employe"))
				{
					e = Context.getInstance().getDaoE().findById(rs.getInt("id_element"));
				}
				else if (type.equals("restaurant"))
				{
					e = Context.getInstance().getDaoR().findById(rs.getInt("id_element"));
				}
				
				Parc p = Context.getInstance().getDaoP().findById(rs.getInt("id_parc"));
				Achat a = new Achat(rs.getInt("id"),e,rs.getInt("nbSameElement"),rs.getInt("niveauAmelioration"),rs.getString("type_element"),p);
				achats.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return achats;
	}

	
	
	@Override
	public int Nvamelioration(Parc parc, Element element) {
		int amelioration=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from achat where id_element=? and id_parc=?");
			ps.setInt(1,element.getId());
			ps.setInt(2,parc.getId());
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				amelioration=rs.getInt("niveauAmelioration");
			}
			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e) {e.printStackTrace();}
		return amelioration;
	}

	
	
	@Override
	public List<Achat> findByParc(Parc p) {
		List<Achat> achats = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from achat where id_parc=?");
			ps.setInt(1,p.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				Element e = null;
				if (rs.getString("type_element").equals("attraction"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("boutique"))
				{
					e = Context.getInstance().getDaoA().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("commodite"))
				{
					e = Context.getInstance().getDaoC().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("employe"))
				{
					e = Context.getInstance().getDaoE().findById(rs.getInt("id_element"));
				}
				else if (rs.getString("type_element").equals("restaurant"))
				{
					e = Context.getInstance().getDaoR().findById(rs.getInt("id_element"));
				}
				
				Achat a = new Achat(rs.getInt("id"),e,rs.getInt("nbSameElement"),rs.getInt("niveauAmelioration"),rs.getString("type_element"),p);
				achats.add(a);
			}
			rs.close();
			ps.close();
			conn.close();
		}
		catch (Exception e){e.getStackTrace();}
		return  achats;	
	}

}
