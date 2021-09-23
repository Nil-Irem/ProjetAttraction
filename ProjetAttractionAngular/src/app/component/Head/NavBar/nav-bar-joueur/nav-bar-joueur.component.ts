import { GestionParcService } from './../../../../service/GestionJeu/gestion-parc.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'nav-bar-joueur',
  templateUrl: './nav-bar-joueur.component.html',
  styleUrls: ['./nav-bar-joueur.component.css']
})
export class NavBarJoueurComponent implements OnInit {

  constructor(private router:Router,
    private gestionParcService: GestionParcService) { }

  ngOnInit(): void {
  }

  deconnection(){
    let storage = localStorage.getItem("parcChosen");
    if (storage){
      this.gestionParcService.save(JSON.parse(storage)).subscribe(
        (parcSave) => {},
        (error) => console.log("Erreur deconnexion, saveParc ",error)
      );
    }
    localStorage.clear()
    this.router.navigate(['/accueil']);
  }

  changerParc(){
    let storage = localStorage.getItem("parcChosen");
    if (storage){
      this.gestionParcService.save(JSON.parse(storage)).subscribe(
        (parcSave) => {
          localStorage.removeItem("parcChosen");
          this.router.navigate(['/jeu/choixparc']);},
        (error) => console.log("Erreur deconnexion, saveParc ",error)
      );
    }
  }

  parcIsChosen():boolean{
    if (localStorage.getItem("parcChosen")){
      return true;
    }
    return false;
  }
}
