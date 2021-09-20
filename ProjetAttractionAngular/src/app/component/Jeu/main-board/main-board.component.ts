import { Component, OnInit } from '@angular/core';
import { Parc } from 'src/app/model/parc';
import { Boutique } from 'src/app/model/boutique';
import { Restaurant } from 'src/app/model/restaurant';
import { Employe } from 'src/app/model/employe';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Achat } from 'src/app/model/achat';

@Component({
  selector: 'app-main-board',
  templateUrl: './main-board.component.html',
  styleUrls: ['./main-board.component.css']
})
export class MainBoardComponent implements OnInit {


  parcStorage = localStorage.getItem("parcChosen");
  boutiques : Boutique[]=[];
  restaurants : Restaurant[]=[];
  employes : Employe[]=[];
  achats : Achat[]=[];



  constructor(private gestionAchatService: GestionAchatService,
    private ar:ActivatedRoute,
    private router: Router,
    private route:Router ) {


    if(this.parcStorage){

    this.gestionAchatService.getByParc(JSON.parse(this.parcStorage)).subscribe(
      (res) => this.achats = res,
      (error) => console.log(error)
    );
   }

   }

  ngOnInit(): void {}

  public getParc():Parc{
    if(this.parcStorage){
      return JSON.parse(this.parcStorage);
    }
    return new Parc("probleme","probleme");
  }

  public ameliorer(id: number){
    // Ça se fera dans possession plutôt, je pense
    //Number nvAm =
    // Achat achat={

    //   id:id;
    // }


    // update(achat);
    /*Element element = new Attraction();
		Parc parc = (Parc) session.getAttribute("parc");
		double prix=0;
		switch(type)
		{
			case "attraction" : element=daoAt.findById(id).get();prix=prixAmeliorationAttraction;break;
			case "boutique" : element=daoB.findById(id).get();prix=prixAmeliorationBoutique;break;
			case "restaurant" : element=daoR.findById(id).get();prix=prixAmeliorationRestaurant;break;
			default : return new ModelAndView("redirect:/possessions");
		}
		Achat achat = daoA.findByElementAndParc(element,parc).get();
		achat.setNiveauAmelioration(achat.getNiveauAmelioration()+1);
		daoA.save(achat);

		parc.setArgent(parc.getArgent()-prix);
		session.setAttribute("parc", parc);
		return new ModelAndView("redirect:/possessions");*/


    }
  public deleteAchat(id: number,idj: number,ida: number){}

  public retour(){
    localStorage.removeItem("parcChosen");
    this.router.navigate(['jeu/choixparc']);

  }

  public finJournee(){
    /* Afficher dans le footer les résultats ?*/
    //meteo,nbVisiteurs, dépenses, recette



  }

}
