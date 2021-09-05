package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.IDAO.IDAOEmploye;

import metier.Employe;

public class DAOEmployeJDBC implements IDAOEmploye {

	@Override
	public Employe findById(Integer id) {
		Employe emp = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from employe where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				 emp = new Employe(rs.getInt("id"),rs.getString("metier"),rs.getDouble("salaire"));
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return emp;
	}
	

	@Override
	public List<Employe> findAll() {
		List<Employe> employe = new ArrayList();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("SELECT * from employe");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				Employe emp = new Employe(rs.getInt("id"),rs.getString("metier"),rs.getDouble("salaire"));
				employe.add(emp);
			}
			rs.close();
			ps.close();
			conn.close();
		} 
		catch (Exception e) {e.printStackTrace();}
		return employe;
	}

	@Override
	public Employe insert(Employe emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);
			
			PreparedStatement ps = conn.prepareStatement("INSERT into employe (metier, salaire) VALUES (?,?)");
			ps.setString(1, emp.getMetier());
			ps.setDouble(2, emp.getSalaire());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return emp;
	}

	@Override
	public Employe update(Employe emp) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("UPDATE employe set metier=?,salaire=? where id=?");
			
			ps.setString(1, emp.getMetier());
			ps.setDouble(2, emp.getSalaire());
			ps.setInt(3, emp.getId());
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return emp;
	}

	@Override
	public void delete(Integer id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlBDD,loginBDD,passwordBDD);

			PreparedStatement ps = conn.prepareStatement("DELETE from employe where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			ps.close();
			conn.close();
		}
		catch(Exception e) {e.printStackTrace();}	
		
	}


	@Override
	public List<Employe> filterEmploye(String mot) {
		// TODO Auto-generated method stub
		return null;
	}

}
