import { Construction } from './construction';

export class Boutique extends Construction {

  constructor(
    private _revenuJourPersonne : number,
	  nom : string,
	  taille: number,
	  prixAcquisition: number,
	  prixFonctionnement: number,
	  nbAmelioration: number,
	  tauxIncident: number,
	  affluence: number,
	  id?: number
  )
  {super(nom,taille,prixAcquisition,prixFonctionnement,
    nbAmelioration,tauxIncident,affluence,id);}


	public get revenuJourPersonne() :number {
		return this._revenuJourPersonne;
	}

	public set revenuJourPersonne(revenuJourPersonne: number) {
		this._revenuJourPersonne = revenuJourPersonne;
	}
}
