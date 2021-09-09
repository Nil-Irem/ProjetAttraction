package SoprAjc.ParcAttractionBoot.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Element {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected Integer id;

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
}
