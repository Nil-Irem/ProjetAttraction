import { Component, OnInit } from '@angular/core';
import { Parc } from 'src/app/model/parc';

@Component({
  selector: 'head-parc',
  templateUrl: './head-parc.component.html',
  styleUrls: ['./head-parc.component.css']
})
export class HeadParcComponent implements OnInit {

  parcStorage = localStorage.getItem("parcChosen");

  constructor() { }

  ngOnInit(): void {}

  public getParc():Parc{
    if(this.parcStorage){
      return JSON.parse(this.parcStorage);
    }
    return new Parc("probleme","probleme");
  }

}
