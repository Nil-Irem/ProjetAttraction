import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { GestionElementService } from './../../../service/GestionJeu/gestion-element.service';
import { GestionAchatService } from './../../../service/GestionJeu/gestion-achat.service';
import { GestionParcService } from './../../../service/GestionJeu/gestion-parc.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Element } from 'src/app/model/element';
import { Achat } from 'src/app/model/achat';
import { Parc } from 'src/app/model/parc';
import { Observable } from 'rxjs';

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
  achatTerrain = false;
  prixM2Terrain = 1000;
  private elementAcheter:Element=new Element("undefined");

  AchatTerrainForm: FormGroup;
  InputM2Terrain : FormControl;

  constructor(
    private ar: ActivatedRoute,
    private formBuilder:FormBuilder,
    private gestionAchatService:GestionAchatService,
    private gestionElementService:GestionElementService,
    private gestionParcService: GestionParcService) {

    let storage = localStorage.getItem("parcChosen");
    let maxM2 = 100;

    if (storage){
      let parc:Parc = JSON.parse(storage);
      maxM2 = parc.argent!/this.prixM2Terrain;
    }

    this.InputM2Terrain = this.formBuilder.control('',[
      Validators.required,
      Validators.min(1),
      Validators.max(maxM2)
    ]);

    this.AchatTerrainForm = this.formBuilder.group({
      m2Terrain: this.InputM2Terrain
    });
    this.constructionListes();
  }

  ngOnInit(): void {}

  get f() { return this.AchatTerrainForm.controls; }

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
        else if (params.typeElement==="terrain"){
          this.achatTerrain = true;
        }
      }
      else{
        this.achatTerrain = true;
        this.listAttractionWithoutAchat();
        this.listBoutiqueWithoutAchat();
        this.listRestaurantWithoutAchat();
        this.listCommodite();
        this.listEmploye();
      }
    });
  }


  private listAttractionWithoutAchat(){
    let allAttractions: Element[]=[];
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      this.gestionElementService.getAttraction().subscribe(
        (res) => {
          res.forEach(
            attraction => attraction.typeElement = "attraction"
          );
          allAttractions = res;
        },
        (error) => console.log("Erreur achat, liste all attraction ",error)
      );

      this.gestionAchatService.getByTypeElementAndParc("attraction",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              allAttractions.forEach(
                attraction => {
                  if (attraction.id===achat.element.id)
                    {allAttractions.splice(allAttractions.indexOf(attraction),1)}
                }
              )
            }
          );
          this.attractions = allAttractions;
        },
        (error) => console.log("Erreur achat, liste attractions parc ",error)
      );
    }
  }



  private listBoutiqueWithoutAchat(){
    let allBoutiques: Element[]=[];
    let parcStorage = localStorage.getItem("parcChosen");

    if (parcStorage){
      this.gestionElementService.getBoutique().subscribe(
        (res) => {
          res.forEach(
            boutique => boutique.typeElement = "boutique"
          );
          allBoutiques = res;
        },
        (error) => console.log("Erreur achat, liste all boutiques ",error)
      );

      this.gestionAchatService.getByTypeElementAndParc("boutique",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              allBoutiques.forEach(
                boutique => {
                  if (boutique.id===achat.element.id)
                    {allBoutiques.splice(allBoutiques.indexOf(boutique),1)}
                }
              )
            }
          );
          this.boutiques = allBoutiques;
        },
        (error) => console.log("Erreur achat, liste boutiques parc ",error)
      );
    }
  }

  private listRestaurantWithoutAchat(){
    let allRestaurants: Element[]=[];
    let parcStorage = localStorage.getItem("parcChosen");
    if (parcStorage){
      this.gestionElementService.getRestaurant().subscribe(
        (res) =>{
          res.forEach(
            restaurant => restaurant.typeElement = "restaurant"
          );
          allRestaurants = res;
        },
        (error) => console.log("Erreur achat, liste tous restaurants ",error)
      );

      this.gestionAchatService.getByTypeElementAndParc("restaurant",JSON.parse(parcStorage)).subscribe(
        (res) => {
          res.forEach(
            achat => {
              allRestaurants.forEach(
                restaurant => {
                  if (restaurant.id===achat.element.id)
                    {allRestaurants.splice(allRestaurants.indexOf(restaurant),1)}
                }
              )
            }
          );
          this.restaurants = allRestaurants;
        },
        (error) => console.log("Erreur achat, liste restaurants parc ",error)
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
      (error) => console.log("Erreur achat, liste commodites ",error)
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
      (error) => console.log("Erreur achat, liste employe ",error)
    );
  }



  public achat(element:Element){
    let parcStorage = localStorage.getItem("parcChosen");
    let confirmation = false;
    if(element.nom){
      if (element.typeElement==="employe"){
        confirmation = confirm("Voulez vous vraiment embaucher l'employé "+element.nom+" ?");
      }
      else{
        confirmation = confirm("Voulez vous vraiment acheter "+element.nom+" pour "+element.prixAcquisition+"€ ?");
      }
    }
    if (parcStorage && confirmation){
      let parc:Parc = JSON.parse(parcStorage);
      if (element.typeElement==="employe"||(parc.argent && element.prixAcquisition && parc.argent>element.prixAcquisition)){
        if ((parc.taille && element.taille && parc.taille>element.taille)||element.typeElement==="employe") {
          if (element.typeElement==="attraction" || element.typeElement==="boutique" || element.typeElement==="restaurant"){
            this.gestionAchatService.create(new Achat(parc,element,element.typeElement,0,0)).subscribe(
              (res) => {
                this.elementAcheter=element;
                this.newParc(parc);
              },
              (error) => console.log("Erreur achat, création achat ",error)
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
                        (error) => console.log("Erreur achat, save achat ",error)
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
                    (error) => console.log("Erreur achat, création achat ",error)
                  );
                }
              },
              (error) => console.log("Erreur getAchat, création achat ",error)
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


  public acheterTerrain(){
    let parcStorage = localStorage.getItem("parcChosen");
    let tailleAchatTerrain = this.AchatTerrainForm.get('m2Terrain')?.value;
    this.AchatTerrainForm.reset();
    let confirmation = confirm("Voulez vous vraiment acheter "+tailleAchatTerrain+"m² de terrain pour "+this.prixM2Terrain*tailleAchatTerrain+"€ ?");

    if (confirmation && parcStorage){
      let parc = JSON.parse(parcStorage);

      if (parc.argent && parc.argent>this.prixM2Terrain*tailleAchatTerrain && parc.taille){
        parc.argent -= this.prixM2Terrain*tailleAchatTerrain;
        parc.taille += tailleAchatTerrain;
      }

      this.gestionParcService.save(parc).subscribe(
        (parcSave) => localStorage.setItem("parcChosen",JSON.stringify(parcSave)),
        (error) => console.log("Erreur achatTerrain, saveParc ",error)
      );
    }
  }


  private newParc(parc:Parc){
    if (parc.argent && this.elementAcheter.prixAcquisition){
      parc.argent -= this.elementAcheter.prixAcquisition;
    }

    let storageTaille = localStorage.getItem("tailleTotStructure");
    if (storageTaille && this.elementAcheter.taille){
      let tailleTotStructure:number = JSON.parse(storageTaille);
      tailleTotStructure += this.elementAcheter.taille;
      localStorage.setItem("tailleTotStructure",JSON.stringify(tailleTotStructure));
    }

    if (parc.taille && this.elementAcheter.taille){
      parc.taille -= this.elementAcheter.taille;
    }

    this.gestionParcService.save(parc).subscribe(
      (parcSave) => {localStorage.setItem("parcChosen",JSON.stringify(parcSave));},
      (error) => console.log("Erreur achat, saveParc ",error)
    );
    this.constructionListes();
  }



}
