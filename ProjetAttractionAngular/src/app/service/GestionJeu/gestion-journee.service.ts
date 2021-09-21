/*
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class GestionJourneeService {

  private url: string="http://localhost:8080/Yolo/api/journee"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) { }

  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public finJourneeS(parc:Parc){
    this.initHeaders();
    return this.httpClient.finJournee(this.url+"&parc="+parc,{headers:this.headers});
  }




}
*/
