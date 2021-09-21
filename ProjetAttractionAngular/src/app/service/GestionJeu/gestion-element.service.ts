import { Element } from 'src/app/model/element';
import { Observable } from 'rxjs';
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

  public getAttraction(): Observable <Element[]>{
    this.initHeaders();
    return this.httpClient.get<Element[]>(this.url +"/attraction",{headers:this.headers});;
  }

  public getBoutique(): Observable <Element[]>{
    this.initHeaders();
    return this.httpClient.get<Element[]>(this.url +"/boutique",{headers:this.headers});
  }

  public getCommodite(): Observable <Element[]>{
    this.initHeaders();
    return this.httpClient.get<Element[]>(this.url +"/commodite",{headers:this.headers});
  }

  public getEmploye(): Observable <Element[]>{
    this.initHeaders();
    return this.httpClient.get<Element[]>(this.url +"/employe",{headers:this.headers});
  }

  public getRestaurant(): Observable <Element[]>{
    this.initHeaders();
    return this.httpClient.get<Element[]>(this.url +"/restaurant",{headers:this.headers});
  }

}
