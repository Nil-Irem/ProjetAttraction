package ParcAttractionBoot.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Element {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@JsonView(JsonViews.Common.class)
	protected Integer id;
	private transient String typeElement;

	public Element(Integer id) {
		this.id = id;
	}
	
	public Element() {}
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeElement() {
		return typeElement;
	}

	public void setTypeElement(String typeElement) {
		this.typeElement = typeElement;
	}
	
}
