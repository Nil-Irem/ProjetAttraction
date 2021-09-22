import { Element } from 'src/app/model/element';
import { Component, OnInit } from '@angular/core';
import { Parc } from 'src/app/model/parc';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';
import { ActivatedRoute, Router, RouterLink} from '@angular/router';
import { Achat } from 'src/app/model/achat';

@Component({
  selector: 'app-main-board',
  templateUrl: './main-board.component.html',
  styleUrls: ['./main-board.component.css']
})
export class MainBoardComponent implements OnInit {


  parcStorage = localStorage.getItem("parcChosen");
  boutiques : Element[]=[];
  restaurants : Element[]=[];
  employes : Element[]=[];
  achats : Achat[]=[];



  constructor(
    private gestionAchatService: GestionAchatService,
    private router: Router) {

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

  public finJourneeA() {
    /*
    //Afficher dans le footer les résultats :
    //meteo,nbVisiteurs, dépenses, recette
    this.gestionJourneeService.finJourneeS(JSON.parse(this.parcStorage)).subscribe(
(res) =>
  {this.parc.set(parc.nbjour, parc.nbjour+1);}
  (error) => console.log(error)
    );
    return this.gestionJourneeService.finJourneeS(JSON.parse(this.parcStorage)).subscribe();

*/

  }
}
