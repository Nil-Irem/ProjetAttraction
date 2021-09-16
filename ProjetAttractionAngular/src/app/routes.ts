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
  {path: 'accueil', component: AccueilComponent},
  {path: 'connexion', component: ConnexionComponent},
  {path: 'inscription', component: InscriptionComponent},
  {path: 'infosJeu', component: InfosComponent},

  {path: 'jeu/choixparc', component: ChoixParcComponent},

  {path: 'admin', component: AdminComponent},
  {path: 'comptes', component: ListCompteComponent},
  {path: 'compte/edit/:id', component: EditCompteComponent},
  {path: 'compte/edit', component: EditCompteComponent}
];
