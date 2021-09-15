import { Compte } from './../../../model/compte';
import { CompteService } from './../../../service/compte.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'list-compte',
  templateUrl: './list-compte.component.html',
  styleUrls: ['./list-compte.component.css']
})
export class ListCompteComponent implements OnInit {

  comptes: Compte[]=[];

  constructor(private compteService: CompteService) { }

  ngOnInit(): void {
    this.list();
  }

  public list(){
    this.compteService.getAll()
      .subscribe(res=>{this.comptes=res;});
  }

  public delete(id:number|undefined){
    if (id !== undefined){
      this.compteService.delete(id)
        .subscribe(res=>{this.list();});
    }
  }
}
