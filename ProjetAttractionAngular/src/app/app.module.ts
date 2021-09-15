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
import { BaseComponent } from './component/Accueil/base/base.component';

@NgModule({
  declarations: [
    AppComponent,
    ListCompteComponent,
    EditCompteComponent,
    AccueilComponent,
    ConnexionComponent,
    InscriptionComponent,
    InfosComponent,
    BaseComponent
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
