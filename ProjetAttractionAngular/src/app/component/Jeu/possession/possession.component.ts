import { Achat } from 'src/app/model/achat';
import { FormControl, Validators } from '@angular/forms';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';
import { Parc } from 'src/app/model/parc';
import { Element } from 'src/app/model/element';

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
  prixAmelioration = 500;
  prixVente = 0;



  private parcStorage = localStorage.getItem("parcChosen");

 constructor(
    private ar: ActivatedRoute,
    private gestionAchatService:GestionAchatService,
    private formBuilder: FormBuilder) {

      this.listPossession();

  }

  ngOnInit(): void {}


  public listPossession(){
    this.attractions.clear();
    this.boutiques.clear();
    this.restaurants.clear();
    this.commodites.clear();
    this.employes.clear();

    this.ar.params.subscribe((params) => {
      if (params.typeElement && this.parcStorage) {
        if (params.typeElement==="attraction"){
          this.gestionAchatService.getByTypeElementAndParc("attraction",JSON.parse(this.parcStorage)).subscribe(
            (res) =>{
              res.forEach(
                achat => this.attractions.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
        else if(params.typeElement==="boutique"){
          this.gestionAchatService.getByTypeElementAndParc("boutique",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.boutiques.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
         else if (params.typeElement==="restaurant"){
          this.gestionAchatService.getByTypeElementAndParc("restaurant",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.restaurants.set(achat.element,achat.niveauAmelioration)
              )},
            (error) => console.log(error)
          );
        }
        else if (params.typeElement==="employe"){
          this.gestionAchatService.getByTypeElementAndParc("employe",JSON.parse(this.parcStorage)).subscribe(
            (res) => {
              res.forEach(
                achat => this.employes.set(achat.element,achat.nbSameElement)
              )},
            (error) => console.log(error)
          );
        }
        else if (params.typeElement==="commodite"){
          this.gestionAchatService.getByTypeElementAndParc("commodite",JSON.parse(this.parcStorage)).subscribe(
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


  ameliorer(element:Element){
    let storage = localStorage.getItem("parcChosen");
    if (storage && element.id){
      let parc:Parc = JSON.parse(storage);
      let prixAmelioration = 5;


      this.gestionAchatService.getByElementAndParc(element.id,JSON.parse(storage)).subscribe(
        (res) => {
          if(element.nbAmelioration && res.niveauAmelioration===element.nbAmelioration){
            console.log("amelioration max atteinte");
          }
          else
          {
            res.niveauAmelioration++;

            this.gestionAchatService.update(res).subscribe(
              (res2) => {
                if (parc.argent){
                  parc.argent -= prixAmelioration;
                }
                localStorage.setItem("parcChosen",JSON.stringify(parc));
                this.listPossession();
              },
              (error) => console.log(error)
            );
          }
        },
        (error) => console.log(error)
      );
    }
  }


  vendre(id:number|undefined,nom:string){
    let confirmation = confirm("Voulez vous vraiment vendre "+nom+" ?");
    let storage = localStorage.getItem("parcChosen");
    if (storage && id && confirmation){
      let parc:Parc = JSON.parse(storage);
      let prixVente = 100.5;

      this.gestionAchatService.getByElementAndParc(id,JSON.parse(storage)).subscribe(
        (res) => {
          if (res.id){
          this.gestionAchatService.delete(res.id).subscribe(
            (res2) =>{
              if (parc.argent){
                parc.argent += prixVente;
              }
              localStorage.setItem("parcChosen",JSON.stringify(parc));
              this.listPossession();
            },
            (error) => console.log(error)
          );
        }},
        (error) => console.log(error)
      );
    }
  }

  virer(id:number|undefined,nom:string){
    let confirmation = confirm("Voulez vous vraiment virer "+nom+" ?");
    let storage = localStorage.getItem("parcChosen");
    if (storage && id && confirmation){
      let parc:Parc = JSON.parse(storage);
      let prixVente = 100.5;

      this.gestionAchatService.getByElementAndParc(id,JSON.parse(storage)).subscribe(
        (res) => {
          if (res.id){
          this.gestionAchatService.delete(res.id).subscribe(
            (res2) =>{
              if (parc.argent){
                parc.argent += prixVente;
              }
              localStorage.setItem("parcChosen",JSON.stringify(parc));
              this.listPossession();
            },
            (error) => console.log(error)
          );
        }},
        (error) => console.log(error)
      );
    }
  }


  public getParc(): Parc{
    let parc = localStorage.getItem("parcChosen");
    if(parc){
      return JSON.parse(parc);
    }
    return new Parc("probleme","probleme");
  }


  public disabledAmelioration(nvAmelioration:number,nbAmeliorationMax:number):boolean{
    if (nvAmelioration===nbAmeliorationMax){
      return true;
    }
    return false;
  }
}

