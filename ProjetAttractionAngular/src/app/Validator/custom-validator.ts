import { AbstractControl, FormControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export class CustomValidator {

  public static testLogin(control:FormControl) : ValidationErrors | null{
    if(control.value === 'valeur'){
      return {'erreurlogin': true};
    }
    else
    {
      return null;
    }
  }

  public static testPassword(text:string) : ValidatorFn{
    return (control: AbstractControl): ValidationErrors | null => {
      if(control.value === text){
        return {'erreurpassword': true};
      }
      else
      {
        return null;
      }
    }
  }

}
