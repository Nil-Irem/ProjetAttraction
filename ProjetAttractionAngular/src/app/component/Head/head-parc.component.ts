import { Component, OnInit } from '@angular/core';
import { Parc } from 'src/app/model/parc';

@Component({
  selector: 'head-parc',
  templateUrl: './head-parc.component.html',
  styleUrls: ['./head-parc.component.css']
})
export class HeadParcComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {}

  public getParc():Parc{
    let parcStorage = localStorage.getItem("parcChosen");
    if(parcStorage){
      return JSON.parse(parcStorage);
    }
    return new Parc("probleme","probleme");
  }


  public getTailleTot():Parc{
    let storage = localStorage.getItem("tailleTotStructure");
    if(storage){
      return JSON.parse(storage)+this.getParc().taille!;
    }
    return new Parc("probleme","probleme");
  }

}
