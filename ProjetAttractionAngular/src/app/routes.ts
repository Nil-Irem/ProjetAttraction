import { AdminRightsService } from './service/Security/admin-rights.service';
import { AnonymousRightsService } from './service/Security/anonymous-rights.service';
import { MainBoardComponent } from './component/Jeu/main-board/main-board.component';
import { PartieChosenRightsService } from './service/Security/partie-chosen-rights.service';
import { JoueurRightsService } from './service/Security/joueur-rights.service';
import { AdminComponent } from './component/Admin/admin/admin.component';
import { ChoixParcComponent } from './component/Jeu/choix-parc/choix-parc.component';
import { InfosComponent } from './component/Accueil/infos/infos.component';
import { InscriptionComponent } from './component/Accueil/inscription/inscription.component';
import { ConnexionComponent } from './component/Accueil/connexion/connexion.component';
import { AccueilComponent } from './component/Accueil/accueil.component';
import { EditCompteComponent } from './component/Compte/edit-compte/edit-compte.component';
import { ListCompteComponent } from './component/Compte/list-compte/list-compte.component';
import { Routes } from "@angular/router";

export const routes : Routes = [
  {path: '', redirectTo: 'accueil', pathMatch: 'full'},
  {path: 'accueil', component: AccueilComponent,canActivate:[AnonymousRightsService]},
  {path: 'connexion', component: ConnexionComponent,canActivate:[AnonymousRightsService]},
  {path: 'inscription', component: InscriptionComponent,canActivate:[AnonymousRightsService]},
  {path: 'infosJeu', component: InfosComponent,canActivate:[AnonymousRightsService]},

  {path: 'jeu/choixparc', component: ChoixParcComponent,canActivate:[JoueurRightsService,PartieChosenRightsService]},
  {path: 'jeu/mainBoard', component: MainBoardComponent,canActivate:[PartieChosenRightsService]},

  {path: 'admin', component: AdminComponent,canActivate:[AdminRightsService]},
  {path: 'admin/comptes', component: ListCompteComponent,canActivate:[AdminRightsService]},
  {path: 'admin/compte/edit/:id', component: EditCompteComponent,canActivate:[AdminRightsService]},
  {path: 'admin/compte/edit', component: EditCompteComponent,canActivate:[AdminRightsService]}
];
