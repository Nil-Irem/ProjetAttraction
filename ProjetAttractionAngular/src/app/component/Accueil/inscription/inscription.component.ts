import { LoginService } from './../../../service/login.service';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { debounceTime, map } from 'rxjs/operators';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  FormulaireInscription: FormGroup;
  InputLogin:FormControl;
  InputPassword:FormControl;

  constructor(private formBuilder:FormBuilder,private loginService:LoginService) {
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

    this.FormulaireInscription = this.formBuilder.group({
      InputLogin: this.InputLogin,
      InputPassword: this.InputPassword
    });
  }

  ngOnInit(): void {
  }

  controlLoginIsPresent():AsyncValidatorFn{
    return (control: AbstractControl):Observable<ValidationErrors | null> =>{
      return this.loginService.isPresent(control.value).pipe(
        debounceTime(1000),
        map((res: boolean) => {
          return res ? { isPresent: true } : null;
        })
      );
    };
  }

  submit(){
    console.log("inscription envoye")
  }
}
