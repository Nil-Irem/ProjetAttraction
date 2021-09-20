
import { GestionElementService } from './../../../service/GestionJeu/gestion-element.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';
import { Parc } from 'src/app/model/parc';

@Component({
  selector: 'app-possession',
  templateUrl: './possession.component.html',
  styleUrls: ['./possession.component.css']
})
export class PossessionComponent implements OnInit {

  attractions = new Map();
  commodites = new Map();
  boutiques = new Map();
  restaurants = new Map();
  employes = new Map();

  private parcStorage = localStorage.getItem("parcChosen");

 constructor(
    private ar: ActivatedRoute,
    private gestionAchatService:GestionAchatService,
    private gestionElementService:GestionElementService,
    private router:Router) {

    this.ar.params.subscribe((params) => {
      if (params.typeElement && this.parcStorage) {
        if (params.typeElement==="attraction"){
          this.gestionAchatService.getByElementAndParc("attraction",JSON.parse(this.parcStorage)).subscribe(
            (res) =>
 {
              res.forEach(
                achat => this.attractions.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
        else if(params.typeElement==="boutique"){
          this.gestionAchatService.getByElementAndParc("boutique",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.boutiques.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
         else if (params.typeElement==="restaurant"){
          this.gestionAchatService.getByElementAndParc("restaurant",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.restaurants.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
        else if (params.typeElement==="employe"){
          this.gestionAchatService.getByElementAndParc("employe",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.employes.set(achat.element,achat.nbSameElement)
              )},
            (error) => console.log(error)
          );
        }
        else if (params.typeElement==="commodite"){
          this.gestionAchatService.getByElementAndParc("commodite",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.commodites.set(achat.element,achat.nbSameElement)
              )},
            (error) => console.log(error)
          );
        }
      }
      else if (this.parcStorage){
        this.gestionAchatService.getByParc(JSON.parse(this.parcStorage)).subscribe(
          (res) => {
            res.forEach(
              achat => {
              if(achat.typeElement==="attraction") {this.attractions.set(achat.element,achat.niveauAmelioration);}
              if(achat.typeElement==="boutique") {this.boutiques.set(achat.element,achat.niveauAmelioration);}
              if(achat.typeElement==="restaurant") {this.restaurants.set(achat.element,achat.niveauAmelioration);}
              if(achat.typeElement==="commodite") {this.commodites.set(achat.element,achat.nbSameElement);}
              if(achat.typeElement==="employe") {this.employes.set(achat.element,achat.nbSameElement);}
              }
            )},
          (error) => console.log(error)
        );
      }
    });
  }


  ngOnInit(): void {}

  ameliorer(id:number | undefined){}

  vendre(id:number| undefined){}

  public getParc(): Parc{
    if(this.parcStorage){
      return JSON.parse(this.parcStorage);
    }
    return new Parc("probleme","probleme");
  }

}

