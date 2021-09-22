import { Compte } from './compte';
export class Parc {

  constructor(
    private _nomParc: string,
  	private _typeDifficulte: string,
    private _taille?: number,
    private _nbjour?: number,
  	private _argent?: number,
    private _joueur?: Compte,
    private _id?: number
  ){}


	public get argent(): number|undefined {
		return this._argent;
	}

	public set argent(argent: number|undefined) {
		this._argent = argent;
	}

	public get typeDifficulte(): string {
		return this._typeDifficulte;
	}

	public set typeDifficulte(typeDifficulte: string) {
		this._typeDifficulte = typeDifficulte;
	}


	public get nomParc(): string {
		return this._nomParc;
	}


	public set nomParc(nomParc: string) {
		this._nomParc = nomParc;
	}


	public get taille(): number|undefined {
		return this._taille;
	}


	public set taille(taille: number|undefined) {
		this._taille = taille;
	}


	public get nbjour(): number|undefined {
		return this._nbjour;
	}


	public set nbjour(nbjour: number|undefined) {
		this._nbjour = nbjour;
	}


	public get joueur(): Compte|undefined {
		return this._joueur;
	}


	public set joueur(joueur: Compte|undefined) {
		this._joueur = joueur;
	}

	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}
}
