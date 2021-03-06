import { GestionParcService } from './../../../../service/GestionJeu/gestion-parc.service';
import { Component, OnInit } from '@angular/core';
import { Parc } from 'src/app/model/parc';
import { GestionAchatService } from 'src/app/service/GestionJeu/gestion-achat.service';

@Component({
  selector: 'app-main-board',
  templateUrl: './main-board.component.html',
  styleUrls: ['./main-board.component.css'],
})
export class MainBoardComponent implements OnInit {
  meteo: string = '';
  argentGagne: number = 0;
  argentDepense: number = 0;
  nbIncident: number = 0;
  nbVisiteur: number = 0;
  prixEntree: number = 50;

  constructor(
    private gestionAchatService: GestionAchatService,
    private gestionParcService: GestionParcService
  ) {}

  ngOnInit(): void {}

  public getParc(): Parc {
    const storage = localStorage.getItem('parcChosen');
    if (storage) {
      return JSON.parse(storage);
    }
    return new Parc('probleme', 'probleme');
  }

  public debutPartie(): boolean {
    const storage = localStorage.getItem('parcChosen');
    if (storage) {
      let parc = JSON.parse(storage);
      if(parc.nbjour>0){
        return false;
      }
    }
    return true;
}

  public finJournee() {
    let nbIncidentInit = 0;
    let nbVisiteurInit = 0;

    const storage = localStorage.getItem('parcChosen');

    let prixFonctionnement = 0;
    let revenuJourPersonne = 0;
    let attractivite = 0;
    let salaire = 0;
    let capaciteMax = 0;
    let impactEmployeCapacite = 2;
    let impactEmployeAttractivite = 2;

    if (storage) {
      let parc = JSON.parse(storage);
      this.gestionAchatService
        .getByTypeElementAndParc('employe', parc)
        .subscribe(
          (achatEmployes) => {
            achatEmployes.forEach((achatEmploye) => {
              if (
                achatEmploye.typeElement === 'employe' &&
                achatEmploye.element.salaire &&
                achatEmploye.element.salaire > 50
              ) {
                impactEmployeCapacite += achatEmploye.element.salaire;
                salaire += achatEmploye.element.salaire;
              } else if (
                achatEmploye.typeElement === 'employe' &&
                achatEmploye.element.salaire &&
                achatEmploye.element.salaire > 50
              ) {
                impactEmployeAttractivite += achatEmploye.element.salaire;
                salaire += achatEmploye.element.salaire;
              }
            });

            this.gestionAchatService.getByParc(parc).subscribe(
              (achats) => {
                achats.forEach((achat) => {
                  if (
                    achat.typeElement !== 'employe' &&
                    achat.typeElement !== 'commodite'
                  ) {
                    let aleatoire = Math.round(Math.random() * 10);
                    let incidentElement = 1;
                    if (aleatoire < achat.element.tauxIncident! / 10) {
                      nbIncidentInit++;
                      incidentElement -= 1 / impactEmployeCapacite;
                    }
                    if (
                      achat.niveauAmelioration &&
                      achat.niveauAmelioration !== 0 &&
                      achat.element.nbAmelioration
                    ) {
                      attractivite +=
                        achat.niveauAmelioration / achat.element.nbAmelioration;
                    }
                    capaciteMax += incidentElement * achat.element.affluence!;
                    prixFonctionnement += achat.element.prixFonctionnement!;
                    if (
                      achat.typeElement === 'restaurant' ||
                      achat.typeElement === 'boutique'
                    ) {
                      revenuJourPersonne += achat.element.revenuJourPersonne!;
                    }
                  }
                });
                attractivite += achats.length / 100;
                if (impactEmployeAttractivite !== 0) {
                  attractivite += 1 / impactEmployeAttractivite;
                }
                if (attractivite > 1) {
                  attractivite = 1;
                } else if (attractivite < 0) {
                  attractivite = 0.1;
                }

                switch (Math.round(Math.random() * 4)) {
                  case 0:
                    this.meteo = 'Forte pluie ??????';
                    nbVisiteurInit = capaciteMax * attractivite * 0.5;
                    break;
                  case 1:
                    this.meteo = 'Faible pluie ???????';
                    nbVisiteurInit = capaciteMax * attractivite * 0.7;
                    break;
                  case 2:
                    this.meteo = 'Nuageux ??????';
                    nbVisiteurInit = capaciteMax * attractivite * 0.9;
                    break;
                  case 3:
                    this.meteo = 'Belles ??claircies ???';
                    nbVisiteurInit = capaciteMax * attractivite;
                    break;
                  case 4:
                    this.meteo = 'Ensoleill?? ??????';
                    nbVisiteurInit = capaciteMax * attractivite * 0.7;
                    break;
                  default:
                    console.log('pb dans calcul m??t??o (fin de journee)');
                }

                this.argentGagne = nbVisiteurInit * (this.prixEntree + revenuJourPersonne);
                this.argentDepense = salaire + prixFonctionnement;
                this.nbVisiteur = nbVisiteurInit;
                this.nbIncident = nbIncidentInit;

                parc.argent = parc.argent + this.argentGagne - this.argentDepense;
                parc.nbjour++;

              this.gestionParcService.save(parc).subscribe(
                (parcSave) => localStorage.setItem("parcChosen",JSON.stringify(parcSave)),
                (error) => console.log("Erreur finDeJournee, saveParc ",error)
              );
            },
            (error) => console.log("Erreur finDeJournee, getAchatByParc ",error)
          );
        },
        (error) => console.log("Erreur finDeJournee, getEmployesByParc ",error)
      );
    }
  }
}
