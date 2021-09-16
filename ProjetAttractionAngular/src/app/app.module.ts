import { routes } from './routes';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListCompteComponent } from './component/Compte/list-compte/list-compte.component';
import { RouterModule } from '@angular/router';
import { EditCompteComponent } from './component/Compte/edit-compte/edit-compte.component';
import { AccueilComponent } from './component/Accueil/accueil.component';
import { ConnexionComponent } from './component/Accueil/connexion/connexion.component';
import { InscriptionComponent } from './component/Accueil/inscription/inscription.component';
import { InfosComponent } from './component/Accueil/infos/infos.component';
import { NavBarAccueilComponent } from './component/NavBar/nav-bar-accueil/nav-bar-accueil.component';
import { NavBarJoueurComponent } from './component/NavBar/nav-bar-joueur/nav-bar-joueur.component';
import { NavBarAdminComponent } from './component/NavBar/nav-bar-admin/nav-bar-admin.component';
import { ChoixParcComponent } from './component/Jeu/choix-parc/choix-parc.component';
import { AdminComponent } from './component/Admin/admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    ListCompteComponent,
    EditCompteComponent,
    AccueilComponent,
    ConnexionComponent,
    InscriptionComponent,
    InfosComponent,
    NavBarAccueilComponent,
    NavBarJoueurComponent,
    NavBarAdminComponent,
    ChoixParcComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
