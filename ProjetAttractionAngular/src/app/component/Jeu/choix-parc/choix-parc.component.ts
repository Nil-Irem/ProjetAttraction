import { GestionParcService } from './../../../service/GestionJeu/gestion-parc.service';
import { UserAccountService } from './../../../service/user-account.service';
import { FormControl, FormGroup, FormBuilder, Validators, AsyncValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Parc } from 'src/app/model/parc';

@Component({
  selector: 'app-choix-parc',
  templateUrl: './choix-parc.component.html',
  styleUrls: ['./choix-parc.component.css']
})
export class ChoixParcComponent implements OnInit {

  newParcForm: FormGroup;
  InputNom:FormControl;
  InputDifficulte:FormControl;
  ajout = false;
  parcs:Parc[] = [];

  constructor(
    private formBuilder:FormBuilder,
    private userAccountService:UserAccountService,
    private gestionParcService:GestionParcService)
  {
    this.InputNom = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ],
      // this.controlParcIsPresent()
    );

    this.InputDifficulte = this.formBuilder.control('',[
        Validators.required]
    );

    this.newParcForm = this.formBuilder.group({
      nom: this.InputNom,
      difficulte: this.InputDifficulte
    });
  }

  ngOnInit(): void {
    this.listParc();
  }

  private listParc(){
    this.gestionParcService.listByJoueur(this.userAccountService.userAccount()).subscribe(
      (res)=>this.parcs=res,
      (error)=>console.log(error)
    );
  }

  // controlParcIsPresent():AsyncValidatorFn{
    // return (control: AbstractControl):Observable<ValidationErrors | null> =>{
      // return this.gestionParcService.nomIsPresent(control.value).pipe(
      //   debounceTime(1000),
      //   map((res: boolean) => {
      //     return res ? { isPresent: true } : null;
      //   })
      // );
  //   };
  // }

  addParc(){
    const nom = this.newParcForm.get('nom')?.value;
    const difficulte = this.newParcForm.get('difficulte')?.value;

    this.gestionParcService.create(new Parc(nom,difficulte)).subscribe(
      (res) => this.listParc(),
      (error) => console.log(error)
    );
  }


  deleteParc(id:number|undefined,nom:string){
    if (id && alert("Voulez vous vraiment supprimer le parc "+nom+" ?")){
      this.gestionParcService.delete(id).subscribe(
        (res) => this.listParc(),
        (error) => console.log(error)
      );
    }
  }


  playParc(id:number|undefined){

    // this.userAccountService.,
    //   (error) => console.log(error)
    // );
  }
}
