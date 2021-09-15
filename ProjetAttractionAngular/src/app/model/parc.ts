import { Difficulte } from './Difficulte';
import { Joueur } from './joueur';
export class Parc {

  constructor(
    private _nomParc: string,
    private _taille: number,
    private _nbjour: number,
  	private _argent: number,
  	private _typeDifficulte: Difficulte,
    private _joueur: Joueur,
    private _id?: number
  ){}


	public get argent(): number {
		return this._argent;
	}

	public set argent(argent: number) {
		this._argent = argent;
	}

	public get typeDifficulte(): Difficulte {
		return this._typeDifficulte;
	}

	public set typeDifficulte(typeDifficulte: Difficulte) {
		this._typeDifficulte = typeDifficulte;
	}


	public get nomParc(): string {
		return this._nomParc;
	}


	public set nomParc(nomParc: string) {
		this._nomParc = nomParc;
	}


	public get taille(): number {
		return this._taille;
	}


	public set taille(taille: number) {
		this._taille = taille;
	}


	public get nbjour(): number {
		return this._nbjour;
	}


	public set nbjour(nbjour: number) {
		this._nbjour = nbjour;
	}


	public get joueur(): Joueur {
		return this._joueur;
	}


	public set joueur(joueur: Joueur) {
		this._joueur = joueur;
	}

	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}
}
