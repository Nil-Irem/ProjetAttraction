import { GestionCompteService } from './gestion-compte.service';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Compte } from '../model/compte';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserAccountService {

  private userAccount: Compte|undefined
  private _isConnectedJoueur = new Subject<boolean>();
  private _isConnectedAdmin = new Subject<boolean>();
  private _parcIsChosen = new Subject<boolean>();


  constructor(
    private gestionCompteService:GestionCompteService,
    private router: Router)
  {
    this.userAccount = undefined;
  }

  public async connexion(login:string,password:string){
    const user = await this.gestionCompteService.connexion(
      login,password
    ).toPromise();

    if (user){
      this.userAccount=user;
      if (this.userAccount.isJoueur){
        this._isConnectedJoueur.next(true);
        this.router.navigate(['/jeu/choixparc']);
      }
      else{
        this._isConnectedAdmin.next(true);
        this.router.navigate(['/admin']);
      }
    }
  }


  public isConnectedJoueur(): Observable<boolean> {
    return this._isConnectedJoueur.asObservable();
  }

  public isConnectedAdmin(): Observable<boolean> {
    return this._isConnectedAdmin.asObservable();
  }


  public parcIsChosen(): Observable<boolean> {
    return this._parcIsChosen.asObservable();
  }

  public disconnect(){
    this.userAccount = undefined;
    this._isConnectedJoueur.next(false);
    this._isConnectedAdmin.next(false);
    this._parcIsChosen.next(false);
  }
}
