import { Element } from './element';

export class Commodite extends Element{

  constructor(
    private _nom: string,
  	private _prixAcquisition: number,
  	private _taille: number,
    id?: number
  ){super();}


	public get nom(): string {
		return this._nom;
	}

	public set nom(nom: string) {
		this._nom = nom;
	}

	public get prixAcquisition(): number {
		return this._prixAcquisition;
	}

	public set prixAcquisition(prixAcquisition: number) {
		this._prixAcquisition = prixAcquisition;
	}

	public get taille(): number {
		return this._taille;
	}

	public set taille(taille: number) {
		this._taille = taille;
	}
}
