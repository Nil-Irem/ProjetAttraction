package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="id_element")
	private Element element;
	private int niveauAmelioration;
	private int nbSameElement;
	@Column(name="type_element",length = 40)
	private String typeElement;
	@ManyToOne
	@JoinColumn(name="id_parc")
	private Parc parc;



	public Achat(int id, Element element,int nbSameElement,int niveauAmelioration,String typeElement,Parc parc) {
		this.id = id;
		this.element = element;
		this.niveauAmelioration = niveauAmelioration;
		this.nbSameElement=nbSameElement;
		this.typeElement=typeElement;
		this.parc=parc;
	}

	public Achat(Element element,int niveauAmelioration,String typeElement,Parc parc) {
		this.element = element;
		this.niveauAmelioration = niveauAmelioration;
		this.typeElement=typeElement;
		this.parc=parc;
	}

	public Achat(Element element,int nbSameElement,int niveauAmelioration,String typeElement,Parc parc) {
		this.element = element;
		this.niveauAmelioration = niveauAmelioration;
		this.nbSameElement=nbSameElement;
		this.typeElement=typeElement;
		this.parc=parc;
	}

	public Achat() {}

	
	

	public String getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}
	
	public int getNbSameElement() {
		return nbSameElement;
	}

	public void setNbSameElement(int nbSameElement) {
		this.nbSameElement = nbSameElement;
	}

	public int getNiveauAmelioration() {
		return niveauAmelioration;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Element getElement() {
		return element;
	}


	public void setElement(Element element) {
		this.element = element;
	}


	public Parc getParc() {
		return parc;
	}

	public void setParc(Parc parc) {
		this.parc = parc;
	}

	public void setNiveauAmelioration(int niveauAmelioration) {
		this.niveauAmelioration = niveauAmelioration;
	}

}
