import { UserAccountService } from './../user-account.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AnonymousRightsService {
  isConnectedJoueur = false;
  isConnectedAdmin = false;
  parcIsChosen = false;

  constructor(private userAccountService: UserAccountService) {
    this.userAccountService.isConnectedJoueur().subscribe(
      (res) => this.isConnectedJoueur = res,
      (error) => console.error(error)
    );

    this.userAccountService.isConnectedAdmin().subscribe(
      (res) => this.isConnectedAdmin = res,
      (error) => console.error(error)
    );

    this.userAccountService.parcIsChosen().subscribe(
      (res) => this.parcIsChosen = res,
      (error) => console.error(error)
    );
  }

  canActivate():boolean{
    if (this.isConnectedAdmin || this.isConnectedJoueur || this.parcIsChosen ){
      return false;
    }
    else
    {
      return true;
    }
  }
}
