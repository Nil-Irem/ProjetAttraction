export class Element {

  constructor(
	// private _typeElement: string
	// protected _nom?: string,
	// protected _taille?: number,
	// protected _prixAcquisition?: number,
	// protected _prixFonctionnement?: number,
	// protected _nbAmelioration?: number,
	// protected _tauxIncident?: number,
	// protected _affluence?: number,
    // private _metier?: string,
  	// private _salaire?: number,
    protected _id?: number
  ){}


	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}

	// public get nom() : string|undefined {
	// 	return this._nom;
	// }


	// public set nom(nom: string|undefined) {
	// 	this._nom = nom;
	// }


	// public get prixAcquisition() : number|undefined {
	// 	return this._prixAcquisition;
	// }


	// public set prixAcquisition(prixAcquisition: number|undefined) {
	// 	this._prixAcquisition = prixAcquisition;
	// }


	// public get prixFonctionnement(): number|undefined {
	// 	return this._prixFonctionnement;
	// }


	// public set prixFonctionnement(prixFonctionnement: number|undefined) {
	// 	this._prixFonctionnement = prixFonctionnement;
	// }



	// public get nbAmelioration() : number|undefined{
	// 	return this._nbAmelioration;
	// }


	// public set nbAmelioration(nbAmelioration: number|undefined) {
	// 	this._nbAmelioration = nbAmelioration;
	// }


	// public get tauxIncident() : number{
	// 	return this._tauxIncident;
	// }


	// public set tauxIncident(tauxIncident: number|undefined) {
	// 	this._tauxIncident = tauxIncident;
	// }


	// public get taille(): number|undefined {
	// 	return this._taille;
	// }


	// public set taille(taille: number|undefined) {
	// 	this._taille = taille;
	// }


	// public get affluence() : number|undefined{
	// 	return this._affluence;
	// }


	// public set affluence(affluence: number|undefined) {
	// 	this._affluence = affluence;
	// }


	// public get metier(): string|undefined {
	// 	return this._metier;
	// }

	// public set metier(metier: string|undefined) {
	// 	this._metier = metier;
	// }

	// public get salaire(): number|undefined {
	// 	return this._salaire;
	// }

	// public set salaire(salaire: number|undefined) {
	// 	this._salaire = salaire;
	// }
}
