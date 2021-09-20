import { GestionCompteService } from './../../../service/GestionJeu/gestion-compte.service';

import { Compte } from './../../../model/compte';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'list-compte',
  templateUrl: './list-compte.component.html',
  styleUrls: ['./list-compte.component.css']
})
export class ListCompteComponent implements OnInit {

  comptes: Compte[]=[];

  constructor(private gestionCompteService: GestionCompteService) { }

  ngOnInit(): void {
    this.list();
  }

  public list(){
    this.gestionCompteService.getAll()
      .subscribe(res=>{this.comptes=res;});
  }

  public delete(id:number|undefined){
    if (id !== undefined){
      this.gestionCompteService.delete(id)
        .subscribe(res=>{this.list();});
    }
  }
}
