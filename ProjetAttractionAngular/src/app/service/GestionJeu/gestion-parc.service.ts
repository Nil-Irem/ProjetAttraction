import { Parc } from './../../model/parc';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from 'src/app/model/compte';

@Injectable({
  providedIn: 'root'
})
export class GestionParcService {

  private url: string="http://localhost:8080/Yolo/api/parc"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) {
  }


  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public listByJoueur(compte:Compte|undefined): Observable<Parc[]>{
    this.initHeaders();
    if (compte){
      return this.httpClient.post<Parc[]>(this.url+"/byJoueur",compte,{headers:this.headers});
    }
    else{
      return new Observable;
    }
  }

  public create(parc:Parc): Observable<Parc>{
    this.initHeaders();
    const obj = {
      nomParc:parc.nomParc,
      typeDifficulte:parc.typeDifficulte,
      id:null
    };
    return this.httpClient.post<Parc>(this.url+"/create",obj,{headers:this.headers});
  }

  public delete(id:number){
    this.initHeaders();
    return this.httpClient.delete(this.url+"/delete/"+id,{headers:this.headers});
  }

}
