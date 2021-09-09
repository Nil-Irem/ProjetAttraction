package SoprAjc.ParcAttractionBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class ParcException extends RuntimeException{

	
	public ParcException(String message,Throwable err){
		super(message,err);
	}
	
	public ParcException(String message){
		super(message);
	}
	
	public ParcException(){
		super("Données invalides dans parc");
	}
}
