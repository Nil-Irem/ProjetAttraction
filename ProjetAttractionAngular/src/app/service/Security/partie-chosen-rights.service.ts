import { CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PartieChosenRightsService implements CanActivate {

  constructor() { }

  canActivate():boolean{
    return localStorage.getItem("parcChosen")?true:false;
  }
}
