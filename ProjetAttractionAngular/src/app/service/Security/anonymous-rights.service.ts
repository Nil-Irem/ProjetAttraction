import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AnonymousRightsService {

  constructor() {}

  canActivate():boolean{
    if (localStorage.getItem("isAdmin") || localStorage.getItem("isJoueur") || localStorage.getItem("parcChosen") ){
      return false;
    }
    else
    {
      return true;
    }
  }
}
