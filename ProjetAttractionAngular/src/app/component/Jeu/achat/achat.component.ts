import { Parc } from 'src/app/model/parc';
import { GestionElementService } from './../../../service/GestionJeu/gestion-element.service';
import { Commodite } from './../../../model/commodite';
import { Employe } from './../../../model/employe';
import { Restaurant } from './../../../model/restaurant';
import { Router, ActivatedRoute } from '@angular/router';
import { GestionAchatService } from './../../../service/GestionJeu/gestion-achat.service';
import { Component, OnInit } from '@angular/core';
import { Attraction } from 'src/app/model/attraction';
import { Boutique } from 'src/app/model/boutique';
import { Achat } from 'src/app/model/achat';
import { Element } from 'src/app/model/element';

@Component({
  selector: 'app-achat',
  templateUrl: './achat.component.html',
  styleUrls: ['./achat.component.css']
})
export class AchatComponent implements OnInit {

  attractions : Attraction[]=[];
  commodites : Commodite[]=[];
  boutiques : Boutique[]=[];
  restaurants : Restaurant[]=[];
  employes : Employe[]=[];
  // attractions : Element[]=[];
  // commodites : Element[]=[];
  // boutiques : Element[]=[];
  // restaurants : Element[]=[];
  // employes : Element[]=[];
  afficheMessage = false;
  private parcStorage = localStorage.getItem("parcChosen");


  constructor(
    // private elementAcheter:Element,
    private ar: ActivatedRoute,
    private gestionAchatService:GestionAchatService,
    private gestionElementService:GestionElementService,
    private router:Router) {

      this.constructionListes();
  }

  ngOnInit(): void {
  }


  private constructionListes(){
    this.ar.params.subscribe((params) => {
      if (params.typeElement) {
        if (params.typeElement==="attraction"){
          this.gestionElementService.getAttraction().subscribe(
            (res) => {this.attractions = res;},
            (error) => console.log(error)
          );
          this.listAttractionWithoutAchat();
        }
        else if(params.typeElement==="boutique"){
          this.gestionElementService.getBoutique().subscribe(
            (res) => this.boutiques = res,
            (error) => console.log(error)
          );
          this.listBoutiqueWithoutAchat();
        }
         else if (params.typeElement==="restaurant"){
          this.gestionElementService.getRestaurant().subscribe(
            (res) => this.restaurants = res,
            (error) => console.log(error)
          );
          this.listRestaurantWithoutAchat();
        }
        else if (params.typeElement==="employe"){
          this.gestionElementService.getEmploye().subscribe(
            (res) => this.employes = res,
            (error) => console.log(error)
          );
        }
        else if (params.typeElement==="commodite"){
          this.gestionElementService.getCommodite().subscribe(
            (res) => this.commodites = res,
            (error) => console.log(error)
          );
        }
      }
      else{
        this.gestionElementService.getAttraction().subscribe(
        (res) => {this.attractions = res;},
        (error) => console.log(error)
      );
      this.gestionElementService.getBoutique().subscribe(
        (res) => this.boutiques = res,
        (error) => console.log(error)
      );
      this.gestionElementService.getRestaurant().subscribe(
        (res) => this.restaurants = res,
        (error) => console.log(error)
      );
      this.gestionElementService.getEmploye().subscribe(
        (res) => this.employes = res,
        (error) => console.log(error)
      );
      this.gestionElementService.getCommodite().subscribe(
        (res) => this.commodites = res,
        (error) => console.log(error)
      );
      this.listAttractionWithoutAchat();
      this.listBoutiqueWithoutAchat();
      this.listRestaurantWithoutAchat();
      }
    });
  }

  private listAttractionWithoutAchat(){
    if (this.parcStorage){
      this.gestionAchatService.getByTypeElementAndParc("attraction",JSON.parse(this.parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
            // if(this.attractions.includes(achat.element))
            //   {this.attractions.splice(this.attractions.indexOf(achat.element),1);}
            this.attractions.forEach(
              attraction =>{
                if(attraction.id === achat.element.id)
                {this.attractions.splice(this.attractions.indexOf(attraction),1);}
              }
            )
            }
          )},
        (error) => console.log(error)
      );
    }
  }

  private listBoutiqueWithoutAchat(){
    if (this.parcStorage){
      this.gestionAchatService.getByTypeElementAndParc("boutique",JSON.parse(this.parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
            this.boutiques.forEach(
              boutique =>{
                if(boutique.id === achat.element.id)
                {this.boutiques.splice(this.boutiques.indexOf(boutique),1);}
              }
            )
            }
          )},
        (error) => console.log(error)
      );
    }
  }

  private listRestaurantWithoutAchat(){
    if (this.parcStorage){
      this.gestionAchatService.getByTypeElementAndParc("restaurant",JSON.parse(this.parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
            this.restaurants.forEach(
              restaurant =>{
                if(restaurant.id === achat.element.id)
                {this.restaurants.splice(this.restaurants.indexOf(restaurant),1);}
              }
            )
            }
          )},
        (error) => console.log(error)
      );
    }
  }



  public achat(element:Element,type:string){
    if (this.parcStorage){
      if (type==="attraction" || type==="boutique" || type==="restaurant"){
        this.gestionAchatService.create(new Achat(JSON.parse(this.parcStorage),element,type,0,0)).subscribe(
          (res) => this.newParc(element) ,
          (error) => console.log(error)
        );
      }
      else if (type==="commodite" || type==="employe"){
        let exist = false;
        this.gestionAchatService.getByTypeElementAndParc(type,JSON.parse(this.parcStorage)).subscribe(
          (res) => {
            res.forEach(
              achat => {
                if(element.id === achat.element.id){
                  exist=true;
                  achat.nbSameElement++;
                  this.gestionAchatService.update(achat).subscribe();
                }
              }
            );
            // this.elementAcheter = element;
            this.afficheMessage = true;
            this.newParc(element);
          },
          (error) => console.log(error)
        )
      }
    }
  }


  newParc(element:Element){
    let storage = localStorage.getItem("parcChosen");
    if (storage){
      let parc:Parc = JSON.parse(storage);

      // if (parc.argent && element.prixAcquisition){
      //   parc.argent -= element.prixAcquisition;
      // }

      // if (parc.taille && element.taille){
      //   parc.taille -= element.taille;
      // }

      localStorage.setItem("parcChosen",JSON.stringify(parc));
    }
    this.constructionListes();
  }

}
