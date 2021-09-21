import { Element } from 'src/app/model/element';
import { Parc } from 'src/app/model/parc';
import { GestionElementService } from './../../../service/GestionJeu/gestion-element.service';
import { ActivatedRoute } from '@angular/router';
import { GestionAchatService } from './../../../service/GestionJeu/gestion-achat.service';
import { Component, OnInit } from '@angular/core';
import { Achat } from 'src/app/model/achat';

@Component({
  selector: 'app-achat',
  templateUrl: './achat.component.html',
  styleUrls: ['./achat.component.css']
})
export class AchatComponent implements OnInit {

  attractions : Element[]=[];
  commodites : Element[]=[];
  boutiques : Element[]=[];
  restaurants : Element[]=[];
  employes : Element[]=[];
  afficheMessage = false;
  private elementAcheter:Element=new Element("undefined");


  constructor(
    private ar: ActivatedRoute,
    private gestionAchatService:GestionAchatService,
    private gestionElementService:GestionElementService) {

      this.constructionListes();
  }

  ngOnInit(): void {
  }


  private constructionListes(){
    this.ar.params.subscribe((params) => {
      if (params.typeElement) {
        if (params.typeElement==="attraction"){
          this.listAttractionWithoutAchat();
        }
        else if(params.typeElement==="boutique"){
          this.listBoutiqueWithoutAchat();
        }
         else if (params.typeElement==="restaurant"){
          this.listRestaurantWithoutAchat();
        }
        else if (params.typeElement==="employe"){
          this.listEmploye();
        }
        else if (params.typeElement==="commodite"){
          this.listCommodite();
        }
      }
      else{
        this.listAttractionWithoutAchat();
        this.listBoutiqueWithoutAchat();
        this.listRestaurantWithoutAchat();
        this.listCommodite();
        this.listEmploye();
      }
    });
  }

  private listAttractionWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      this.gestionElementService.getAttraction().subscribe(
        (res) => this.attractions = res,
        (error) => console.log(error)
      );

      this.gestionAchatService.getByTypeElementAndParc("attraction",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              if(this.attractions.includes(achat.element))
                {this.attractions.splice(this.attractions.indexOf(achat.element),1);}
            }
          );

          this.attractions.forEach(
            attraction => attraction.typeElement = "attraction"
          );},
        (error) => console.log(error)
      );
    }
  }



  private listBoutiqueWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");

    if (parcStorage){
      this.gestionElementService.getBoutique().subscribe(
        (res) => this.boutiques = res,
        (error) => console.log(error)
      );

      this.gestionAchatService.getByTypeElementAndParc("boutique",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              if(this.attractions.includes(achat.element))
                {this.attractions.splice(this.attractions.indexOf(achat.element),1);}
            }
          );
          this.boutiques.forEach(
            boutique => boutique.typeElement = "boutique"
          );
        },
        (error) => console.log(error)
      );

    }
  }

  private listRestaurantWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      this.gestionElementService.getRestaurant().subscribe(
        (res) => this.restaurants = res,
        (error) => console.log(error)
      );

      this.gestionAchatService.getByTypeElementAndParc("restaurant",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              if(this.restaurants.includes(achat.element))
                {this.restaurants.splice(this.restaurants.indexOf(achat.element),1);}
            }
          );
          this.restaurants.forEach(
            restaurant => restaurant.typeElement = "restaurant"
          );
        },
        (error) => console.log(error)
      );
    }
  }


  private listCommodite(){
    this.gestionElementService.getCommodite().subscribe(
      (res) => {
        res.forEach(
          commodite => commodite.typeElement = "commodite"
        );
        this.commodites = res;
      },
      (error) => console.log(error)
    );
  }




  private listEmploye(){
    this.gestionElementService.getEmploye().subscribe(
      (res) => {
        res.forEach(
          employe => employe.typeElement = "employe"
        );
        this.employes = res;
      },
      (error) => console.log(error)
    );
  }



  public achat(element:Element){
    console.log("type="+element.typeElement);
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      if (element.typeElement==="attraction" || element.typeElement==="boutique" || element.typeElement==="restaurant"){
        this.gestionAchatService.create(new Achat(JSON.parse(parcStorage),element,element.typeElement,0,0)).subscribe(
          (res) => {
            this.elementAcheter=element;
            this.newParc();
          },
          (error) => console.log(error)
        );
      }
      else if (element.typeElement==="commodite" || element.typeElement==="employe"){
        let exist = false;
        this.gestionAchatService.getByTypeElementAndParc(element.typeElement,JSON.parse(parcStorage)).subscribe(
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
            this.elementAcheter = element;
            this.afficheMessage = true;
            this.newParc();
          },
          (error) => console.log(error)
        )
      }
    }
  }


  newParc(){
    let storage = localStorage.getItem("parcChosen");
    if (storage){
      let parc:Parc = JSON.parse(storage);

      if (parc.argent && this.elementAcheter.prixAcquisition){
        parc.argent -= this.elementAcheter.prixAcquisition;
      }

      if (parc.taille && this.elementAcheter.taille){
        parc.taille -= this.elementAcheter.taille;
      }

      localStorage.setItem("parcChosen",JSON.stringify(parc));
    }
    this.constructionListes();
  }

}
