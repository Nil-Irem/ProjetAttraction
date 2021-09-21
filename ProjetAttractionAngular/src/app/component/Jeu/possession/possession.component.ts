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
    private router:Router) {

      this.listPossession();
  }


  ngOnInit(): void {}


  public listPossession(){
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


  ameliorer(id:number|undefined){
    let storage = localStorage.getItem("parcChosen");
    if (storage && id){
      let parc:Parc = JSON.parse(storage);
      let prix = 0;

      this.gestionAchatService.getByElementAndParc(id,JSON.parse(storage)).subscribe(
        (res) => {
          res.niveauAmelioration++;
          this.gestionAchatService.update(res).subscribe();
        },
        (error) => console.log(error)
      );
	// 	Achat achat = daoA.findByElementAndParc(element,parc).get();
	// 	achat.setNiveauAmelioration(achat.getNiveauAmelioration()+1);
	// 	daoA.save(achat);

	// 	parc.setArgent(parc.getArgent()-prix);
    localStorage.setItem("parcChosen",JSON.stringify(parc));
    this.listPossession();
    }
  }

vendre(id:number|undefined){
    // Element element = new Attraction();
		// Parc parc = (Parc) session.getAttribute("parc");
		// double prix=0;
		// switch(type)
		// {
		// 	case "attraction" : element=daoAt.findById(id).get();prix=prixVenteAttraction;break;
		// 	case "boutique" : element=daoB.findById(id).get();prix=prixVenteBoutique;break;
		// 	case "restaurant" : element=daoR.findById(id).get();prix=prixVenteRestaurant;break;
		// 	case "commodite" : element=daoC.findById(id).get();prix=prixVenteCommodite;break;
		// 	case "employe" : element=daoE.findById(id).get();break;
		// 	default : return new ModelAndView("redirect:/possessions");
		// }
		// Achat achat = daoA.findByElementAndParc(element,parc).get();
		// daoA.delete(achat);

		// parc.setArgent(parc.getArgent()+prix);
		// session.setAttribute("parc", parc);
		// return new ModelAndView("redirect:/possessions");
  }

  public getParc(): Parc{
    let parc = localStorage.getItem("parcChosen");
    if(parc){
      return JSON.parse(parc);
    }
    return new Parc("probleme","probleme");
  }

}

