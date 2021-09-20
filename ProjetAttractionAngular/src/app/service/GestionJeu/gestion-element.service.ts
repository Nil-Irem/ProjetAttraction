import { Parc } from './../../model/parc';
import { Restaurant } from './../../model/restaurant';
import { Employe } from './../../model/employe';
import { Commodite } from './../../model/commodite';
import { Boutique } from './../../model/boutique';
import { Observable } from 'rxjs';
import { Attraction } from './../../model/attraction';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GestionElementService {

  private url: string="http://localhost:8080/Yolo/api"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient:HttpClient) { }

  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public getAttraction(): Observable <Attraction[]>{
    this.initHeaders();
    return this.httpClient.get<Attraction[]>(this.url +"/attraction",{headers:this.headers});
  }

  public getBoutique(): Observable <Boutique[]>{
    this.initHeaders();
    return this.httpClient.get<Boutique[]>(this.url +"/boutique",{headers:this.headers});
  }

  public getCommodite(): Observable <Commodite[]>{
    this.initHeaders();
    return this.httpClient.get<Commodite[]>(this.url +"/commodite",{headers:this.headers});
  }

  public getEmploye(): Observable <Employe[]>{
    this.initHeaders();
    return this.httpClient.get<Employe[]>(this.url +"/employe",{headers:this.headers});
  }

  public getRestaurant(): Observable <Restaurant[]>{
    this.initHeaders();
    return this.httpClient.get<Restaurant[]>(this.url +"/restaurant",{headers:this.headers});
  }

}
