import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from '../model/compte';

@Injectable({
  providedIn: 'root'
})
export class GestionCompteService {

  private url: string="http://localhost:8080/Yolo/api/compte"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) {
  }


  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }


  public getAll(): Observable<Compte[]>{
    this.initHeaders();
    return this.httpClient.get<Compte[]>(this.url,{headers:this.headers});
  }


  public delete(id: number){
    this.initHeaders();
    return this.httpClient.delete(this.url+"/delete/"+id,{headers:this.headers});
  }


  public get(id: number): Observable<Compte> {
    this.initHeaders();
    return this.httpClient.get<Compte>(this.url + "/id=" + id,{headers:this.headers});
  }


  public create(compte: Compte): Observable<Compte> {
    this.initHeaders();
    const obj = {
      login:compte.login,
      password:compte.password,
      isJoueur:compte.isJoueur,
      id:0
    };
    console.log("create",obj);
    return this.httpClient.post<Compte>(this.url +"/create",obj,{headers:this.headers});
  }


  public update(compte: Compte): Observable<Compte> {
    this.initHeaders();
    return this.httpClient.put<Compte>(this.url + "/" + compte.id, compte,{headers:this.headers});
  }


  public connexion(login:string,password:string): Observable<Compte> {
    this.initHeaders();
    return this.httpClient.post<Compte>(this.url + "/connexion",{login:login,password:password},{headers:this.headers});
  }

  loginIsPresent(login:string):Observable<boolean>{
    return this.httpClient.get<boolean>(this.url+"/loginIsPresent/"+login);
  }
}
