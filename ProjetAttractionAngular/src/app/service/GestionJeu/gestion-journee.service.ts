import { Observable } from 'rxjs';
import { Parc } from 'src/app/model/parc';

import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class GestionJourneeService {

  private url: string="http://localhost:8080/Yolo/api/finJournee/byParc"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) { }

  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public finJourneeS(parc:Parc):Observable<String[]>{
    this.initHeaders();
    return this.httpClient.post<String[]>(this.url,parc,{headers:this.headers});
  }




}

