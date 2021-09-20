import { Restaurant } from './../../model/restaurant';
import { Employe } from './../../model/employe';
import { Boutique } from './../../model/boutique';
import { Attraction } from './../../model/attraction';
import { Observable } from 'rxjs';
import { Commodite } from './../../model/commodite';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { filter } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GestionFiltreService {

  private url: string="http://localhost:8080/Yolo/api/filtre"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) { }

  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public getCommodite(): Observable<Commodite[]>{
    this.initHeaders();
    return this.httpClient.get<Commodite[]>(this.url+"/commodite",{headers:this.headers});
  }

  public filtreCommodite(commodites:Commodite | any){
    this.getCommodite();
    filter(commodites);
  }

  public getAttraction(): Observable<Attraction[]>{
    this.initHeaders();
    return this.httpClient.get<Attraction[]>(this.url+"/attraction",{headers:this.headers});
  }

  public filtreAttraction(attractions:Attraction | any){
    this.getAttraction();
    filter(attractions);
  }

  public getBoutique(): Observable<Boutique[]>{
    this.initHeaders();
    return this.httpClient.get<Boutique[]>(this.url+"/boutique",{headers:this.headers});
  }

  public filtreBoutique(boutiques:Boutique | any){
    this.getBoutique();
    filter(boutiques);
  }

  public getEmploye(): Observable<Employe[]>{
    this.initHeaders();
    return this.httpClient.get<Employe[]>(this.url+"/employe",{headers:this.headers});
  }

  public filtreEmploye(employes:Employe | any){
    this.getEmploye();
    filter(employes);
  }

  public getRestaurant(): Observable<Restaurant[]>{
    this.initHeaders();
    return this.httpClient.get<Restaurant[]>(this.url+"/restaurant",{headers:this.headers});
  }

  public filtreRestaurant(restaurants:Restaurant | any){
    this.getRestaurant();
    filter(restaurants);
  }

}
