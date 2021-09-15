import { Element } from './element';

export class Employe extends Element{

  constructor(
    private _metier: string,
  	private _salaire: number,
    id?: number
  ){super(id);}


	public get metier(): string {
		return this._metier;
	}

	public set metier(metier: string) {
		this._metier = metier;
	}

	public get salaire(): number {
		return this._salaire;
	}

	public set salaire(salaire: number) {
		this._salaire = salaire;
	}
}
