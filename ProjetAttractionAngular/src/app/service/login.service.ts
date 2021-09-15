import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url: string = "http://localhost:8080/Yolo/api/compte"
  constructor(private http: HttpClient) { }

  isPresent(login:string):Observable<boolean>{
    return this.http.get<boolean>(this.url+"/loginIsPresent/"+login);
  }
}
