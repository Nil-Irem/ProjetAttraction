import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Parc } from 'src/app/model/parc';
import { Element } from 'src/app/model/element';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  parcStorage = localStorage.getItem('parcChosen');

  elements: Element[] = [];
  mapSize: number = 0;

  constructor(private gestionAchatService: GestionAchatService) {
    if (this.parcStorage) {
      this.gestionAchatService
        .getByParc(JSON.parse(this.parcStorage))
        .subscribe(
          (res) => {
            res.forEach((achat) => {
              if (achat.typeElement !== 'employe') {
                //  achat.element.typeElement=achat.typeElement;
                if (achat.typeElement == 'commodite') {
                  console.log(achat);
                  for (let i = 0; i < achat.nbSameElement; i++) {
                    this.elements.push(achat.element);
                  }
                }

                this.elements.push(achat.element);
              }
            });
          },
          (error) => console.log(error)
        );
    }
    this.mapSize = this.getParc().taille!;
  }

  ngOnInit(): void {}

  public getParc(): Parc {
    let parc = localStorage.getItem('parcChosen');
    if (parc) {
      return JSON.parse(parc);
    }
    return new Parc('probleme', 'probleme');
  }

  public empty(): boolean {
    if (this.elements.length == 0) {
      return true;
    }
    return false;
  }
}
