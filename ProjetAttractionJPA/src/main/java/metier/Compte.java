package metier;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte",columnDefinition = "ENUM('admin', 'joueur')")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compte")
	protected int id;
	protected String login;
	protected String password;
	
	
	public Compte(int id,String login, String password) {
		this.login = login;
		this.password = password;
		this.id = id;
	}
	
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Compte(String login) {
		this.login = login;
		
	}
	
	
	public Compte() {
	}


	public int getId() {
		return id;
	}
<<<<<<< Updated upstream:ProjetAttractionJPA/src/main/java/metier/Compte.java


	public void setId(int id) {
		this.id = id;
	}


=======
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	
>>>>>>> Stashed changes:src/main/java/metier/Compte.java
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Compte [login=" + login + ", password=" + password + "]";
	}
	

	

}
