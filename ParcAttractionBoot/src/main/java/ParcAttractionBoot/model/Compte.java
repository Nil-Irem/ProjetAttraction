package ParcAttractionBoot.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte",columnDefinition = "ENUM('admin', 'joueur')")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compte")
	@JsonView(JsonViews.Common.class)
	protected Integer id;
	@JsonView(JsonViews.Common.class)
	protected String login;
	@JsonView(JsonViews.Common.class)
	protected String password;
	@Column(name="enable")
	@JsonView(JsonViews.Common.class)
	private boolean enable;
	
	@JsonView(JsonViews.Common.class)
	private transient boolean isJoueur;
	
	
	public Compte(Integer id,String login, String password) {
		this.login = login;
		this.password = password;
		this.id = id;
	}
	
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	

	public Compte(String login, String password,boolean isJoueur) {
		this.login = login;
		this.password = password;
		this.isJoueur = isJoueur;
	}
	
	public Compte(String login) {
		this.login = login;
		
	}
	
	
	public Compte() {
	}


	
	
	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
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


	public boolean isJoueur() {
		return isJoueur;
	}


	public void setIsJoueur(boolean isJoueur) {
		this.isJoueur = isJoueur;
	}


	@Override
	public String toString() {
		return "Compte [login=" + login + ", password=" + password + "]";
	}
	

	

}
