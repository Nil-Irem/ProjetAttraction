import { GestionCompteService } from './../../../service/GestionJeu/gestion-compte.service';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { debounceTime, map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  inscriptionForm: FormGroup;
  InputLogin:FormControl;
  InputPassword:FormControl;

  constructor(
    private formBuilder:FormBuilder,
    private gestionCompteService:GestionCompteService,
    private router: Router)
  {
    this.InputLogin = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ],
      this.controlLoginIsPresent()
    );

    this.InputPassword = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3),
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{5,})$/)
      ]
    );

    this.inscriptionForm = this.formBuilder.group({
      login: this.InputLogin,
      password: this.InputPassword
    });
  }

  ngOnInit(): void {
  }

  controlLoginIsPresent():AsyncValidatorFn{
    return (control: AbstractControl):Observable<ValidationErrors | null> =>{
      return this.gestionCompteService.loginIsPresent(control.value).pipe(
        debounceTime(1000),
        map((res: boolean) => {
          return res ? { isPresent: true } : null;
        })
      );
    };
  }

  async submit(){
    const login = this.inscriptionForm.get('login')?.value;
    const password = this.inscriptionForm.get('password')?.value;
    const user = await this.gestionCompteService.create({login:login,password:password,isJoueur:true,id:undefined}).toPromise();

    if (user){
      const userBody = {id:user.id,login:user.login,password:user.password};
      localStorage.setItem("isJoueur",JSON.stringify(userBody));
      this.router.navigate(['/jeu/choixparc']);
    }
  }
}
