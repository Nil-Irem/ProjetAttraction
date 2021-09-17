import { CanActivate } from '@angular/router';
import { UserAccountService } from './../user-account.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminRightsService implements CanActivate {

  isConnectedAdmin = false;

  constructor(private userAccountService: UserAccountService) {
    this.userAccountService.isConnectedAdmin().subscribe(
      (res) => this.isConnectedAdmin = res,
      (error) => console.error(error)
    );
  }

  canActivate():boolean{
    if (this.isConnectedAdmin){
      return true;
    }
    else
    {
      return false;
    }
  }


}
