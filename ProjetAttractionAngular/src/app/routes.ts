import { InfosComponent } from './component/Accueil/infos/infos.component';
import { InscriptionComponent } from './component/Accueil/inscription/inscription.component';
import { ConnexionComponent } from './component/Accueil/connexion/connexion.component';
import { AccueilComponent } from './component/Accueil/accueil.component';
import { EditCompteComponent } from './component/Compte/edit-compte/edit-compte.component';
import { ListCompteComponent } from './component/Compte/list-compte/list-compte.component';
import { Routes } from "@angular/router";

export const routes : Routes = [
  {path: 'accueil', component: AccueilComponent},
  {path: 'connexion', component: ConnexionComponent},
  {path: 'inscription', component: InscriptionComponent},
  {path: 'infosJeu', component: InfosComponent},
  {path: 'comptes', component: ListCompteComponent},
  {path: 'compte/edit/:id', component: EditCompteComponent},
  {path: 'compte/edit', component: EditCompteComponent}
  //{path: '', redirectTo: 'home', pathMatch: 'full'}
];
