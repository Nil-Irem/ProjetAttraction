import { PossessionComponent } from './component/Jeu/possession/possession.component';
import { AchatComponent } from './component/Jeu/achat/achat.component';
import { HeadParcComponent } from './component/Head/head-parc.component';
import { NavBarAdminComponent } from './component/Head/NavBar/nav-bar-admin/nav-bar-admin.component';
import { NavBarJoueurComponent } from './component/Head/NavBar/nav-bar-joueur/nav-bar-joueur.component';
import { NavBarAccueilComponent } from './component/Head/NavBar/nav-bar-accueil/nav-bar-accueil.component';
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
import { ChoixParcComponent } from './component/Jeu/choix-parc/choix-parc.component';
import { AdminComponent } from './component/Admin/admin/admin.component';
import { MainBoardComponent } from './component/Jeu/mainBoard/main-board/main-board.component';
import { MapComponent } from './component/Jeu/mainBoard/map/map.component';



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
    AdminComponent,
    AchatComponent,
    HeadParcComponent,
    PossessionComponent,
    MainBoardComponent,
    MapComponent


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
