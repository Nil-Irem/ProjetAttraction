export class Element {

  constructor(
  	private _typeElement: string,
	  private _nom?: string,
  	private _taille?: number,
	  private _prixAcquisition?: number,
  	private _prixFonctionnement?: number,
	  private _nbAmelioration?: number,
  	private _tauxIncident?: number,
	  private _affluence?: number,
    private _revenuJourPersonne?:number,
  	private _salaire?: number,
  	private _metier?: string,
    private _id?: number
  ){}

	public get typeElement() : string {
    return this._typeElement;
	}


	public set typeElement(typeElement : string) {
		this._typeElement = typeElement;
	}

	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}

	public get nom() : string|undefined {
		return this._nom;
	}


	public set nom(nom: string|undefined) {
		this._nom = nom;
	}


	public get prixAcquisition() : number|undefined {
		return this._prixAcquisition;
	}


	public set prixAcquisition(prixAcquisition: number|undefined) {
		this._prixAcquisition = prixAcquisition;
	}


	public get prixFonctionnement(): number|undefined {
		return this._prixFonctionnement;
	}


	public set prixFonctionnement(prixFonctionnement: number|undefined) {
		this._prixFonctionnement = prixFonctionnement;
	}



	public get nbAmelioration() : number|undefined{
		return this._nbAmelioration;
	}


	public set nbAmelioration(nbAmelioration: number|undefined) {
		this._nbAmelioration = nbAmelioration;
	}


	public get tauxIncident() : number|undefined{
		return this._tauxIncident;
	}


	public set tauxIncident(tauxIncident: number|undefined) {
		this._tauxIncident = tauxIncident;
	}


	public get taille(): number|undefined {
		return this._taille;
	}


	public set taille(taille: number|undefined) {
		this._taille = taille;
	}


	public get affluence() : number|undefined{
		return this._affluence;
	}


	public set affluence(affluence: number|undefined) {
		this._affluence = affluence;
	}


	public get revenuJourPersonne() : number|undefined{
		return this._revenuJourPersonne;
	}


	public set revenuJourPersonne(revenuJourPersonne: number|undefined) {
		this._revenuJourPersonne = revenuJourPersonne;
	}

	public get salaire(): number|undefined {
		return this._salaire;
	}

	public set salaire(salaire: number|undefined) {
		this._salaire = salaire;
	}

	public get metier(): string|undefined {
		return this._metier;
	}

	public set metier(metier: string|undefined) {
		this._metier = metier;
	}
}
