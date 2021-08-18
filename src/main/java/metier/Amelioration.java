package metier;

import javax.persistence.*;

@Entity
public class Amelioration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
	@JoinColumn(name="id_parc")
	private Parc parc;
	@OneToOne
	@JoinColumn(name="id_construction")
	private Construction construction;
	private int niveauAmelioration;
	
	

	
	public Amelioration(Parc parc, Construction construction, int niveauAmelioration) {
		this.parc = parc;
		this.construction = construction;
		this.niveauAmelioration = niveauAmelioration;
	}


	public Amelioration(int id, Parc parc, Construction construction, int niveauAmelioration) {
		this.id = id;
		this.parc = parc;
		this.construction = construction;
		this.niveauAmelioration = niveauAmelioration;
	}
	
	public Amelioration() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Parc getParc() {
		return parc;
	}


	public void setParc(Parc parc) {
		this.parc = parc;
	}


	public Construction getConstruction() {
		return construction;
	}


	public void setConstruction(Construction construction) {
		this.construction = construction;
	}


	public int getNiveauAmelioration() {
		return niveauAmelioration;
	}


	public void setNiveauAmelioration(int niveauAmelioration) {
		this.niveauAmelioration = niveauAmelioration;
	}


	@Override
	public String toString() {
		return "Amelioration [id=" + id + ", parc=" + parc + ", construction=" + construction + ", niveauAmelioration="
				+ niveauAmelioration + "]";
	}
	
	

}
