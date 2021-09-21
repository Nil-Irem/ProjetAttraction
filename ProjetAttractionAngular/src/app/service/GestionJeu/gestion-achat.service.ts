import { Element } from './../../model/element';
import { Parc } from './../../model/parc';
import { Observable } from 'rxjs';
import { Achat } from './../../model/achat';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GestionAchatService {

  private url: string="http://localhost:8080/Yolo/api/achat"
  private headers: HttpHeaders =new HttpHeaders();

  constructor(private httpClient: HttpClient) { }

  public initHeaders(){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  public create(achat:Achat): Observable<Achat>{
    this.initHeaders();
    const obj={
      parc:achat.parc,
      typeElement:achat.typeElement,
      nbSameElement:achat.nbSameElement,
      niveauAmelioration:achat.niveauAmelioration,
      id:0
    };
    return this.httpClient.post<Achat>(this.url+"/create/"+achat.element.id,obj,{headers:this.headers});
  }

  public update(achat:Achat): Observable <Achat>{
    this.initHeaders();
    return this.httpClient.patch<Achat>(this.url+"/save"+achat.id,{headers:this.headers});
  }

  public delete(id: number){
    this.initHeaders();
    return this.httpClient.delete<Achat>(this.url+"/delete/"+ id,{headers:this.headers});
  }

  public deleteByElementAndParc(element:Element, parc:Parc ){
    this.initHeaders();
    return this.httpClient.delete<Achat>(this.url+"/element="+ element+"&parc="+parc,{headers:this.headers});

  }

  public getByParc(parc: Parc): Observable <Achat[]>{
    this.initHeaders();
    return this.httpClient.post<Achat[]>(this.url +"/byParc", parc,{headers:this.headers});
  }

  public getByElement(element: Element): Observable <Achat[]>{
    this.initHeaders();
    return this.httpClient.get<Achat[]>(this.url +"/element="+ element,{headers:this.headers});
  }

  public getByTypeElementAndParc(element: string,parc: Parc ): Observable <Achat[]>{
    this.initHeaders();
    return this.httpClient.post<Achat[]>(this.url +"/byTypeElementAndParc/"+ element,parc,{headers:this.headers});
  }

  public getByElementAndParc(element: number,parc: Parc ): Observable <Achat>{
    this.initHeaders();
    return this.httpClient.post<Achat>(this.url +"/byElementAndParc/"+ element,parc,{headers:this.headers});
  }


}
