import { UserAccountService } from './../../../service/user-account.service';
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
    private userAccountService:UserAccountService,
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
    const typeCompte = await this.userAccountService.connexion(
      this.connexionForm.get('login')?.value,
      this.connexionForm.get('password')?.value);

    if (typeCompte==="joueur"){
      this.router.navigate(['/jeu/choixparc']);
    }
    else if(typeCompte==="admin"){
      this.router.navigate(['/admin']);
    }

    this.userValid = false;
  }
}
