import { CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminRightsService implements CanActivate {

  constructor() { }

  canActivate():boolean{
    return localStorage.getItem("isAdmin")?true:false;
  }


}
