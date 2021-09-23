import { GestionParcService } from './../../../service/GestionJeu/gestion-parc.service';
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
  pourcentageVente = 0.7;

  private parcStorage = localStorage.getItem("parcChosen");

 constructor(
    private ar: ActivatedRoute,
    private gestionAchatService:GestionAchatService,
    private gestionParcService: GestionParcService) {

      this.listPossession();
  }


  ngOnInit(): void {}


  private listPossession(){
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


  public ameliorer(element:Element){
    let confirmation = confirm("Voulez vous vraiment améliorer "+element.nom+" pour "+ this.prixAmelioration+"€ ?");
    let storage = localStorage.getItem("parcChosen");
    if (storage && element.id && confirmation){
      let parc:Parc = JSON.parse(storage);

      if (parc.argent && parc.argent>this.prixAmelioration){
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
                    parc.argent -= this.prixAmelioration;
                  }
                  this.newParc(parc);
                },
                (error) => console.log(error)
              );
            }
          },
          (error) => console.log(error)
        );
      }
      else{
        alert("Vous n'avez pas assez d'argent pour améliorer "+element.nom);
      }
    }
  }


  public vendre(element:Element){
    let confirmation = confirm("Voulez vous vraiment vendre "+element.nom+" pour "+element.prixAcquisition!*this.pourcentageVente+"€ ?");
    let storage = localStorage.getItem("parcChosen");
    if (storage && element.id && confirmation){
      let parc:Parc = JSON.parse(storage);

      this.gestionAchatService.getByElementAndParc(element.id,parc).subscribe(
        (res) => {
          if (res.id){
            this.gestionAchatService.delete(res.id).subscribe(
              (res2) =>{
                if (parc.argent && element.prixAcquisition && parc.taille && element.taille){
                  parc.argent += element.prixAcquisition*this.pourcentageVente;
                  parc.taille += element.taille;
                }
                localStorage.setItem("parcChosen",JSON.stringify(parc));
                this.newParc(parc);
              },
              (error) => console.log(error)
            );
          }
        },
        (error) => console.log(error)
      );
    }
  }


  public vendreCommodite(element:Element,nbTotal:number){
    let nbVente: number;
    if (nbTotal!==1){
      nbVente = Math.round(Number(prompt("Vous avez "+nbTotal+" "+ element.nom+", combien voulez vous en vendre ?")));
      while (isNaN(nbVente)){
        if(confirm("Vous devez rentrer un nombre voulez vous réessayer ?")){
          nbVente = Math.round(Number(prompt("Vous avez "+nbTotal+" "+ element.nom+", combien voulez vous en vendre ?")));
        }
        else{
          nbVente = 0;
        }
      }
    }
    else{
      nbVente=1;
    }

    if (nbVente!==0){
      if (nbVente > nbTotal){
        alert("Vous n'avez pas autant de "+element.nom);
      }
      else if (nbVente < 0){
        alert("Impossible de vendre "+nbVente+" "+element.nom);
      }
      else{
        let confirmationRenvoie = confirm("Voulez vous vraiment vendre "+nbVente+" "+element.nom+" ?");
        let storage = localStorage.getItem("parcChosen");
        if (storage && element.id && confirmationRenvoie){
          let parc:Parc = JSON.parse(storage);

          this.gestionAchatService.getByElementAndParc(element.id,parc).subscribe(
            (res) => {
              if (res.id){
                if (res.nbSameElement>1 && res.nbSameElement>nbVente){
                  res.nbSameElement-=nbVente;
                  this.gestionAchatService.update(res).subscribe(
                    (res2) => {
                      if (parc.argent && element.prixAcquisition && parc.taille && element.taille){
                        parc.argent += element.prixAcquisition*this.pourcentageVente;
                        parc.taille += element.taille;
                      }
                      this.newParc(parc);
                    },
                    (error) => console.log(error)
                  );
                }
                else if (res.nbSameElement===1 || res.nbSameElement===nbVente){
                  this.gestionAchatService.delete(res.id).subscribe(
                    (res2) =>  {
                      if (parc.argent && element.prixAcquisition && parc.taille && element.taille){
                        parc.argent += element.prixAcquisition*this.pourcentageVente;
                        parc.taille += element.taille;
                      }
                      this.newParc(parc);
                    },
                    (error) => console.log(error)
                  );
                }
              }
            },
            (error) => console.log(error)
          );
        }
      }
    }
  }



  public virer(element:Element,nbTotal:number){
    let nbRenvoie:number;
    if (nbTotal!==1){
      nbRenvoie = Math.round(Number(prompt(nbTotal+" "+ element.metier+" travaille(nt) pour vous, combien voulez vous en renvoyer ?")));
      while (isNaN(nbRenvoie)){
        if(confirm("Vous devez rentrer un nombre voulez vous réessayer ?")){
          nbRenvoie = Math.round(Number(prompt(nbTotal+" "+ element.metier+" travaille(nt) pour vous, combien voulez vous en renvoyer ?")));
        }
        else{
          nbRenvoie = 0;
        }
      }
    }
    else{
      nbRenvoie=1;
    }
    if (nbRenvoie!==0){
      if (nbRenvoie > nbTotal){
        alert("Vous n'avez pas autant de "+element.metier);
      }
      else if (nbRenvoie < 0){
        alert("Impossible de renvoyer "+nbRenvoie+" "+element.metier);
      }
      else{
        let confirmationRenvoie = confirm("Voulez vous vraiment renvoyer "+nbRenvoie+" "+element.metier+" ?");
        let storage = localStorage.getItem("parcChosen");
        if (storage && element.id && confirmationRenvoie){
          let parc:Parc = JSON.parse(storage);

          this.gestionAchatService.getByElementAndParc(element.id,parc).subscribe(
            (res) => {
              if (res.id){
                if (res.nbSameElement>1 && res.nbSameElement>nbRenvoie){
                  res.nbSameElement-=nbRenvoie;
                  this.gestionAchatService.update(res).subscribe(
                    (res2) => this.listPossession(),
                    (error) => console.log(error)
                  );
                }
                else if (res.nbSameElement===1 || res.nbSameElement===nbRenvoie){
                  this.gestionAchatService.delete(res.id).subscribe(
                    (res2) => this.listPossession(),
                    (error) => console.log(error)
                  );
                }
              }
            },
            (error) => console.log(error)
          );
        }
      }
    }
  }

  public newParc(parc:Parc){
    this.gestionParcService.save(parc).subscribe(
      (parcSave) => {
        localStorage.setItem("parcChosen",JSON.stringify(parc));
        this.listPossession();},
      (error) => console.log("Erreur deconnexion, saveParc ",error)
    );
  }

  public prixVente(prixAcquisition:number):number{
    return Math.round(prixAcquisition*this.pourcentageVente);

  }


  public getParc(): Parc{
    let parc = localStorage.getItem("parcChosen");
    if(parc){
      return JSON.parse(parc);
    }
    return new Parc("probleme","probleme");
  }

}

