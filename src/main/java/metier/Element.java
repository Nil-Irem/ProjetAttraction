package metier;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Element {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected int id;

	public Element(int id) {
		this.id = id;
	}
	
	public Element() {}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
