import { GestionCompteService } from './GestionJeu/gestion-compte.service';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Compte } from '../model/compte';
import { Parc } from '../model/parc';

@Injectable({
  providedIn: 'root'
})
export class UserAccountService {

  private _userAccount: Compte|undefined;
  private _userParc: Parc|undefined;
  private _isConnectedJoueur = new Subject<boolean>();
  private _isConnectedAdmin = new Subject<boolean>();
  private _parcIsChosen = new Subject<boolean>();


  constructor(
    private gestionCompteService:GestionCompteService)
  {
    this._userAccount = undefined;
    this._userParc = undefined;
  }

  public async connexion(login:string,password:string):Promise<string>{
    const user = await this.gestionCompteService.connexion(
      login,password
    ).toPromise();

    if (user){
      this._userAccount=user;
      if (this._userAccount.isJoueur){
        this._isConnectedJoueur.next(true);
        return "joueur";
      }
      else{
        this._isConnectedAdmin.next(true);
        return "admin";
      }
    }
    return "inconnu";
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

  public userAccount(): Compte |undefined {
    return this._userAccount;
  }

  public deconnexion(){
    this._userAccount = undefined;
    this._isConnectedJoueur.next(false);
    this._isConnectedAdmin.next(false);
    this._parcIsChosen.next(false);
  }
}
