import { Element } from './element';
import { Parc } from './parc';

export class Achat {

  	constructor(
	  private _parc : Parc,
	  private _element : Element,
	  private _typeElement : string,
	  private _niveauAmelioration : number,
	  private _nbSameElement : number,
    	private _id? : number
 	){}


	public get typeElement() : string {
		return this._typeElement;
	}

	public set typeElement(typeElement : string) {
		this._typeElement = typeElement;
	}

	public get nbSameElement() : number {
		return this._nbSameElement;
	}

	public set nbSameElement(nbSameElement : number) {
		this._nbSameElement = nbSameElement;
	}

	public get niveauAmelioration() : number {
		return this._niveauAmelioration;
	}

	public set niveauAmelioration(niveauAmelioration : number) {
		this._niveauAmelioration = niveauAmelioration;
	}

	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}


	public get element() : Element {
		return this._element;
	}


	public set element(element : Element) {
		this._element = element;
	}


	public get parc() : Parc {
		return this._parc;
	}


	public set parc(parc : Parc) {
		this._parc = parc;
	}

}
