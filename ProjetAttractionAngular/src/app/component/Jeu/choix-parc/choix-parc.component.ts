import { GestionParcService } from './../../../service/GestionJeu/gestion-parc.service';
import { FormControl, FormGroup, FormBuilder, Validators, AsyncValidatorFn, AbstractControl, ValidationErrors } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Parc } from 'src/app/model/parc';
import { Router } from '@angular/router';
import { debounceTime, map } from 'rxjs/operators';

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
  joueur = localStorage.getItem('isJoueur');

  constructor(
    private formBuilder:FormBuilder,
    private gestionParcService:GestionParcService,
    private router:Router)
  {
    this.InputNom = this.formBuilder.control('',[
        Validators.required,
        Validators.minLength(3)
      ],
      this.controlNomIsPresent()
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
    const joueur = localStorage.getItem('isJoueur');
    if (joueur){
      this.gestionParcService.listByJoueur(JSON.parse(joueur)).subscribe(
        (res)=>this.parcs=res,
        (error)=>console.log(error)
      );
    }

  }

  controlNomIsPresent():AsyncValidatorFn{
    return (control: AbstractControl):Observable<ValidationErrors | null> =>{
      if (this.joueur){
        return this.gestionParcService.nomIsPresent(control.value,JSON.parse(this.joueur)).pipe(
        debounceTime(1000),
        map((res: boolean) => {
          return res ? { isPresent: true } : null;
       })
      )}
      return new Observable<null>();
    };
  }

  addParc(){
    const nom = this.newParcForm.get('nom')?.value;
    const difficulte = this.newParcForm.get('difficulte')?.value;

    if (this.joueur){
    this.gestionParcService.create(new Parc(nom,difficulte),JSON.parse(this.joueur)).subscribe(
      (res) => this.listParc(),
      (error) => console.log(error)
    );
    }
  }


  deleteParc(id:number|undefined,nom:string){
    if (id && confirm("Voulez vous vraiment supprimer le parc "+nom+" ?")){
      this.gestionParcService.delete(id).subscribe(
        (res) => this.listParc(),
        (error) => console.log(error)
      );
    }
  }


  playParc(index:number){
    localStorage.setItem("parcChosen",JSON.stringify(this.parcs[index]));
    this.router.navigate(['/jeu/mainBoard']);
  }
}
