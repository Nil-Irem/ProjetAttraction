import { UserAccountService } from './service/user-account.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ProjetAttractionAngular';
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

  public disconnect(){
    this.userAccountService.disconnect();
  }
}
