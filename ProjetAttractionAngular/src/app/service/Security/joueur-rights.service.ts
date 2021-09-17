import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class JoueurRightsService implements CanActivate {

  constructor() { }

  canActivate(): boolean {
    if(localStorage.getItem("isJoueur") && !localStorage.getItem("parcChosen")){
      return true;
    }
    return false;
  }

}
