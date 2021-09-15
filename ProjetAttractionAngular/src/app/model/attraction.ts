import { Construction } from './construction';

export class Attraction extends Construction {

  constructor(
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
}
