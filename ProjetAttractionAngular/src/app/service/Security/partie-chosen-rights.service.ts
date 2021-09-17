import { Observable } from 'rxjs';
import { UserAccountService } from './../user-account.service';
import { CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PartieChosenRightsService implements CanActivate {

  constructor(private userAccountService: UserAccountService) { }

  canActivate():Observable<boolean>{
    return this.userAccountService.parcIsChosen();
  }
}
