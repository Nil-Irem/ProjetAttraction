import { UserAccountService } from './../user-account.service';
import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoueurRightsService implements CanActivate {

  constructor(private userAccountService: UserAccountService) { }

  canActivate(): Observable<boolean> {
    return this.userAccountService.isConnectedJoueur();
  }

}
