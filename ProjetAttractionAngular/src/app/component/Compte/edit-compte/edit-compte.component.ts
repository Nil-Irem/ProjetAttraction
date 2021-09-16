import { GestionCompteService } from './../../../service/gestion-compte.service';
import { CustomValidator } from './../../../Validator/custom-validator';
import { Joueur } from './../../../model/joueur';
import { Compte } from './../../../model/compte';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-compte',
  templateUrl: './edit-compte.component.html',
  styleUrls: ['./edit-compte.component.css']
})
export class EditCompteComponent implements OnInit {

  Formulaire:FormGroup;
  Input:FormControl;
  compte:Compte=new Joueur();

  constructor(
    private fb:FormBuilder,
    private activatedRoute: ActivatedRoute,
    private gestionCompteService: GestionCompteService,
    private router: Router
  ) {
    this.Input = this.fb.control('',[
      Validators.required,
      Validators.minLength(3),
      CustomValidator.testPassword('test'),
      CustomValidator.testLogin
    ]);
    this.Formulaire = this.fb.group({
      Input : this.Input
    });
    this.activatedRoute.params.subscribe((params) => {
      if (params.id) {
        this.gestionCompteService.get(params.id).subscribe((res) => {
          this.compte = res;
        });
      }
    });
  }

  ngOnInit(): void {}


  save() {
    if (this.compte.id) {
      console.log('update');
      this.gestionCompteService.update(this.compte).subscribe((res) => {
        this.goListcompte();
      });
    } else {
      console.log('create');
      this.gestionCompteService.create(this.compte).subscribe((res) => {
        this.goListcompte();
      });
    }
  }

  goListcompte() {
    this.router.navigate(['/comptes']);
  }

  onSubmit(){
    console.log("c'est soumis");
  }

 // controlLiginIsPresent():AsyncValidatorFn{
   // return (control:AbstractControl):Observable<ValidationErrors| null>{
     // return this.loginService.isPresent(control.value).pipe(map((res:boolean)=>
      //{return res?{isPesent:true}:null;}))
//    };
  //}
}
