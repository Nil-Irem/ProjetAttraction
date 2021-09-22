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


  private async listAttractionWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      await this.gestionElementService.getAttraction().subscribe(
        (res) => {
          res.forEach(
            attraction => attraction.typeElement = "attraction"
          );
          this.attractions = res;
        },
        (error) => console.log(error)
      );

      this.gestionAchatService.getByTypeElementAndParc("attraction",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              this.attractions.forEach(
                attraction => {
                  if (attraction.id===achat.element.id)
                    {this.attractions.splice(this.attractions.indexOf(attraction),1)}
                }
              )
            }
          );
        },
        (error) => console.log(error)
      );
    }
  }



  private async listBoutiqueWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");

    if (parcStorage){
      await this.gestionElementService.getBoutique().subscribe(
        (res) => {
          res.forEach(
            boutique => boutique.typeElement = "boutique"
          );
          this.boutiques = res;
        },
        (error) => console.log(error)
      );

      this.gestionAchatService.getByTypeElementAndParc("boutique",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              this.boutiques.forEach(
                boutique => {
                  if (boutique.id===achat.element.id)
                    {this.boutiques.splice(this.boutiques.indexOf(boutique),1)}
                }
              )
            }
          );
        },
        (error) => console.log(error)
      );
    }
  }

  private async listRestaurantWithoutAchat(){
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      await this.gestionElementService.getRestaurant().subscribe(
        (res) =>{
          res.forEach(
            restaurant => restaurant.typeElement = "restaurant"
          );
          this.restaurants = res;
        },
        (error) => console.log(error)
      );

      await this.gestionAchatService.getByTypeElementAndParc("restaurant",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              this.restaurants.forEach(
                restaurant => {
                  if (restaurant.id===achat.element.id)
                    {this.restaurants.splice(this.restaurants.indexOf(restaurant),1)}
                }
              )
            }
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
          employe => {
            employe.typeElement = "employe";
            employe.nom = employe.metier;
          }
        );
        this.employes = res;
      },
      (error) => console.log(error)
    );
  }



  public achat(element:Element){
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      let parc:Parc = JSON.parse(parcStorage);
      if (element.typeElement==="employe"||(parc.argent && element.prixAcquisition && parc.argent>element.prixAcquisition)){
        if ((parc.taille && element.taille && parc.taille>element.taille)||element.typeElement==="employe") {
          if (element.typeElement==="attraction" || element.typeElement==="boutique" || element.typeElement==="restaurant"){
            this.gestionAchatService.create(new Achat(parc,element,element.typeElement,0,0)).subscribe(
              (res) => {
                this.elementAcheter=element;
                this.newParc(parc);
              },
              (error) => console.log(error)
            );
          }
          else if (element.typeElement==="commodite" || element.typeElement==="employe"){
            let exist = false;
            this.gestionAchatService.getByTypeElementAndParc(element.typeElement,parc).subscribe(
              (res) => {
                res.forEach(
                  achat => {
                    if(element.id === achat.element.id){
                      exist=true;
                      achat.nbSameElement++;
                      this.gestionAchatService.update(achat).subscribe(
                        (res2) => {
                          this.elementAcheter = element;
                          this.newParc(parc);
                        },
                        (error) => console.log(error)
                      );
                    }
                  }
                )
                if (!exist){
                  this.gestionAchatService.create(new Achat(parc,element,element.typeElement,0,1)).subscribe(
                    (res) => {
                      this.elementAcheter=element;
                      this.newParc(parc);
                    },
                    (error) => console.log(error)
                  );
                }
              },
              (error) => console.log(error)
            )
          }
          else{
            console.log("Erreur de donnée")
          }
        }
        else {
          alert("Vous n'avez pas assez de terrain pour acheter "+element.nom);
        }
      }
      else{
        alert("Vous n'avez pas assez d'argent pour acheter "+element.nom);
      }
    }
  }


  private newParc(parc:Parc){
    if (parc.argent && this.elementAcheter.prixAcquisition){
      parc.argent -= this.elementAcheter.prixAcquisition;
    }

    if (parc.taille && this.elementAcheter.taille){
      parc.taille -= this.elementAcheter.taille;
    }

    if(this.elementAcheter.nom){
      if (this.elementAcheter.typeElement==="employe"){
        alert("l'employé "+this.elementAcheter.nom+" a bien été embauché");
      }
      else{
        alert(this.elementAcheter.nom+" a bien été acheté");
      }
    }

    localStorage.setItem("parcChosen",JSON.stringify(parc));
    this.constructionListes();
  }

}
