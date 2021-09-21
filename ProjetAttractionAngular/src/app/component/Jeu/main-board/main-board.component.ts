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
    }
  public deleteAchat(id: number,idj: number,ida: number){}

  public retour(){
    localStorage.removeItem("parcChosen");
    this.router.navigate(['jeu/choixparc']);

  }

  public finJourneeA() {
    /* Afficher dans le footer les résultats ?
    //meteo,nbVisiteurs, dépenses, recette
    //return this.
*/

  }
}
