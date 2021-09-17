import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'nav-bar-joueur',
  templateUrl: './nav-bar-joueur.component.html',
  styleUrls: ['./nav-bar-joueur.component.css']
})
export class NavBarJoueurComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  deconnection(){
    localStorage.clear();
    this.router.navigate(['/accueil']);
  }

}
