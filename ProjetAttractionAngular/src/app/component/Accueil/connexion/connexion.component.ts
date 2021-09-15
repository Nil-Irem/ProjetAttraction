import { FormControl, FormGroup, FormBuilder, Validators, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  FormulaireConnexion: FormGroup;
  GroupConnexion: FormGroup;
  InputLogin:FormControl;
  InputPassword:FormControl;

  constructor(private formBuilder:FormBuilder) {
    this.InputLogin = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ]
    );

    this.InputPassword = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ]
    );

    this.FormulaireConnexion = this.formBuilder.group({
      InputLogin: this.InputLogin,
      InputPassword: this.InputPassword
    });

    this.GroupConnexion = this.formBuilder.group({
      login: this.InputLogin,
      password: this.InputPassword
    });
  }

  ngOnInit(): void {
  }

  controlEquals(group: FormGroup): ValidationErrors | null {
      let value1 = group.controls.input1.value;
      let value2 = group.controls['input2'].value;
      if (group.controls.input1.errors) {
        return null;
      }
      return value1 != value2 ? { notEquals: true } : null;
  }

  submit(){
    console.log("connexion envoyé");
  }
}
