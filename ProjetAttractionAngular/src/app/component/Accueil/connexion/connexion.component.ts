import { GestionCompteService } from './../../../service/GestionJeu/gestion-compte.service';
import { FormControl, FormGroup, FormBuilder, Validators, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  connexionForm: FormGroup;
  InputLogin:FormControl;
  InputPassword:FormControl;
  userValid = true;

  constructor(
    private formBuilder:FormBuilder,
    private gestionCompteService: GestionCompteService,
    private router: Router)
  {
    this.InputLogin = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ],
    );

    this.InputPassword = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ]
    );

    this.connexionForm = this.formBuilder.group({
      login: this.InputLogin,
      password: this.InputPassword
    });
  }

  ngOnInit(): void {
  }


  async submit(){
    const user = await this.gestionCompteService.connexion(
      this.connexionForm.get('login')?.value,
      this.connexionForm.get('password')?.value)
    .toPromise();

    if (user){
      const userBody = {id:user.id,login:user.login,password:user.password};

      if (user.isJoueur){
        localStorage.setItem("isJoueur",JSON.stringify(userBody));
        this.router.navigate(['/jeu/choixparc']);
      }
      else {
        localStorage.setItem("isAdmin",JSON.stringify(userBody));
        this.router.navigate(['/admin']);
      }
    }
    else{
      this.userValid = false;
    }
  }
}
