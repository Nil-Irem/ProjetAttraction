export class Construction {

  constructor(
	  protected _nom : string,
	  protected _taille: number,
	  protected _prixAcquisition: number,
	  protected _prixFonctionnement: number,
	  protected _nbAmelioration: number,
	  protected _tauxIncident: number,
	  protected _affluence: number,
	  protected _id?: number
  ){}



	public get nom() : string {
		return this._nom;
	}


	public set nom(nom: string) {
		this._nom = nom;
	}


	public get prixAcquisition() : number {
		return this._prixAcquisition;
	}


	public set prixAcquisition(prixAcquisition: number) {
		this._prixAcquisition = prixAcquisition;
	}


	public get prixFonctionnement(): number {
		return this._prixFonctionnement;
	}


	public set prixFonctionnement(prixFonctionnement: number) {
		this._prixFonctionnement = prixFonctionnement;
	}



	public get nbAmelioration() : number{
		return this._nbAmelioration;
	}


	public set nbAmelioration(nbAmelioration: number) {
		this._nbAmelioration = nbAmelioration;
	}


	public get tauxIncident() : number{
		return this._tauxIncident;
	}


	public set tauxIncident(tauxIncident: number) {
		this._tauxIncident = tauxIncident;
	}


	public get taille(): number {
		return this._taille;
	}


	public set taille(taille: number) {
		this._taille = taille;
	}


	public get affluence() : number{
		return this._affluence;
	}


	public set affluence(affluence: number) {
		this._affluence = affluence;
	}


	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}
}
