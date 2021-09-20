import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Yolo Coaster Tycoon';

  constructor() {}

  public isConnectedJoueur():boolean{
    return localStorage.getItem('isJoueur') ? true : false;
  }

  public isConnectedAdmin():boolean{
    return localStorage.getItem('isAdmin') ? true : false;
  }

  public parcIsChosen():boolean{
    return localStorage.getItem('parcChosen') ? true : false;
  }

}
